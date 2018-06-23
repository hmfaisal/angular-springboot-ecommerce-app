package com.basketbird.backend.service;

import java.util.List;

import com.basketbird.backend.model.ListWrap;
import com.basketbird.backend.model.ListWrapper;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.ListProductWrapper;

/**
 * Created by hasan.mahmud on 2017-08-29.
 */

public interface ShoppingListService {
	
	ListWrap loadListByListId(String id);
	ListWrap loadListByListName(String listName);  
	List<ListWrap> loadListByMaker (int pageNumber);
	
	ListWrap saveListWrapper(ShoppingList list,List<ListProduct> lproduct);
	ShoppingList saveList(ShoppingList list);
	ShoppingList updateList(ShoppingList list);
    void removeList(String listId);
    
    Long loadListByMakerTotal();
}
