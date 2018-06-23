package com.basketbird.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import com.basketbird.backend.model.DeliveryAddress;
import com.basketbird.backend.model.RequestWrap;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.type.RequestStatus;

/**
 * Created by hasan.mahmud on 2017-12-11.
 */

public interface ShoppingRequestService {
	
	RequestWrap loadRequestById(String id);
	List<ShoppingRequest> loadAllRequestByMaker ();
	List<RequestWrap> loadActiveRequestByMaker (int pageNumber);
	List<RequestWrap> loadArchivedRequestByMaker (int pageNumber);
	List<RequestWrap> loadDeliveredRequestByMaker (int pageNumber);
	List<RequestWrap> loadConfirmedRequestByMaker (int pageNumber);
	List<RequestWrap> loadByStatus (int pageNumber, RequestStatus status, String country, String city);
	List<RequestWrap> loadByStatusAll ( RequestStatus status,String country, String city);
	List<RequestWrap> loadByTime(int pageNumber,LocalDateTime deliveryTime,String country, String city);
	List<RequestWrap> loadRequestByDistance(int pageNumber,String longitude, String latitude, int distance);
	List<RequestWrap> loadRequestByRange(int pageNumber,String min, String max,String country, String city);
	
	RequestWrap saveRequestWrapper(ShoppingRequest request, ShoppingList list, DeliveryAddress deliveryAddress);
	ShoppingRequest saveRequest(ShoppingRequest request);
	ShoppingRequest updateRequest(ShoppingRequest request);
	void removeRequest(String requestId);
	ShoppingRequest saveDeliverConfirm(ShoppingRequest request);
	
	long loadActiveRequestByMakerTotal();
	long loadArchivedRequestByMakerTotal();
	long loadDeliveredRequestByMakerTotal();
	long loadConfirmedRequestByMakerTotal();
	long loadByStatusTotal(RequestStatus status,String country, String city);
	long loadRequestByTimeTotal(LocalDateTime deliveryTime,String country, String city);
	long loadRequestByRangeTotal(String min, String max,String country, String city);
	List<ShoppingRequest> getCurrency( );
	
	//helper
	RequestWrap returnRequestWrap( ShoppingRequest request);
	long durationInMinute(LocalDateTime deliveryTime);
	boolean canRequesterEdit(ShoppingRequest req);
}
