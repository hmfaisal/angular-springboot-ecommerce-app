package com.basketbird.backend.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListProductWrap {

	private ListProduct listProduct;
	private Product product;

}
