package com.basketbird.backend.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basketbird.backend.model.RequestWrap;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.User;
import com.basketbird.backend.model.type.RequestStatus;
import com.basketbird.backend.model.type.ShoppingStatus;
import com.basketbird.backend.repository.ShoppingListRepository;
import com.basketbird.backend.repository.ShoppingRequestRepository;
import com.basketbird.backend.service.ShoppingAcceptService;
import com.basketbird.backend.service.ShoppingListService;
import com.basketbird.backend.service.ShoppingProductService;
import com.basketbird.backend.service.HelperService;
import com.basketbird.backend.service.ListProductService;
import com.basketbird.backend.service.ShoppingRequestService;
import com.basketbird.backend.service.UserService;

@Service
@Transactional
public class ShoppingAcceptServiceImpl implements ShoppingAcceptService{
	
	@Autowired
	private ShoppingRequestService shoppingRequestService;
	
	@Autowired
	public ShoppingRequestRepository shoppingRequestRepository;
	
	@Autowired
	public ShoppingListRepository shoppingListRepository;
	
	@Autowired
	public ShoppingListService shoppingListService;
	
	@Autowired
	private ShoppingProductService shoppingProductService;
	
	@Autowired
	private HelperService helperService;
	
	@Autowired
    private UserService userService;
	
	private final static int PAGESIZE = 20;
	
	@Override
    @Transactional(readOnly = true)
    public List<RequestWrap> loadActiveAcceptByMaker(int pageNumber) throws AccessDeniedException {
		
		List<RequestWrap> result = new ArrayList<>();
		
		User acceptMaker = this.userService.currentUser();
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
        List<ShoppingRequest> req = shoppingRequestRepository.findByShopperOrderByDeliveryTimeAsc(request,acceptMaker);
        if(req!=null){
        	req.forEach(item->{
            	ShoppingStatus shopstatus = item.getShoppingStatus();
            	if(shopstatus==ShoppingStatus.ACCEPTED){
            		RequestWrap res = this.shoppingRequestService.returnRequestWrap(item);
                	result.add(res);
            	}
            	
            });
        }
        return result;
    }
	
	@Override
    @Transactional(readOnly = true)
    public List<RequestWrap> loadArchivedAcceptByMaker(int pageNumber) throws AccessDeniedException {
		
		List<RequestWrap> result = new ArrayList<>();
		
		User acceptMaker = this.userService.currentUser();
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
        List<ShoppingRequest> req = shoppingRequestRepository.findByShopperOrderByDeliveryTimeAsc(request,acceptMaker);
        if(req!=null){
        	req.forEach(item->{
            	ShoppingStatus shopstatus = item.getShoppingStatus();
            	if(shopstatus==ShoppingStatus.DELIVERED){
            		RequestWrap res = this.shoppingRequestService.returnRequestWrap(item);
                	result.add(res);
            	}
            	
            });
        }
        
        return result;
    }
	
	public ShoppingRequest saveAccept(ShoppingRequest request) {

		ShoppingRequest req = this.helperService.getShoppingRequest(request);
		if(req!=null && canAccepterSave(req)){
			req.setShopper(this.userService.currentUser());
			req.setShoppingStatus(ShoppingStatus.ACCEPTED);
			return this.shoppingRequestService.saveRequest(req);	
		}else{
			return null;
		}
	}
	
	public ShoppingRequest removeAccept(ShoppingRequest request) {

		ShoppingRequest req = this.helperService.getShoppingRequest(request);
		if(req!=null && canAccepterEdit(req)){
			req.setShoppingStatus(null);
			return this.shoppingRequestService.saveRequest(req);	
		}else{
			return null;
		}
	}
	
	public ShoppingRequest saveDeliver(ShoppingRequest request) {

		ShoppingRequest req = this.helperService.getShoppingRequest(request);
		if(req!=null && canAccepterEdit(req)){
			req.setShoppingStatus(ShoppingStatus.DELIVERED);
			return this.shoppingRequestService.saveRequest(req);	
		}else{
			return null;
		}	
	}
	
	private boolean canAccepterSave(ShoppingRequest request){
		//ShoppingRequest request = this.shoppingRequestRepository.findOne(req.getId());
		User currentUser = this.userService.currentUser();
		if(request.getShopper()==null && currentUser != request.getRequester()){
			RequestStatus reqstatus= request.getRequestStatus();
			ShoppingStatus shopstatus = request.getShoppingStatus();
			LocalDateTime deliveryTime = request.getDeliveryTime();
			long duration = this.shoppingRequestService.durationInMinute(deliveryTime);
			if(shopstatus==null && reqstatus==RequestStatus.REQUESTED && duration>0){
				return true;
			}
		}
		return false;
	}
	
	public boolean canAccepterEdit(ShoppingRequest request){
		//ShoppingRequest request = this.shoppingRequestRepository.findOne(req.getId());
		User currentUser = this.userService.currentUser();
		if(currentUser == request.getShopper() && currentUser != request.getRequester()){
			ShoppingStatus shopstatus = request.getShoppingStatus();
			if(shopstatus==ShoppingStatus.ACCEPTED){
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public long loadActiveAcceptByMakerTotal() throws AccessDeniedException {
		User shopper = this.userService.currentUser();
		long count = 0;
		List<ShoppingRequest> req = this.shoppingRequestRepository.findByShopper(shopper);

		for(ShoppingRequest item:req){
			ShoppingStatus shopstatus = item.getShoppingStatus();
        	if(shopstatus==ShoppingStatus.ACCEPTED){
        		count++;
        	}
		}
		return count;
	}
	
	@Override
	@Transactional(readOnly = true)
	public long loadArchivedAcceptByMakerTotal() throws AccessDeniedException {
		User shopper = this.userService.currentUser();
		long count = 0;
		List<ShoppingRequest> req = this.shoppingRequestRepository.findByShopper(shopper);
		for(ShoppingRequest item:req){
			ShoppingStatus shopstatus = item.getShoppingStatus();
        	if(shopstatus==ShoppingStatus.DELIVERED){
        		count++;
        	}
		}
		return count;
	}

}
