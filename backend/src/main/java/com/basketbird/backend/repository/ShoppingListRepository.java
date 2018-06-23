package com.basketbird.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.User;

public interface ShoppingListRepository extends BaseRepository<ShoppingList,Long> {
	
	ShoppingList findById(Long id);
	List<ShoppingList> findByMaker(Pageable pageable,User maker);
	
	//unused
	Page<ShoppingList> findAll(Pageable pageable);
	
	ShoppingList findByListName( String listName );
	
	Long countByMaker(User maker);
}
