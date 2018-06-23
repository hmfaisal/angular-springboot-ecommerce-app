package com.basketbird.backend.importer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.WritableResource;

import com.basketbird.backend.importer.entity.OpenProduct;
import com.basketbird.backend.model.Elements;
import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.type.ProductType;
import com.basketbird.backend.model.type.UnitType;
import com.basketbird.backend.repository.ElementRepository;
import com.basketbird.backend.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@SpringBootApplication
public class OpenProductImporter extends ProductImporter<OpenProduct, Product> {

	public static boolean isImporterDone = false;
	public static boolean isConversionDone = false;
	public static boolean allDone = false;

	public static final Logger logger = LoggerFactory.getLogger(OpenProductImporter.class);

	public static String IMAGE_MAP_FILE = "image_map.txt";

	public static HashMap<String, String> imageMap = new HashMap<>();
	
	public static HashMap<String, String> elements = new HashMap<>();

	public static Queue<String> imageUrls = new LinkedList<String>();

	
	ElementRepository elementRepository;
	
	public static void run(ProductRepository productRepository, ElementRepository elementRepository) {
		OpenProductImporter importer = new OpenProductImporter();
		importer.elementRepository = elementRepository;
		ExecutorService executor = Executors.newFixedThreadPool(4);
		executor.submit(() -> {
			importer.loadAllProduct();
		});
		executor.submit(() -> {
			importer.convertAllProduct();
		});
		executor.submit(() -> {
			importer.saveAllProduct(productRepository);
		});
		executor.submit(() -> {
			importer.downloadImage();
		});

		try {
			importer.loadHashMap();
			logger.info("attempt to shutdown executor");
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException | ClassNotFoundException | IOException e) {
			System.err.println("tasks interrupted");
		} finally {
			if (!executor.isTerminated()) {
				logger.error("cancel non-finished tasks");
			}
			executor.shutdownNow();
			logger.error("shutdown finished");
		}
	}

	@Override
	public void loadAllProduct() {
		logger.debug("################# Importer Started #############################");
		Reader input;
		try {
			input = new FileReader("/home/hmahmud/Downloads/en.openfoodfacts.org.products.csv");
			CsvMapper m = new CsvMapper();
			CsvSchema schema = m.schemaFor(OpenProduct.class).withoutHeader().withLineSeparator("\n")
					.withColumnSeparator('\t');
			MappingIterator<OpenProduct> r = m.reader(OpenProduct.class).with(schema).readValues(input);
			int i = 0;
			String line ="";
			while (r.hasNext()) {
				try {
					i++;
					line = r.toString();
					// logger.debug(r.next().toString());
					importer.add(r.next());
				} catch (Exception e) {
					logger.error(line + e.getMessage());
				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage());
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		isImporterDone = true;
		logger.debug("################# Importer Done #############################");
	}

	@Override
	public void convertAllProduct() {
		logger.debug("################# Conversion Started #############################");
		while (!importer.isEmpty() || !isImporterDone) {
			try {
				if (importer.isEmpty()) {
					Thread.sleep(100);
					continue;
				}

				OpenProduct openProduct = importer.poll();
				if (openProduct == null)
					continue;

				Product p = getConvertedProduct(openProduct);

				logger.debug("Conversion: " + p.toString());
				converter.add(p);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}

		}
		isConversionDone = true;
		logger.debug("################# Conversion Done #############################");
	}

	@Override
	public void saveAllProduct(ProductRepository productRepository) {
		logger.debug("################# Insertion Started #############################");
		while (!converter.isEmpty() || !isImporterDone || !isConversionDone) {
			try {
				if (converter.isEmpty()) {
					Thread.sleep(100);
					continue;
				}

				Product p = converter.poll();
				if (p == null)
					continue;

				productRepository.save(p);
				logger.debug("save:" + p.toString());
				if (p.getProductImageUrl() != null && !imageMap.containsKey(p.getProductImageUrl())) {
					imageUrls.add(p.getProductImageUrl());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e.getMessage());
			}

		}
		allDone = true;
		logger.debug("################# Insertion Done #############################");
	}

	public Product getConvertedProduct(OpenProduct openProduct) {
		try {
			Product p = new Product();
			p.setCompanyProductId(openProduct.getCode());
			p.setCountry(((openProduct.getCountriesEn()) != null) ? openProduct.getCountriesEn().split(":")[0] : "");
			p.setProductName(
					((openProduct.getProductName()) != null) ? openProduct.getProductName().split(":")[0] : "");
			p.setCategory(ProductType.PRODUCT);
			if(openProduct.getImageUrl() != null)
				p.setProductImageUrl(openProduct.getImageUrl().toString());
			//p.setElements(getElements(openProduct));
			return p;
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return null;
	}

	public void downloadImage() {
		logger.debug("************* IMAGE DOWNLOAD START **********************");
		while (!imageUrls.isEmpty() || !allDone) {
			try {
				if (imageUrls.isEmpty()) {
					Thread.sleep(100);
					continue;
				}

				String url = imageUrls.poll();
				if (url == null)
					continue;

				if (!storeImageIntoFS(url)) {
					logger.debug("******************failed*********************");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e.getMessage());
			}
		}
		logger.debug("************* IMAGE DOWNLOAD END **********************");
	}

	public List<Elements> getElements(OpenProduct openProduct) {
		List<Field> fields = Arrays.asList(openProduct.getClass().getDeclaredFields());
		List<Elements> elements = new ArrayList<Elements>();
		HashSet<String> items = new HashSet<>();
		
		fields.parallelStream().forEach(f -> {
			f.setAccessible(true);

			try {
				if (f.get(openProduct) == null || f.get(openProduct).toString().equals(""))
					return;

				else if (f.getName().endsWith("100g") && !items.contains(f.getName().replace("100g", ""))) {
					items.add(f.getName().replace("100g", ""));
					Elements em = new Elements();
					em.setElementName(f.getName().replace("100g", ""));
					em.setUnit(100);
					em.setUnitType(UnitType.GRAM);
					elements.add(em);
				} else if (f.getName().toUpperCase().equals("ADDITIVES")) {
					String additives[] = f.get(openProduct).toString().replace('>', ' ').replace(']', ' ')
							.replace('[', ' ').split(" |-");
					for (int i = 0; i < additives.length - 1; i += 2) {
						if (items.contains(additives[i]))
							continue;
						items.add(additives[i]);
						Elements em = new Elements();
						em.setElementName(additives[i]);
						em.setUnit(1);
						em.setUnitType(UnitType.STUCK);
						elements.add(em);
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {

				logger.debug(e.getStackTrace().toString());
			}
		});
		return elements;
	}

	public void loadHashMap() throws IOException, ClassNotFoundException {

		File file = new File(IMAGE_MAP_FILE);
		FileInputStream f = new FileInputStream(file);
		ObjectInputStream s = new ObjectInputStream(f);
		HashMap<String, Object> fileObj2 = (HashMap<String, Object>) s.readObject();
		s.close();

	}

	public void saveHashMap() throws IOException {
		File file = new File(IMAGE_MAP_FILE);
		FileOutputStream f = new FileOutputStream(file);
		ObjectOutputStream s = new ObjectOutputStream(f);
		s.writeObject(imageMap);
		f.close();
		s.close();
	}

	public boolean storeImageIntoFS(String imageUrl) {
		String imagePath = null;
		try {
			String fileName = imagePath.split("/")[imagePath.split("/").length - 1];
			byte[] bytes = Jsoup.connect(imageUrl).ignoreContentType(true).execute().bodyAsBytes();
			ByteBuffer buffer = ByteBuffer.wrap(bytes);
			String rootTargetDirectory = WritableResource.class.getResource("/").getFile() + "images";
			imagePath = rootTargetDirectory + "/" + fileName;
			FileOutputStream out = (new FileOutputStream(new java.io.File(imagePath)));
			out.write(bytes); // resultImageResponse.body() is where the image's
								// contents are.
			out.close();
			imageMap.put(imageUrl, imagePath);
			saveHashMap();

		} catch (IOException e) {
			logger.debug(e.getMessage());
			return false;
		}
		return true;
	}

}
