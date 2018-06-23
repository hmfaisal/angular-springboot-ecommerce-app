package com.basketbird.backend.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListWrap {
	private ShoppingList shoppingList;
	//private List<ListProductWrap> listProductWrap;
	private List<ListProduct> listProduct;

}
