package com.basketbird.backend.service;

import java.util.List;

import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.UserAddress;
import com.basketbird.backend.model.UserInfo;
import com.basketbird.backend.model.DeliveryAddress;
import com.basketbird.backend.model.ListProduct;

public interface HelperService {
	
	ListProduct getListProduct(ListProduct lproduct);
	Product getProduct(Product product);
	ShoppingList getShoppingList(ShoppingList list);
	ShoppingProduct getShoppingProduct(ShoppingProduct sproduct);
	ShoppingRequest getShoppingRequest(ShoppingRequest request);
	DeliveryAddress getDeliveryAddress(DeliveryAddress address);
	UserInfo getUserInfo(UserInfo info);
	UserAddress getUserAddress(UserAddress address);
}
