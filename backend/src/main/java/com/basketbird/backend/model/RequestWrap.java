package com.basketbird.backend.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestWrap {
	
	private ShoppingRequest shoppingRequest;
	//private List<ShoppingProductWrap> shoppingProductWrap;
	private DeliveryAddress deliveryAddress;
	private List<ShoppingProduct> shoppingProduct;

}
