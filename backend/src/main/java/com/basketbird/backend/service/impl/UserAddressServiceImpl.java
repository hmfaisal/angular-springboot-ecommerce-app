package com.basketbird.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basketbird.backend.model.DeliveryAddress;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.User;
import com.basketbird.backend.model.UserAddress;
import com.basketbird.backend.model.UserInfo;
import com.basketbird.backend.repository.UserAddressRepository;
import com.basketbird.backend.repository.UserInfoRepository;
import com.basketbird.backend.service.HelperService;
import com.basketbird.backend.service.UserAddressService;
import com.basketbird.backend.service.UserService;

@Service
@Transactional
public class UserAddressServiceImpl implements UserAddressService {
	
	@Autowired
	public UserAddressRepository userAddressRepository;
	
	@Autowired
	public UserInfoRepository userInfoRepository;
	
	@Autowired
	private HelperService helperService;
	
	@Autowired
    private UserService userService;
	
	@Override
	@Transactional(readOnly = true)
	public UserAddress loadAddressById (String id) throws AccessDeniedException{
		UserAddress address = this.userAddressRepository.findById(Long.valueOf(id));
		return address;
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserAddress loadByMaker () throws AccessDeniedException{
		User currentUser = this.userService.currentUser();
		UserAddress address = this.userAddressRepository.findByMaker(currentUser);
		return address;
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserAddress loadUserAddressByInfo(UserInfo info) throws AccessDeniedException {

		UserAddress address = this.userAddressRepository.findByInfo(info);
		return address;
	}
	
	@Override
	public UserAddress saveUserAddress (UserInfo in,UserAddress adr) throws AccessDeniedException{
		
		UserInfo info = this.helperService.getUserInfo(in);
		UserAddress address = this.helperService.getUserAddress(adr);
		if(address==null){
			adr.setMaker(this.userService.currentUser());
			adr.setInfo(info);
			return this.save(adr);
		}
		return address;
	}
	
	@Override
	public UserAddress updateUserAddress (UserAddress adr) throws AccessDeniedException{
		
		UserAddress address = this.helperService.getUserAddress(adr);
		User currentUser = this.userService.currentUser();
		if(address!=null && currentUser==address.getMaker()){
			adr.setInfo(address.getInfo());
			return this.save(adr);
		}
		return null;
	}
	
	public UserAddress save(UserAddress address){
		return this.userAddressRepository.save(address);
	}

}
