package com.basketbird.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoWrapper {
	
	private UserInfo userInfo;
	private UserAddress address;

}
