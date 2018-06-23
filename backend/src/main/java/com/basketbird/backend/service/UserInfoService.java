package com.basketbird.backend.service;

import com.basketbird.backend.model.UserInfo;

public interface UserInfoService {
	
	UserInfo loadUserInfoById(String id);
	UserInfo loadInfoByMaker();
	UserInfo saveInfo(UserInfo info);

}
