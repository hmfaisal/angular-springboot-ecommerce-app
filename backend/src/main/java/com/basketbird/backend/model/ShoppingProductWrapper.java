package com.basketbird.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShoppingProductWrapper {
	
	private ShoppingProduct shoppingProduct;
	private ShoppingRequest request;
	private Product product;

}
