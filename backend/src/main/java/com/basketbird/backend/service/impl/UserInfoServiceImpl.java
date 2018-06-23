package com.basketbird.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basketbird.backend.model.User;
import com.basketbird.backend.model.UserInfo;
import com.basketbird.backend.repository.UserInfoRepository;
import com.basketbird.backend.service.HelperService;
import com.basketbird.backend.service.UserInfoService;
import com.basketbird.backend.service.UserService;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
    private UserInfoRepository userInfoRepository;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private HelperService helperService;

	
	@Override
    @Transactional(readOnly = true)
	public UserInfo loadUserInfoById(String id){
		UserInfo info = this.userInfoRepository.findById(Long.valueOf(id));
		return info;	
	}
	
	@Override
    @Transactional(readOnly = true)
	public UserInfo loadInfoByMaker(){
		User currentUser = this.userService.currentUser();
		UserInfo info = this.userInfoRepository.findByUser(currentUser);
		return info;	
	}
	
	@Override
	public UserInfo saveInfo(UserInfo info){
		
		UserInfo uinfo = this.helperService.getUserInfo(info);
		User currentUser = this.userService.currentUser();
		
		if(uinfo==null){
			info.setUser(currentUser);
			return this.save(info);
		}else if(uinfo!=null && currentUser==uinfo.getUser()){
			info.setAddress(uinfo.getAddress());
			info.setUser(uinfo.getUser());
			return this.save(info);
		}
		return null;
		
	}
	
	public UserInfo save(UserInfo info){
		return this.userInfoRepository.save(info);
	}
	

}
