package com.basketbird.backend.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListWrapper {
	
	private ShoppingList shoppingList;
	private List<ListProduct> listProduct;
}
