package com.basketbird.backend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.User;

public interface ShoppingProductRepository extends BaseRepository<ShoppingProduct,Long>{
	
	ShoppingProduct findById(Long id);	
	List<ShoppingProduct> findByRequest(ShoppingRequest request);
}
