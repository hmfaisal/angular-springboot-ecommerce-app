package com.basketbird.backend.repository;

import org.springframework.data.domain.Pageable;

import com.basketbird.backend.model.User;
import com.basketbird.backend.model.UserInfo;

public interface UserInfoRepository extends BaseRepository<UserInfo,Long> {
	
	UserInfo findById(Long id);
	UserInfo findByUser(User user);

}
