package com.basketbird.backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basketbird.backend.model.User;
import com.basketbird.backend.model.UserAddress;
import com.basketbird.backend.model.UserInfo;


public interface UserAddressRepository extends BaseRepository<UserAddress,Long>{
	
	UserAddress findById(Long id);
	UserAddress findByMaker(User maker);
	UserAddress findByInfo(UserInfo info);

}
