package com.basketbird.backend.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ListProductWrapper {
	
	private ShoppingList shoppingList;
	private ListProduct listProduct;
	private Product product;

}
