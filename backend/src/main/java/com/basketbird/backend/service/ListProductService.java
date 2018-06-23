package com.basketbird.backend.service;

import java.util.List;

import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.ListProductWrap;
import com.basketbird.backend.model.ListProductWrapper;

public interface ListProductService {
	
	ListProduct loadProductById(String id);
	
	List<ListProduct> loadProductByList (ShoppingList list);
	List<ListProduct> loadUnlistedProductByMaker ();
	
	ListProduct saveProduct(ListProduct sp);
	ListProduct updateProduct(ListProduct sp);
	ListProduct saveLPWrapper(ShoppingList list,ListProduct sproduct,Product product);
	ListProduct updateListProduct(ListProduct sp, ShoppingList list);
	void removeProduct(String productId);
    
    	Long loadUnlistedProductByMakerTotal();

}
