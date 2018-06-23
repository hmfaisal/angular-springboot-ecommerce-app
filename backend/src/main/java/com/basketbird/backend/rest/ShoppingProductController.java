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


import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ShoppingProductWrapper;
import com.basketbird.backend.service.ShoppingProductService;
import com.basketbird.backend.repository.ShoppingProductRepository;

@RestController
@RequestMapping( value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
public class ShoppingProductController {
	
	@Autowired
	private ShoppingProductService shoppingProductService;

	@Autowired
	public ShoppingProductRepository shoppingProductRepository;
	
	@RequestMapping( method = GET, value = "/request/product/{productId}" )
	public ResponseEntity<?> loadById( @PathVariable String productId ) {
		try{
			ShoppingProduct result= this.shoppingProductService.loadProductById( productId );
			return new ResponseEntity<ShoppingProduct>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping( method = POST, value = "/request/product/save" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> save(@RequestBody ShoppingProductWrapper sproductwrapper ) {

		ShoppingProduct sproduct = sproductwrapper.getShoppingProduct();
		ShoppingRequest request = sproductwrapper.getRequest();
		Product product = sproductwrapper.getProduct();
		try{
			ShoppingProduct result = this.shoppingProductService.saveSPWrapper(request, sproduct, product);
			return new ResponseEntity<ShoppingProduct>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}			
	}

	@RequestMapping( method = POST, value = "/request/product/update" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> update(@RequestBody ShoppingProduct sproduct ) {

		try{
			ShoppingProduct result = this.shoppingProductService.updateProduct(sproduct);
			return new ResponseEntity<ShoppingProduct>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}			
	}

	@RequestMapping( method = DELETE, value = "/request/product/remove/{productId}" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> remove( @PathVariable String productId ) {
		
		try{
			this.shoppingProductService.removeProduct( productId);
			return ResponseEntity.ok().build();
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}	
	}

}
