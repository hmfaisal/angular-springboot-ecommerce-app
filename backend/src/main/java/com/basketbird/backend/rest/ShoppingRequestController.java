package com.basketbird.backend.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
import com.basketbird.backend.model.ListWrap;
import com.basketbird.backend.model.RequestWrap;
import com.basketbird.backend.model.RequestWrapper;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.type.RequestStatus;
import com.basketbird.backend.repository.ShoppingRequestRepository;
import com.basketbird.backend.service.ShoppingRequestService;


@RestController
@RequestMapping( value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
public class ShoppingRequestController {

	@Autowired
	private ShoppingRequestService shoppingRequestService;

	@Autowired
	public ShoppingRequestRepository shoppingRequestRepository;
	
	
	@RequestMapping( method = GET, value = "/request/{requestId}" )
	public ResponseEntity<?> loadById( @PathVariable String requestId ) {
		RequestWrap result= this.shoppingRequestService.loadRequestById( requestId );
		try{
			return new ResponseEntity<RequestWrap>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(method = GET, value = "/request/my/all")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getMyActiveRequest() {
		try{
			List<ShoppingRequest> result = this.shoppingRequestService.loadAllRequestByMaker();
			return new ResponseEntity<List<ShoppingRequest>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(method = GET, value = "/request/my/active")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getMyActiveRequest(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		try{
			List<RequestWrap> result = this.shoppingRequestService.loadActiveRequestByMaker(pageNumber);
			return new ResponseEntity<List<RequestWrap>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = GET, value = "/request/my/archived")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getMyArchivedRequest(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		try{
			List<RequestWrap> result = this.shoppingRequestService.loadArchivedRequestByMaker(pageNumber);
			return new ResponseEntity<List<RequestWrap>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = GET, value = "/request/my/delivered")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getMyDeliveredRequest(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		try{
			List<RequestWrap> result = this.shoppingRequestService.loadDeliveredRequestByMaker(pageNumber);
			return new ResponseEntity<List<RequestWrap>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = GET, value = "/request/my/confirmed")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getMyConfirmedRequest(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		try{
			List<RequestWrap> result = this.shoppingRequestService.loadConfirmedRequestByMaker(pageNumber);
			return new ResponseEntity<List<RequestWrap>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = GET, value = "/request/active")
	public ResponseEntity<?> getByStatus(@RequestParam(name = "p", defaultValue = "1") int pageNumber, @RequestParam("requestStatus") RequestStatus requestStatus,@RequestParam(value="country", required=false) String country,@RequestParam(value="city", required=false) String city) {
		try{
			List<RequestWrap> result = this.shoppingRequestService.loadByStatus(pageNumber,requestStatus,country,city);
			return new ResponseEntity<List<RequestWrap>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = GET, value = "/request/active/all")
	public ResponseEntity<?> getByStatusAll(@RequestParam("requestStatus") RequestStatus requestStatus,@RequestParam(value="country", required=false) String country,@RequestParam(value="city", required=false) String city) {
		try{
			List<RequestWrap> result = this.shoppingRequestService.loadByStatusAll(requestStatus,country,city);
			return new ResponseEntity<List<RequestWrap>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = GET, value = "/request/by/time")
	public ResponseEntity<?> getByTime(@RequestParam(name = "p", defaultValue = "1") int pageNumber, @RequestParam("toTime") String deliveryTime,@RequestParam(value="country", required=false) String country,@RequestParam(value="city", required=false) String city) {
		try{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"); 
			LocalDateTime date = LocalDateTime.parse(deliveryTime, formatter);
			Instant instant = date.toInstant(ZoneOffset.UTC);
			//Instant instant = Instant.parse(deliveryTime);
			LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
			List<RequestWrap> result = this.shoppingRequestService.loadByTime(pageNumber,dateTime,country,city);
			return new ResponseEntity<List<RequestWrap>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = GET, value = "/request/by/distance")
	public ResponseEntity<?> getByDistance(@RequestParam(name = "p", defaultValue = "1") int pageNumber, @RequestParam("lng") String longitude, @RequestParam("lat") String latitude, @RequestParam("dist") int distance) {
		try{
			List<RequestWrap> result = this.shoppingRequestService.loadRequestByDistance(pageNumber,longitude,latitude,distance);
			return new ResponseEntity<List<RequestWrap>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = GET, value = "/request/by/range")
	public ResponseEntity<?> getByRange(@RequestParam(name = "p", defaultValue = "1") int pageNumber, @RequestParam("min") String min, @RequestParam("max") String max,@RequestParam(value="country", required=false) String country,@RequestParam(value="city", required=false) String city) {
		try{
			List<RequestWrap> result = this.shoppingRequestService.loadRequestByRange(pageNumber,min,max,country,city);
			return new ResponseEntity<List<RequestWrap>>(result, HttpStatus.OK);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	

	@RequestMapping(value = "/request/save", method = POST)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> save(@RequestBody RequestWrapper request) {

		ShoppingList list = request.getShoppingList();
		ShoppingRequest req = request.getShoppingRequest();
		DeliveryAddress deliveryAddress= request.getDeliveryAddress();
		try{
			RequestWrap result = this.shoppingRequestService.saveRequestWrapper(req,list,deliveryAddress);
			return new ResponseEntity<RequestWrap>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@RequestMapping(value = "/request/update", method = POST)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> update(@RequestBody ShoppingRequest request) {

		try{
			ShoppingRequest result = this.shoppingRequestService.updateRequest(request);
			return new ResponseEntity<ShoppingRequest>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		
	}

	@RequestMapping( method = DELETE, value = "/request/remove/{requestId}" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> remove( @PathVariable String requestId ) {
		try{
			this.shoppingRequestService.removeRequest( requestId );
			return ResponseEntity.ok().build();
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping( method = POST, value = "/request/confirm/save" )
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> deliverConfirmSave(@RequestBody ShoppingRequest request) {

		try{
			ShoppingRequest result = this.shoppingRequestService.saveDeliverConfirm(request);
			return new ResponseEntity<ShoppingRequest>(result, HttpStatus.CREATED);
		}catch(HttpStatusCodeException exception) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@RequestMapping( method = GET, value = "/request/currency" )
    public List<ShoppingRequest> getCurrency( ) throws URISyntaxException{
        return this.shoppingRequestService.getCurrency();
    }
	
	
	@RequestMapping(method = GET, value = "/request/my/active/count")
	@PreAuthorize("hasRole('USER')")
	public long getMyActiveRequestCount() {
		return this.shoppingRequestService.loadActiveRequestByMakerTotal();
	}
	
	@RequestMapping(method = GET, value = "/request/my/archived/count")
	@PreAuthorize("hasRole('USER')")
	public long getMyArchivedRequestCount() {
		return this.shoppingRequestService.loadArchivedRequestByMakerTotal();
	}
	
	@RequestMapping(method = GET, value = "/request/my/delivered/count")
	@PreAuthorize("hasRole('USER')")
	public long getMyDeliveredRequestCount() {
		return this.shoppingRequestService.loadDeliveredRequestByMakerTotal();
	}
	
	@RequestMapping(method = GET, value = "/request/my/confirmed/count")
	@PreAuthorize("hasRole('USER')")
	public long getMyConfirmedRequestCount() {
		return this.shoppingRequestService.loadConfirmedRequestByMakerTotal();
	}
	
	
	@RequestMapping(method = GET, value = "/request/active/count")
	public long getByStatusCount(@RequestParam("requestStatus") RequestStatus requestStatus,@RequestParam(value="country", required=false) String country,@RequestParam(value="city", required=false) String city) {
		return this.shoppingRequestService.loadByStatusTotal(requestStatus,country,city);
	}

	@RequestMapping(method = GET, value = "/request/by/time/count")
	public long getByTimeCount(@RequestParam("toTime") String deliveryTime,@RequestParam(value="country", required=false) String country,@RequestParam(value="city", required=false) String city) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"); 
		LocalDateTime date = LocalDateTime.parse(deliveryTime, formatter);
		Instant instant = date.toInstant(ZoneOffset.UTC);
		//Instant instant = Instant.parse(deliveryTime);
		LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
		return this.shoppingRequestService.loadRequestByTimeTotal(dateTime,country,city);
	}

	@RequestMapping(method = GET, value = "/request/by/range/count")
	public long getByRangeCount(@RequestParam("min") String min, @RequestParam("max") String max,@RequestParam(value="country", required=false) String country,@RequestParam(value="city", required=false) String city) {
		return this.shoppingRequestService.loadRequestByRangeTotal(min,max,country,city);
	}

}
