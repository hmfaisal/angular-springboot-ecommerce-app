package com.basketbird.backend.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.basketbird.backend.service.ShoppingListService;
import com.basketbird.backend.model.ListWrap;
import com.basketbird.backend.model.ListWrapper;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.ListProductWrap;
import com.basketbird.backend.model.ListProductWrapper;
import com.basketbird.backend.repository.ShoppingListRepository;


@RestController
@RequestMapping( value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
public class ShoppingListController {
	
	@Autowired
    private ShoppingListService shoppingListService;
	
	@Autowired
	public ShoppingListRepository shoppingListRepository;
	
	@RequestMapping( method = GET, value = "/list/{listId}" )
    public ResponseEntity<?> loadById( @PathVariable String listId ) {
        try{
        	ListWrap result= this.shoppingListService.loadListByListId( listId );
			return new ResponseEntity<ListWrap>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
    }
	
	@RequestMapping( method = GET, value = "/list/name" )
    public ResponseEntity<?> loadByName( @RequestParam("name") String name) {
		try{
			ListWrap result = this.shoppingListService.loadListByListName( name );
			return new ResponseEntity<ListWrap>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
    }
	
	@RequestMapping(method = GET, value = "/list/my")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getMyAll(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		try{
			List<ListWrap> result = this.shoppingListService.loadListByMaker(pageNumber);
			return new ResponseEntity<List<ListWrap>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping( method = POST, value = "/list/save" )
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> save(@RequestBody ListWrapper listwrapper  ) {
		ShoppingList list = listwrapper.getShoppingList();
		List<ListProduct> lproduct = listwrapper.getListProduct();
		try{
			ListWrap result = this.shoppingListService.saveListWrapper(list,lproduct);
			return new ResponseEntity<ListWrap>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		
    }
	
	@RequestMapping( method = POST, value = "/list/update" )
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> update(@RequestBody ShoppingList list ) {
		try{
			ShoppingList result = this.shoppingListService.updateList(list);
			return new ResponseEntity<ShoppingList>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		
    }
    
	@RequestMapping( method = DELETE, value = "/list/remove/{listId}" )
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> remove( @PathVariable String listId ) {
		
		try{
			this.shoppingListService.removeList( listId );
			return ResponseEntity.ok().build();
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<ShoppingList>(HttpStatus.BAD_REQUEST);
		}
		
    }
	
	@RequestMapping(method = GET, value = "/list/my/count")
	@PreAuthorize("hasRole('USER')")
	public Long getMyAllCount() {
		return this.shoppingListService.loadListByMakerTotal();
	}
	
}
