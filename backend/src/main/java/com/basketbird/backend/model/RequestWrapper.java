package com.basketbird.backend.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class RequestWrapper {
	
	private ShoppingList shoppingList;
	private DeliveryAddress deliveryAddress;
	private ShoppingRequest shoppingRequest;

}
