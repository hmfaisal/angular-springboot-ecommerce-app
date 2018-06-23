package com.basketbird.backend.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShoppingProductWrap {
	
	private ShoppingProduct shoppingProduct;
	private Product product;

}
