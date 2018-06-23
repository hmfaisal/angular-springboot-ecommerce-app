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

import com.basketbird.backend.model.ListWrap;
import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ShoppingProductWrapper;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.UserAddress;
import com.basketbird.backend.model.UserInfo;
import com.basketbird.backend.model.UserInfoWrapper;
import com.basketbird.backend.repository.UserInfoRepository;
import com.basketbird.backend.service.UserInfoService;

@RestController
@RequestMapping( value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
public class UserInfoController {
	
	@Autowired
    private UserInfoRepository userInfoRepository;
	
	@Autowired
    private UserInfoService userInfoService;
	
	@RequestMapping( method = GET, value = "/userinfo/{infoId}" )
	public ResponseEntity<?> loadById( @PathVariable String infoId ) {
		try{
			UserInfo result= this.userInfoService.loadUserInfoById( infoId );
			return new ResponseEntity<UserInfo>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = GET, value = "/userinfo/my")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getMyInfo() {
		try{
			UserInfo result = this.userInfoService.loadInfoByMaker();
			return new ResponseEntity<UserInfo>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping( method = POST, value = "/userinfo/save" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> save(@RequestBody UserInfo info) {
		
		try{
			UserInfo result = this.userInfoService.saveInfo(info);
			return new ResponseEntity<UserInfo>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}			
	}

}
