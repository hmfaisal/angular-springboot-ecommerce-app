package com.basketbird.backend.service;

import java.util.List;


import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.type.Market;
import com.basketbird.backend.model.type.ProductType;

/**
 * Created by hasan.mahmud on 2017-08-29.
 */

public interface ProductService {
	
	List<Product>loadProductByProductId(String id);
    List<Product> loadProductByProductName(int pageNumber,String productName, String country);  
    List<Product> loadProductByCountry(int pageNumber, String country);
    List<Product> loadAll (int pageNumber, String country);
    void saveProduct(Product product);
    List<Product> loadProductByShop(int pageNumber, Market shop, String country);
    List<Product> loadProductByPriceRange(int pageNumber, int min, int max, String country);
    List<Product> loadProductByPriceLow(int pageNumber, String country);
    List<Product> loadProductByPriceHigh(int pageNumber, String country);
    List<Product> loadProductByCategory(int pageNumber, ProductType category, String country);
    List<?> getCategory();
    List<?> getBrand();
    Long getAllTotal(String country);
    Long getByNameTotal(String productName,String country);
    Long getByShopTotal(Market shop,String country);
    Long getByRangeTotal(int min, int max, String country);
    Long getByCategoryTotal(ProductType category,String country);
}
