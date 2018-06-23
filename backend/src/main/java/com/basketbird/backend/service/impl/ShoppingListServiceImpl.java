package com.basketbird.backend.service.impl;

import com.basketbird.backend.model.ListWrap;
import com.basketbird.backend.model.ListWrapper;
import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.ListProductWrap;
import com.basketbird.backend.model.ListProductWrapper;
import com.basketbird.backend.model.User;
import com.basketbird.backend.repository.ShoppingListRepository;
import com.basketbird.backend.repository.ListProductRepository;
import com.basketbird.backend.repository.ShoppingRequestRepository;
import com.basketbird.backend.service.ShoppingListService;
import com.basketbird.backend.service.HelperService;
import com.basketbird.backend.service.ListProductService;
import com.basketbird.backend.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShoppingListServiceImpl implements ShoppingListService {

	@Autowired
	public ShoppingListRepository shoppingListRepository;
	
	@Autowired
	public ListProductRepository listProductRepository;

	@Autowired
	private ListProductService listProductService;
	
	@Autowired
	private HelperService helperService;

	@Autowired
	private UserService userService;

	private final static int PAGESIZE = 20;


	@Override
	@Transactional(readOnly = true)
	public ListWrap loadListByListId(String id ) throws AccessDeniedException {
		
		ShoppingList list = this.shoppingListRepository.findById( Long.valueOf(id) );
		ListWrap result = this.returnListWrap(list);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public ListWrap loadListByListName(String listName ) throws AccessDeniedException {
		
		ShoppingList list = shoppingListRepository.findByListName( listName  );
		ListWrap result = this.returnListWrap(list);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ListWrap> loadListByMaker(int pageNumber) throws AccessDeniedException {
		
		List<ListWrap> result = new ArrayList<>();
		User listMaker = this.userService.currentUser();
		PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
		List<ShoppingList> list = this.shoppingListRepository.findByMaker(request,listMaker);
		if(list!=null){
			list.forEach(item->{
				ListWrap res = this.returnListWrap(item);
				result.add(res);
	        });	
		}
		return result;
	}


	public ListWrap saveListWrapper(ShoppingList list,List<ListProduct> lproduct) {
		ListWrap result = new ListWrap();
		ShoppingList l = this.helperService.getShoppingList(list);	
		if(l==null){
			list.setMaker(this.userService.currentUser());
			ShoppingList lresult = this.saveList(list);
			if(lproduct!=null){
				lproduct.forEach(item->{
					this.listProductService.updateListProduct(item, lresult);
				});
			}
			result = this.returnListWrap(lresult);
		}
		return result;	
	}

	@Override
	public ShoppingList saveList(ShoppingList list) {
		return this.shoppingListRepository.save(list);
	}

	@Override
	public ShoppingList updateList(ShoppingList list) {
		ShoppingList l = this.helperService.getShoppingList(list);
		if(l!=null && canEdit(l)){	
			list.setMaker(this.userService.currentUser());
			return this.saveList(list);
		}else{
			return null;
		}
	}

	@Override
	public void removeList(String listId) {
		ShoppingList list = shoppingListRepository.findOne(Long.valueOf(listId));
		if(list!=null && canEdit(list)){
			shoppingListRepository.delete(Long.valueOf(listId));
		}
	}
	
	private boolean canEdit(ShoppingList list){
		//ShoppingList l = this.shoppingListRepository.findOne(list.getId());
		User currentUser = this.userService.currentUser();
		if(currentUser == list.getMaker()){
			return true;
		}
		return false;
	}
	
	
	private ListWrap returnListWrap( ShoppingList list) throws AccessDeniedException {
		
		List<ListProduct> lp= null;
		if(list!=null){
			lp= this.listProductService.loadProductByList(list);
		}
		ListWrap result = new ListWrap();
    	result.setShoppingList(list);
    	result.setListProduct(lp);
        return result;
    }

	@Override
	@Transactional(readOnly = true)
	public Long loadListByMakerTotal() throws AccessDeniedException {
		User listMaker = this.userService.currentUser();
		Long result = shoppingListRepository.countByMaker(listMaker);
		return result;
	}
}
