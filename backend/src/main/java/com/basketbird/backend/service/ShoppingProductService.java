package com.basketbird.backend.service;

import java.util.List;

import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ShoppingProductWrap;
import com.basketbird.backend.model.ShoppingRequest;

public interface ShoppingProductService {
	
	ShoppingProduct loadProductById(String id);
	List<ShoppingProduct> loadProductByRequest (ShoppingRequest request);
	
	ShoppingProduct saveProduct(ShoppingProduct sp);
	ShoppingProduct updateProduct(ShoppingProduct sp);
	ShoppingProduct saveSPWrapper(ShoppingRequest request, ShoppingProduct sp, Product product);
	ShoppingProduct saveRequestProduct(ListProduct sproduct, ShoppingRequest request);
	void removeProduct(String productId);

}
