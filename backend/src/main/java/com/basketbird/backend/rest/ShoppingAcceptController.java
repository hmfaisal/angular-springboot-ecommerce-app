package com.basketbird.backend.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
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
import com.basketbird.backend.model.RequestWrap;
import com.basketbird.backend.model.RequestWrapper;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.type.RequestStatus;
import com.basketbird.backend.repository.ShoppingListRepository;
import com.basketbird.backend.repository.ShoppingRequestRepository;
import com.basketbird.backend.service.ShoppingAcceptService;
import com.basketbird.backend.service.ShoppingRequestService;

@RestController
@RequestMapping( value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
public class ShoppingAcceptController {
	
	@Autowired
	private ShoppingAcceptService shoppingAcceptService;

	@Autowired
	public ShoppingRequestRepository shoppingRequestRepository;

	@Autowired
	public ShoppingListRepository shoppingListRepository;
	
	
	@RequestMapping(method = GET, value = "/accept/my/active")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getMyActiveAccept(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		try{
			List<RequestWrap> result = this.shoppingAcceptService.loadActiveAcceptByMaker(pageNumber);
			return new ResponseEntity<List<RequestWrap>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = GET, value = "/accept/my/archived")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getMyArchivedAccept(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		try{
			List<RequestWrap> result = this.shoppingAcceptService.loadArchivedAcceptByMaker(pageNumber);
			return new ResponseEntity<List<RequestWrap>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@RequestMapping( method = POST, value = "/accept/save" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> acceptSave(@RequestBody ShoppingRequest request) {

		try{
			ShoppingRequest result = this.shoppingAcceptService.saveAccept(request);
			return new ResponseEntity<ShoppingRequest>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@RequestMapping( method = POST, value = "/accept/remove" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> acceptRemove(@RequestBody ShoppingRequest request) {
		try{
			ShoppingRequest result = this.shoppingAcceptService.removeAccept(request);
			return new ResponseEntity<ShoppingRequest>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@RequestMapping( method = POST, value = "/accept/deliver/save" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> deliverSave(@RequestBody ShoppingRequest request) {

		try{
			ShoppingRequest result = this.shoppingAcceptService.saveDeliver(request);
			return new ResponseEntity<ShoppingRequest>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@RequestMapping(method = GET, value = "/accept/my/active/count")
	@PreAuthorize("hasRole('USER')")
	public long getMyActiveAcceptCount() {
		return this.shoppingAcceptService.loadActiveAcceptByMakerTotal();
	}
	
	@RequestMapping(method = GET, value = "/accept/my/archived/count")
	@PreAuthorize("hasRole('USER')")
	public long getMyArchivedAcceptCount() {
		return this.shoppingAcceptService.loadArchivedAcceptByMakerTotal();
	}

}
