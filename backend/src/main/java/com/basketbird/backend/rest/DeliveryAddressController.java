package com.basketbird.backend.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

import com.basketbird.backend.model.DeliveryAddress;
import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.service.DeliveryAddressService;
import com.basketbird.backend.service.ListProductService;

@RestController
@RequestMapping( value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
public class DeliveryAddressController {
	
	@Autowired
	private DeliveryAddressService deliveryAddressService;
	
	@RequestMapping( method = GET, value = "/delivery/address/{addressId}" )
	public ResponseEntity<?> loadById( @PathVariable String addressId ) {
		try{
			DeliveryAddress result= this.deliveryAddressService.loadAddressById( addressId );
			return new ResponseEntity<DeliveryAddress>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	//this is update
	@RequestMapping( method = POST, value = "/delivery/address/save" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> saveDeliveryAddress(@RequestBody DeliveryAddress address ) {

		try{
			DeliveryAddress result = this.deliveryAddressService.saveDeliveryAddress(address);
			return new ResponseEntity<DeliveryAddress>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}			
	}

}
