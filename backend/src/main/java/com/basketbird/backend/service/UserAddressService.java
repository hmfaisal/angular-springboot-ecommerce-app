package com.basketbird.backend.service;

import com.basketbird.backend.model.UserAddress;
import com.basketbird.backend.model.UserInfo;

public interface UserAddressService {
	
	UserAddress loadAddressById (String id);
	UserAddress loadByMaker ();
	UserAddress loadUserAddressByInfo(UserInfo info);
	UserAddress saveUserAddress (UserInfo info,UserAddress address);
	UserAddress updateUserAddress (UserAddress address);
}
