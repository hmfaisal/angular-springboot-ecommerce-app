package com.basketbird.backend.service.impl;

import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.type.Market;
import com.basketbird.backend.model.type.ProductType;
import com.basketbird.backend.repository.ProductRepository;
import com.basketbird.backend.service.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	public ProductRepository productRepository;
	
	private final static int PAGESIZE = 20;
	
    @Override
	@Transactional(readOnly = true)
    public List<Product> loadProductByProductId( String id ) throws AccessDeniedException {
    	List<Product> p = productRepository.findById( Long.valueOf(id) );
        return p;
    }
	
	@Override
    @Transactional(readOnly = true)
    public List<Product> loadAll(int pageNumber,String country) throws AccessDeniedException {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
        List<Product> result = productRepository.findByAll(request, country);
        return result;
    }
	
	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
		
	}
	
	@Override
    @Transactional(readOnly = true)
    public List<Product> loadProductByProductName( int pageNumber,String productName,String country ) throws AccessDeniedException {
		PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "productName");
		List<Product> result = productRepository.findByProductName(request,productName,country);
        return result;
	}
	
	
	@Override
    @Transactional(readOnly = true)
    public List<Product> loadProductByCountry( int pageNumber,String country ) throws AccessDeniedException {
		PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
		List<Product> result = productRepository.findByCountry(request,country);
        return result;
	}
	
	@Override
    @Transactional(readOnly = true)
    public List<Product> loadProductByShop( int pageNumber,Market shop,String country ) throws AccessDeniedException {
		PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
		List<Product> result = productRepository.findByShop(request,shop,country);
        return result;
	}
	
	@Override
    @Transactional(readOnly = true)
    public List<Product> loadProductByPriceRange( int pageNumber,int min, int max ,String country) throws AccessDeniedException {
		PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
		List<Product> result = productRepository.findByPriceRange(request,min, max,country);
        return result;
	}
	
	@Override
    @Transactional(readOnly = true)
    public List<Product> loadProductByPriceLow( int pageNumber,String country) throws AccessDeniedException {
		PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
		List<Product> result = productRepository.findByPriceLow(request,country);
        return result;
	}
	
	@Override
    @Transactional(readOnly = true)
    public List<Product> loadProductByPriceHigh( int pageNumber,String country) throws AccessDeniedException {
		PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
		List<Product> result = productRepository.findByPriceHigh(request,country);
        return result;
	}
	
	@Override
    @Transactional(readOnly = true)
    public List<Product> loadProductByCategory( int pageNumber,ProductType category ,String country) throws AccessDeniedException {
		PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
		List<Product> result = productRepository.findByCategory(request,category,country);
        return result;
	}
	
	@Override
    @Transactional(readOnly = true)
    public List<?> getCategory( ) throws AccessDeniedException {
		List<?> result = productRepository.getCategory();
        return result;
	}
	
	@Override
    @Transactional(readOnly = true)
    public List<?> getBrand( ) throws AccessDeniedException {
		List<?> result = Arrays.asList(Market.values());
        return result;
	}
	
	@Override
    @Transactional(readOnly = true)
    public Long getAllTotal(String country ) throws AccessDeniedException {
		Long result = productRepository.getTotal(country);
        return result;
	}
	
	@Override
    @Transactional(readOnly = true)
    public Long getByNameTotal(String productName,String country ) throws AccessDeniedException {
		Long result = productRepository.findByNameTotal(productName,country);
        return result;
	}
	
	@Override
    @Transactional(readOnly = true)
    public Long getByShopTotal(Market shop,String country ) throws AccessDeniedException {
		Long result = productRepository.findByShopTotal(shop,country);
        return result;
	}
	
	@Override
    @Transactional(readOnly = true)
    public Long getByRangeTotal(int min, int max,String country ) throws AccessDeniedException {
		Long result = productRepository.findByPriceRangeTotal(min,max,country);
        return result;
	}
	
	@Override
    @Transactional(readOnly = true)
    public Long getByCategoryTotal(ProductType category, String country ) throws AccessDeniedException {
		Long result = productRepository.findByCategoryTotal(category,country);
        return result;
	}

}
