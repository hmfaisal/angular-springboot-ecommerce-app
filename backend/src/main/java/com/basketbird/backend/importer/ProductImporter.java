package com.basketbird.backend.importer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.basketbird.backend.importer.entity.BaseEntity;
import com.basketbird.backend.model.BaseModel;
import com.basketbird.backend.repository.ProductRepository;

public abstract class ProductImporter<I extends BaseEntity, O extends BaseModel> {
public Queue<I> importer = new LinkedList<I>();
public Queue<O> converter = new LinkedList<O>();

public abstract void loadAllProduct();
public abstract void convertAllProduct();
public abstract void saveAllProduct(ProductRepository productRepository);

}
