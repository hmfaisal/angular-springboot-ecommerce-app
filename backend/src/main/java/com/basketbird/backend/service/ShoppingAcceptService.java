package com.basketbird.backend.service;

import java.util.List;

import com.basketbird.backend.model.RequestWrap;
import com.basketbird.backend.model.ShoppingRequest;

public interface ShoppingAcceptService {
	
	List<RequestWrap> loadActiveAcceptByMaker (int pageNumber);
	List<RequestWrap> loadArchivedAcceptByMaker (int pageNumber);
	
	ShoppingRequest saveAccept(ShoppingRequest request);
	ShoppingRequest removeAccept(ShoppingRequest request);
	ShoppingRequest saveDeliver(ShoppingRequest request);
	
	long loadActiveAcceptByMakerTotal();
	long loadArchivedAcceptByMakerTotal();
	
	//helper
	boolean canAccepterEdit(ShoppingRequest req);

}
