package com.basketbird.backend.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;
import org.springframework.social.security.SocialAuthenticationToken;

import com.basketbird.backend.model.User;

public class UserAuthenticationUserIdSource implements UserIdSource {

	@Override
	public String getUserId() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = null;
		if (authentication instanceof UserAuthentication) {
			user = (User) authentication.getPrincipal();
		}

		if (user == null) {
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		}
		return user.getUserId();
	}
}
