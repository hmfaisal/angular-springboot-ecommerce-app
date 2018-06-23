package com.basketbird.backend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.User;

public interface ListProductRepository extends BaseRepository<ListProduct,Long> {

	ListProduct findById(Long id);
	List<ListProduct> findByMaker(Pageable pageable,User productMaker);	
	List<ListProduct> findByList(ShoppingList list);
	
	@Query("select p FROM ListProduct p WHERE p.maker =:productMaker AND p.list IS NULL")
	List<ListProduct> findUnlistedByMaker(@Param("productMaker") User productMaker);
	
	@Query("select count(p) FROM ListProduct p WHERE p.maker =:productMaker AND p.list IS NULL")
	Long countUnlistedByMaker(@Param("productMaker") User productMaker);

}
