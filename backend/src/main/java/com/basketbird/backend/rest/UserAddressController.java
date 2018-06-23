package com.basketbird.backend.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.basketbird.backend.model.DeliveryAddress;
import com.basketbird.backend.model.RequestWrap;
import com.basketbird.backend.model.RequestWrapper;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.UserAddress;
import com.basketbird.backend.model.UserInfo;
import com.basketbird.backend.model.UserInfoWrapper;
import com.basketbird.backend.service.DeliveryAddressService;
import com.basketbird.backend.service.UserAddressService;

@RestController
@RequestMapping( value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
public class UserAddressController {
	
	@Autowired
	private UserAddressService userAddressService;
	
	@RequestMapping( method = GET, value = "/user/address/{addressId}" )
	public ResponseEntity<?> loadById( @PathVariable String addressId ) {
		try{
			UserAddress result= this.userAddressService.loadAddressById( addressId );
			return new ResponseEntity<UserAddress>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = GET, value = "/user/address/my")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getMyInfo() {
		try{
			UserAddress result = this.userAddressService.loadByMaker();
			return new ResponseEntity<UserAddress>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/user/address/save", method = POST)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> save(@RequestBody UserInfoWrapper info) {

		UserInfo uinfo = info.getUserInfo();
		UserAddress address = info.getAddress();
		try{
			UserAddress result = this.userAddressService.saveUserAddress(uinfo,address);
			return new ResponseEntity<UserAddress>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@RequestMapping( method = POST, value = "/user/address/update" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateAddress(@RequestBody UserAddress address ) {

		try{
			UserAddress result = this.userAddressService.updateUserAddress(address);
			return new ResponseEntity<UserAddress>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}			
	}

}
