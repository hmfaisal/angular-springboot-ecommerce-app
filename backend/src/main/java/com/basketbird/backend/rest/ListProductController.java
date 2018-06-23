package com.basketbird.backend.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import java.net.URISyntaxException;
import java.util.List;
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


import com.basketbird.backend.service.ListProductService;
import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.ListProductWrap;
import com.basketbird.backend.model.ListProductWrapper;
import com.basketbird.backend.repository.ListProductRepository;

@RestController
@RequestMapping( value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
public class ListProductController {

	@Autowired
	private ListProductService listProductService;

	@Autowired
	public ListProductRepository listProductRepository;


	@RequestMapping( method = GET, value = "/list/product/{productId}" )
	public ResponseEntity<?> loadById( @PathVariable String productId) {
		try{
			ListProduct result= this.listProductService.loadProductById( productId );
			return new ResponseEntity<ListProduct>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(method = GET, value = "/list/product/unlisted")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> loadAllUnlisted() {
		try{
			List<ListProduct> result = this.listProductService.loadUnlistedProductByMaker();
			return new ResponseEntity<List<ListProduct>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}


	@RequestMapping( method = POST, value = "/list/product/save" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> save(@RequestBody ListProductWrapper lproductwrapper ) {

		ShoppingList list = lproductwrapper.getShoppingList();
		ListProduct lproduct = lproductwrapper.getListProduct();
		Product product = lproductwrapper.getProduct();
		try{
			ListProduct result = this.listProductService.saveLPWrapper(list,lproduct,product);
			return new ResponseEntity<ListProduct>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}			
	}

	@RequestMapping( method = POST, value = "/list/product/update" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> update(@RequestBody ListProduct lproduct ) {

		try{
			ListProduct result = this.listProductService.updateProduct(lproduct);
			return new ResponseEntity<ListProduct>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}			
	}

	@RequestMapping( method = DELETE, value = "/list/product/remove/{productId}" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> remove( @PathVariable String productId ) {
		
		try{
			this.listProductService.removeProduct( productId);
			return new ResponseEntity<String>("Deleted", HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}	
	}

	@RequestMapping(method = GET, value = "/list/product/unlisted/count")
	@PreAuthorize("hasRole('USER')")
	public Long loadAllUnlistedCount() {
		return this.listProductService.loadUnlistedProductByMakerTotal();
	}

}
