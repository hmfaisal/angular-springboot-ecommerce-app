package com.basketbird.backend.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.security.SocialUserDetailsService;

import com.basketbird.backend.model.User;

/**
 * Created by hasan.mahmud on 2017-08-29.
 */

public interface UserService extends SocialUserDetailsService, UserDetailsService{
    void resetCredentials();
    User loadUserByUserId(String id);
    User loadUserByUsername(String username);
    User loadUserByUserEmail(String email);
    User loadUserByConfirmationToken(String confirmationToken);
    void saveUser(User user);
    User loadUserByConnectionKey(ConnectionKey connectionKey);
    List<User> loadAll ();
    User currentUser();
    List<User> getUserByUserId(String id);
}
