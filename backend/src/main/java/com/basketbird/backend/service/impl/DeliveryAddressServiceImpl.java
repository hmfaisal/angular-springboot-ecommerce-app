package com.basketbird.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basketbird.backend.model.DeliveryAddress;
import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.User;
import com.basketbird.backend.model.type.RequestStatus;
import com.basketbird.backend.model.type.ShoppingStatus;
import com.basketbird.backend.repository.DeliveryAddressRepository;
import com.basketbird.backend.repository.ShoppingRequestRepository;
import com.basketbird.backend.service.DeliveryAddressService;
import com.basketbird.backend.service.HelperService;
import com.basketbird.backend.service.UserService;

@Service
@Transactional
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
	
	@Autowired
	public DeliveryAddressRepository deliveryAddressRepository;
	
	@Autowired
	public ShoppingRequestRepository shoppingRequestRepository;
	
	@Autowired
	private HelperService helperService;
	
	@Autowired
    private UserService userService;
	
	@Override
	@Transactional(readOnly = true)
	public DeliveryAddress loadAddressById(String id) throws AccessDeniedException {
		DeliveryAddress address = this.deliveryAddressRepository.findById(Long.valueOf(id));
		return address;
	}
	
	@Override
	@Transactional(readOnly = true)
	public DeliveryAddress loadDelAddressByRequest(ShoppingRequest request) throws AccessDeniedException {

		//List<ShoppingProductWrap> result = new ArrayList<>();
		DeliveryAddress adr = this.deliveryAddressRepository.findByRequest(request);
		return adr;
	}
	
	@Override
	public DeliveryAddress saveDeliveryAddress(DeliveryAddress address){
		User currentUser = this.userService.currentUser();
		DeliveryAddress adr = this.helperService.getDeliveryAddress(address);
		if(adr!=null){
			ShoppingRequest req = adr.getRequest();
			if(currentUser == adr.getMaker()){
				if(this.canEdit(req)){
					address.setMaker(currentUser);
					address.setRequest(adr.getRequest());
					return this.save(address);
				}
			}
		}
		return null;
	}
	
	//Saving Product when creating request first time
	@Override
	public DeliveryAddress saveRequestAddress(DeliveryAddress address, ShoppingRequest request) {
		User currentUser = this.userService.currentUser();
		//Address address = new Address();
		address.setMaker(currentUser);
		address.setRequest(request);
		return this.save(address);
	}
	
	@Override
	public DeliveryAddress save(DeliveryAddress address){
		return this.deliveryAddressRepository.save(address);
	}
	
	private boolean canEdit(ShoppingRequest request) {

		User currentUser = this.userService.currentUser();
		if (request != null ) {
			ShoppingRequest req = this.shoppingRequestRepository.findOne(request.getId());
			if (req != null && currentUser == req.getRequester() && currentUser!=req.getShopper()) {
				if ( req.getRequestStatus()==RequestStatus.REQUESTED && req.getShoppingStatus()!=ShoppingStatus.DELIVERED) {
					return true;
				}
			}
		}
		return false;
	}

}
