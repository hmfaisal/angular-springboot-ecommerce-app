package com.basketbird.backend.service.impl;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ShoppingProductWrap;
import com.basketbird.backend.model.DeliveryAddress;
import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.User;
import com.basketbird.backend.model.type.RequestStatus;
import com.basketbird.backend.model.type.ShoppingStatus;
import com.basketbird.backend.repository.ListProductRepository;
import com.basketbird.backend.repository.ShoppingListRepository;
import com.basketbird.backend.repository.ShoppingRequestRepository;
import com.basketbird.backend.service.ShoppingListService;
import com.basketbird.backend.service.ShoppingProductService;
import com.basketbird.backend.service.DeliveryAddressService;
import com.basketbird.backend.service.HelperService;
import com.basketbird.backend.service.ShoppingAcceptService;
import com.basketbird.backend.service.ShoppingRequestService;
import com.basketbird.backend.service.UserService;

@Service
@Transactional
public class ShoppingRequestServiceImpl implements ShoppingRequestService{
	
	@Autowired
	public ShoppingRequestRepository shoppingRequestRepository;
	
	@Autowired
	public ShoppingListRepository shoppingListRepository;
	
	@Autowired
	public ListProductRepository listProductRepository;
	
	@Autowired
	public ShoppingListService shoppingListService;
	
	@Autowired
	private ShoppingAcceptService shoppingAcceptService;
	
	@Autowired
	private ShoppingProductService shoppingProductService;
	
	@Autowired
	private DeliveryAddressService addressService;
	
	@Autowired
	private HelperService helperService;
	
	@Autowired
    private UserService userService;
	
	private final static int PAGESIZE = 20;
	
    @Override
	@Transactional(readOnly = true)
    public RequestWrap loadRequestById( String id ) throws AccessDeniedException {

    	ShoppingRequest request = this.shoppingRequestRepository.findById( Long.valueOf(id) );
    	RequestWrap result = this.returnRequestWrap(request);
        return result;
    }

	@Override
    @Transactional(readOnly = true)
    public List<ShoppingRequest> loadAllRequestByMaker() throws AccessDeniedException {
		
		
	User requestMaker = this.userService.currentUser();
        List<ShoppingRequest> req = this.shoppingRequestRepository.findByRequester(requestMaker);
        
        return req;
    }
	
	@Override
    @Transactional(readOnly = true)
    public List<RequestWrap> loadActiveRequestByMaker(int pageNumber) throws AccessDeniedException {
		
		List<RequestWrap> result = new ArrayList<>();
		
		User requestMaker = this.userService.currentUser();
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
        List<ShoppingRequest> req = this.shoppingRequestRepository.findByRequesterOrderByDeliveryTimeAsc(request,requestMaker);
        if(req!=null){
        	req.forEach(item->{
            	RequestStatus reqstatus= item.getRequestStatus();
            	LocalDateTime deliveryTime = item.getDeliveryTime();
            	long duration = this.durationInMinute(deliveryTime);
            	if(reqstatus==RequestStatus.REQUESTED && duration>0){
            		RequestWrap res = this.returnRequestWrap(item);
                	result.add(res);
            	}
            });
        }
        return result;
    }
	
	@Override
    @Transactional(readOnly = true)
    public List<RequestWrap> loadArchivedRequestByMaker(int pageNumber) throws AccessDeniedException {
		
		List<RequestWrap> result = new ArrayList<>();
		
		User requestMaker = this.userService.currentUser();
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
        List<ShoppingRequest> req = this.shoppingRequestRepository.findByRequesterOrderByDeliveryTimeAsc(request,requestMaker);
        if(req!=null){
        	req.forEach(item->{
            	RequestStatus reqstatus= item.getRequestStatus();
            	LocalDateTime deliveryTime = item.getDeliveryTime();
            	long duration = this.durationInMinute(deliveryTime);
            	if(reqstatus==RequestStatus.REQUESTED && duration<=0){
            		RequestWrap res = this.returnRequestWrap(item);
                	result.add(res);
            	}
            });
        }
        return result;
    }
	
	@Override
    @Transactional(readOnly = true)
    public List<RequestWrap> loadDeliveredRequestByMaker(int pageNumber) throws AccessDeniedException {
		
		List<RequestWrap> result = new ArrayList<>();
		
		User requestMaker = this.userService.currentUser();
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
        List<ShoppingRequest> req = this.shoppingRequestRepository.findByRequesterOrderByDeliveryTimeAsc(request,requestMaker);
        if(req!=null){
        	req.forEach(item->{
            	RequestStatus reqstatus= item.getRequestStatus();
            	ShoppingStatus shopstatus = item.getShoppingStatus();
            	if(reqstatus==RequestStatus.REQUESTED && shopstatus==ShoppingStatus.DELIVERED){
            		RequestWrap res = this.returnRequestWrap(item);
                	result.add(res);
            	}
            });
        }
        
        return result;
    }
	
	@Override
    @Transactional(readOnly = true)
    public List<RequestWrap> loadConfirmedRequestByMaker(int pageNumber) throws AccessDeniedException {
		
		List<RequestWrap> result = new ArrayList<>();
		
		User requestMaker = this.userService.currentUser();
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
        List<ShoppingRequest> req = this.shoppingRequestRepository.findByRequesterOrderByDeliveryTimeAsc(request,requestMaker);
        if(req!=null){
        	req.forEach(item->{
            	RequestStatus reqstatus= item.getRequestStatus();
            	if(reqstatus==RequestStatus.CONFIRMED){
            		RequestWrap res = this.returnRequestWrap(item);
                	result.add(res);
            	}
            });
        }
        
        return result;
    }
	
	
	@Override
    @Transactional(readOnly = true)
    public List<RequestWrap> loadByStatus(int pageNumber, RequestStatus status,String country, String city) throws AccessDeniedException {
		
		List<RequestWrap> result = new ArrayList<>();
		
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
        List<ShoppingRequest> req = this.shoppingRequestRepository.findByRequestStatusOrderByDeliveryTimeAsc(request,status,country,city);
        if(req!=null){
        	req.forEach(item->{
            	RequestStatus reqstatus= item.getRequestStatus();
		ShoppingStatus shopstatus = item.getShoppingStatus();
            	LocalDateTime deliveryTime = item.getDeliveryTime();
            	long duration = this.durationInMinute(deliveryTime);
            	if(reqstatus==RequestStatus.REQUESTED && shopstatus==null && duration>0){
            		RequestWrap res = this.returnRequestWrap(item);
                	result.add(res);
            	}
            });
        }
        return result;
    }
	
	@Override
    @Transactional(readOnly = true)
    public List<RequestWrap> loadByStatusAll(RequestStatus status,String country, String city) throws AccessDeniedException {
		
		List<RequestWrap> result = new ArrayList<>();
		
        List<ShoppingRequest> req = this.shoppingRequestRepository.findByRequestStatusOrderByDeliveryTimeDesc(status,country,city);
        if(req!=null){
        	req.forEach(item->{
            	RequestStatus reqstatus= item.getRequestStatus();
            	LocalDateTime deliveryTime = item.getDeliveryTime();
            	long duration = this.durationInMinute(deliveryTime);
            	if(reqstatus==RequestStatus.REQUESTED && duration>0){
            		RequestWrap res = this.returnRequestWrap(item);
                	result.add(res);
            	}
            });
        }
        return result;
    }
	
	@Override
    @Transactional(readOnly = true)
	public List<RequestWrap> loadByTime(int pageNumber,LocalDateTime delTime,String country, String city) throws AccessDeniedException{
		
		List<RequestWrap> result = new ArrayList<>();
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
        
        LocalDateTime fromTime = LocalDateTime.now(Clock.systemDefaultZone());
        LocalDateTime toTime = delTime.atZone(ZoneId.systemDefault()).toLocalDateTime();
        List<ShoppingRequest> req = this.shoppingRequestRepository.findByDeliveryTimeRange(request,fromTime,toTime,country,city);
        if(req!=null){
        	req.forEach(item->{
            	RequestStatus reqstatus= item.getRequestStatus();
		ShoppingStatus shopstatus = item.getShoppingStatus();
            	LocalDateTime deliveryTime = item.getDeliveryTime();
            	long duration = this.durationInMinute(deliveryTime);
            	if(reqstatus==RequestStatus.REQUESTED && shopstatus==null && duration>0){
            		RequestWrap res = this.returnRequestWrap(item);
                	result.add(res);
            	}
            });
        }
        return result;
	}
	
	//TODO
	@Override
    @Transactional(readOnly = true)
    public List<RequestWrap> loadRequestByDistance(int pageNumber,String longitude, String latitude, int distance) throws AccessDeniedException {
		
		List<RequestWrap> result = new ArrayList<>();
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
        List<ShoppingRequest> req = this.shoppingRequestRepository.requestByDistance(request,Double.valueOf(longitude),Double.valueOf(latitude),Double.valueOf(distance));
        if(req!=null){
        	req.forEach(item->{
            	RequestStatus reqstatus= item.getRequestStatus();
            	LocalDateTime deliveryTime = item.getDeliveryTime();
            	long duration = this.durationInMinute(deliveryTime);
            	if(reqstatus==RequestStatus.REQUESTED && duration>0){
            		RequestWrap res = this.returnRequestWrap(item);
                	result.add(res);
            	}
            });
        }
        return result;
    }
	
	@Override
    @Transactional(readOnly = true)
    public List<RequestWrap> loadRequestByRange(int pageNumber,String min, String max,String country, String city) throws AccessDeniedException {
		
		List<RequestWrap> result = new ArrayList<>();
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
        List<ShoppingRequest> req = this.shoppingRequestRepository.findByRange(request,Double.valueOf(min),Double.valueOf(max),country,city);
        if(req!=null){
        	req.forEach(item->{
            	RequestStatus reqstatus= item.getRequestStatus();
		ShoppingStatus shopstatus = item.getShoppingStatus();
            	LocalDateTime deliveryTime = item.getDeliveryTime();
            	long duration = this.durationInMinute(deliveryTime);
            	if(reqstatus==RequestStatus.REQUESTED && shopstatus==null && duration>0){
            		RequestWrap res = this.returnRequestWrap(item);
                	result.add(res);
            	}
            });
        }
        return result;
    }
	
	
	public RequestWrap saveRequestWrapper(ShoppingRequest request, ShoppingList list,DeliveryAddress deliveryAddress) {
		RequestWrap result = new RequestWrap();
				
		ShoppingRequest req = this.helperService.getShoppingRequest(request);
		ShoppingList li = this.helperService.getShoppingList(list);
		//Address address = this.helperService.getAddress(deliveryAddress);
		LocalDateTime deliveryTime = this.getDelTime(request);
		long duration = this.durationInMinute(deliveryTime);
		
		if(req==null && li!=null && deliveryAddress != null && duration>0){
			User currentUser = this.userService.currentUser();
			User listMaker = li.getMaker();
			List<ListProduct> lproduct= this.listProductRepository.findByList(li);
			if(currentUser==listMaker && (lproduct != null && !lproduct.isEmpty())){
				request.setRequestStatus(RequestStatus.REQUESTED);
				request.setRequester(currentUser);
				request.setRequestName(li.getListName());
				request.setDeliveryTime(deliveryTime);
				request.setShoppingStatus(null);
				request.setShopper(null);
				request.setActualTotalPrice(request.getActualTotalPrice());
				request.setActualTotalUnit(request.getActualTotalUnit());
				ShoppingRequest reqresult = this.saveRequest(request);
				lproduct.forEach(item->{
					this.shoppingProductService.saveRequestProduct(item,reqresult);
				});	
				this.addressService.saveRequestAddress(deliveryAddress,reqresult);
				result = this.returnRequestWrap(reqresult);
			}	
		}
		return result;	
	}
	
	
	@Override
	public ShoppingRequest saveRequest(ShoppingRequest request) {	
			return this.shoppingRequestRepository.save(request);
	}
	
	@Override
	public ShoppingRequest updateRequest(ShoppingRequest request) {
		ShoppingRequest req = this.helperService.getShoppingRequest(request);
		if(req!=null && this.canRequesterEdit(req)){
			LocalDateTime deliveryTime = request.getDeliveryTime();
        	long duration = this.durationInMinute(deliveryTime);
        	if(duration>0){
        		request.setDeliveryTime(deliveryTime);
        	}else{
        		request.setDeliveryTime(req.getDeliveryTime());
        	}
			request.setShoppingProduct(req.getShoppingProduct());
			request.setShopper(req.getShopper());
			request.setDeliveryaddress(req.getDeliveryaddress());
			request.setShoppingStatus(req.getShoppingStatus());
			request.setActualTotalUnit(req.getActualTotalUnit());
			request.setActualTotalPrice(req.getActualTotalPrice());
			request.setRequester(this.userService.currentUser());
			request.setRequestStatus(req.getRequestStatus());
			return this.shoppingRequestRepository.save(request);
		}else if(this.shoppingAcceptService.canAccepterEdit(req)){
			//request=request.shopperData(req);
			request.setRequester(req.getRequester());
			request.setRequestStatus(req.getRequestStatus());
			request.setShoppingProduct(req.getShoppingProduct());
			request.setEstimateTotalUnit(req.getEstimateTotalUnit());
			request.setEstimatedTotalPrice(req.getEstimatedTotalPrice());
			request.setDeliveryTime(req.getDeliveryTime());
			request.setDeliveryaddress(req.getDeliveryaddress());
			request.setCurrency(req.getCurrency());
			request.setMarketName(req.getMarketName());
			return this. shoppingRequestRepository.save(request);
		}
		return null;	
	}
	
	@Override
	public void removeRequest(String requestId) {
		ShoppingRequest request = shoppingRequestRepository.findOne(Long.valueOf(requestId));
		if(request!=null && this.canRequesterRemove(request)){
			shoppingRequestRepository.delete(Long.valueOf(requestId));
		}
	}
	
	public ShoppingRequest saveDeliverConfirm(ShoppingRequest request) {

		ShoppingRequest req = this.helperService.getShoppingRequest(request);
		if(req!=null && this.canRequesterConfirm(req)){
			req.setRequestStatus(RequestStatus.CONFIRMED);
			return this.saveRequest(req);	
		}else{
			return null;
		}
	}
	
	private LocalDateTime getDelTime(ShoppingRequest request){
		LocalDateTime deliveryTime = request.getDeliveryTime();
		if(deliveryTime==null){
			deliveryTime = LocalDateTime.now(ZoneOffset.UTC).plusHours(4);
			return deliveryTime;
		}else{
			return deliveryTime;
		}
	}
	
	public long durationInMinute(LocalDateTime deliveryTime){
		LocalDateTime currentDateTime = LocalDateTime.now(Clock.systemDefaultZone());
		LocalDateTime delTime = deliveryTime.atZone(ZoneId.systemDefault()).toLocalDateTime();
		long duration = Duration.between(currentDateTime, delTime).toMinutes();
		return duration;
	}
	
	
	public boolean canRequesterEdit(ShoppingRequest request){
		//ShoppingRequest request = this.shoppingRequestRepository.findOne(req.getId());
		User currentUser = this.userService.currentUser();
		if(currentUser == request.getRequester() && currentUser != request.getShopper()){
			RequestStatus reqstatus= request.getRequestStatus();
			ShoppingStatus shopstatus = request.getShoppingStatus();
			if(reqstatus==RequestStatus.REQUESTED){
				return true;
				/*
				if(shopstatus==null || shopstatus!=ShoppingStatus.ACCEPTED){
					return true;
				}
				*/
			}
		}
		return false;
	}

	private boolean canRequesterConfirm(ShoppingRequest request){
		//ShoppingRequest request = this.shoppingRequestRepository.findOne(req.getId());
		User currentUser = this.userService.currentUser();
		if(currentUser == request.getRequester() && currentUser != request.getShopper()){
			RequestStatus reqstatus= request.getRequestStatus();
			ShoppingStatus shopstatus = request.getShoppingStatus();
			if(reqstatus==RequestStatus.REQUESTED){
				if(shopstatus!=null ){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean canRequesterRemove(ShoppingRequest request){
		//ShoppingRequest request = this.shoppingRequestRepository.findOne(req.getId());
		User currentUser = this.userService.currentUser();
		if(request!=null && currentUser == request.getRequester() && currentUser != request.getShopper()){
			RequestStatus reqstatus= request.getRequestStatus();
			ShoppingStatus shopstatus = request.getShoppingStatus();
			if(reqstatus==RequestStatus.REQUESTED){
				if(shopstatus==null){
					return true;
				}
			}
		}
		return false;
	}
	
	
	public RequestWrap returnRequestWrap( ShoppingRequest req) throws AccessDeniedException {
		//ShoppingRequest req = this.shoppingRequestRepository.findOne(request.getId());
		//List<ShoppingProductWrap> sp= null;
		List<ShoppingProduct> sp= null;
		DeliveryAddress address = null;
		if(req!=null){
			sp= this.shoppingProductService.loadProductByRequest(req);
			address= this.addressService.loadDelAddressByRequest(req);
		}
		
		RequestWrap result = new RequestWrap();
		result.setShoppingRequest(req);
		result.setDeliveryAddress(address);
		result.setShoppingProduct(sp);
        return result;
    }

	
	@Override
	@Transactional(readOnly = true)
	public long loadActiveRequestByMakerTotal() throws AccessDeniedException {
		User requester = this.userService.currentUser();
		long count = 0;
		List<ShoppingRequest> req = this.shoppingRequestRepository.findByRequester(requester);
		for(ShoppingRequest item:req){
			RequestStatus reqstatus= item.getRequestStatus();
        	LocalDateTime deliveryTime = item.getDeliveryTime();
        	long duration = this.durationInMinute(deliveryTime);
        	if(reqstatus==RequestStatus.REQUESTED && duration>0){
        		count++;
        	}
		}
		//Long result = this.shoppingRequestRepository.countByRequester(requester);
		return count;
	}
	
	@Override
	@Transactional(readOnly = true)
	public long loadArchivedRequestByMakerTotal() throws AccessDeniedException {
		User requester = this.userService.currentUser();
		long count = 0;
		List<ShoppingRequest> req = this.shoppingRequestRepository.findByRequester(requester);
		for(ShoppingRequest item:req){
			RequestStatus reqstatus= item.getRequestStatus();
        	LocalDateTime deliveryTime = item.getDeliveryTime();
        	long duration = this.durationInMinute(deliveryTime);
        	if(reqstatus==RequestStatus.REQUESTED && duration<=0){
        		count++;
        	}
		}
		return count;
	}
	
	@Override
	@Transactional(readOnly = true)
	public long loadDeliveredRequestByMakerTotal() throws AccessDeniedException {
		User requester = this.userService.currentUser();
		long count = 0;
		List<ShoppingRequest> req = this.shoppingRequestRepository.findByRequester(requester);
		for(ShoppingRequest item:req){
			RequestStatus reqstatus= item.getRequestStatus();
        	ShoppingStatus shopstatus = item.getShoppingStatus();
        	if(reqstatus==RequestStatus.REQUESTED && shopstatus==ShoppingStatus.DELIVERED){
        		count++;
        	}
		}
		return count;
	}
	
	@Override
	@Transactional(readOnly = true)
	public long loadConfirmedRequestByMakerTotal() throws AccessDeniedException {
		User requester = this.userService.currentUser();
		long count = 0;
		List<ShoppingRequest> req = this.shoppingRequestRepository.findByRequester(requester);
		for(ShoppingRequest item:req){
			RequestStatus reqstatus= item.getRequestStatus();
        	if(reqstatus==RequestStatus.CONFIRMED){
        		count++;
        	}
		}
		return count;
	}
	
	@Override
	@Transactional(readOnly = true)
	public long loadByStatusTotal(RequestStatus status,String country, String city) throws AccessDeniedException {
		//Long result = this.shoppingRequestRepository.countByRequestStatus(status);
		long count = 0;
		List<ShoppingRequest> req = this.shoppingRequestRepository.findByRequestStatus(status,country,city);
		for(ShoppingRequest item:req){
			RequestStatus reqstatus= item.getRequestStatus();
		ShoppingStatus shopstatus = item.getShoppingStatus();
        	LocalDateTime deliveryTime = item.getDeliveryTime();
        	long duration = this.durationInMinute(deliveryTime);
        	if(reqstatus==RequestStatus.REQUESTED && shopstatus==null && duration>0){
        		count++;
        	}
		}
		return count;
	}

	@Override
	@Transactional(readOnly = true)
	public long loadRequestByTimeTotal(LocalDateTime delTime,String country, String city) throws AccessDeniedException {
		long count = 0;
		LocalDateTime fromTime = LocalDateTime.now(Clock.systemDefaultZone());
        	LocalDateTime toTime = delTime.atZone(ZoneId.systemDefault()).toLocalDateTime();
		List<ShoppingRequest> req = this.shoppingRequestRepository.findByTimeRange(fromTime,toTime,country,city);
		for(ShoppingRequest item:req){
			RequestStatus reqstatus= item.getRequestStatus();
			ShoppingStatus shopstatus = item.getShoppingStatus();
        	LocalDateTime deliveryTime = item.getDeliveryTime();
        	long duration = this.durationInMinute(deliveryTime);
        	if(reqstatus==RequestStatus.REQUESTED && shopstatus==null && duration>0){
        		count++;
        	}
		}
		return count;
	}

	@Override
	@Transactional(readOnly = true)
	public long loadRequestByRangeTotal(String min, String max,String country, String city) throws AccessDeniedException {
		long count = 0;
		List<ShoppingRequest> req = this.shoppingRequestRepository.findByPriceRange(Double.valueOf(min),Double.valueOf(max),country,city);
		for(ShoppingRequest item:req){
			RequestStatus reqstatus= item.getRequestStatus();
		ShoppingStatus shopstatus = item.getShoppingStatus();
        	LocalDateTime deliveryTime = item.getDeliveryTime();
        	long duration = this.durationInMinute(deliveryTime);
        	if(reqstatus==RequestStatus.REQUESTED && shopstatus==null && duration>0){
        		count++;
        	}
		}
		return count;

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ShoppingRequest> getCurrency( ) throws AccessDeniedException {
		List<ShoppingRequest> result = shoppingRequestRepository.getCurrency();
		return result;
	}

}
