package com.basketbird.backend.service.impl;

import com.basketbird.backend.model.User;
import com.basketbird.backend.repository.UserRepository;
import com.basketbird.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hasan.mahmud on 2017-08-29.
 */

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    public void resetCredentials() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername( String username ) throws UsernameNotFoundException {
        User u = userRepository.findByUsername( username );
        return u;
    }
    
    @Override
	@Transactional(readOnly = true)
    public User loadUserByUserId( String id ) throws AccessDeniedException {
        User u = userRepository.findById( Long.valueOf(id) );
        return u;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<User> loadAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }
    
    @Override
	@Transactional(readOnly = true)
    public User loadUserByUserEmail(String email) {
		return userRepository.findByEmail(email);
	}
    
    @Override
	@Transactional(readOnly = true)
    public User loadUserByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}
    
    @Override
	@Transactional(readOnly = true)
	public User loadUserByConnectionKey(ConnectionKey connectionKey) {
		final User u = userRepository.findByProviderIdAndProviderUserId(connectionKey.getProviderId(), connectionKey.getProviderUserId());
		return checkUser(u);
	}

	@Override
	public void saveUser(User user) {
        userRepository.save(user);
	}
	
	private User checkUser(User user) {
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		detailsChecker.check(user);
		return user;
	}
	
	public User currentUser(){
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = (User) loadUserByUsername(username);
        return  user;
	}
	
	@Override
	@Transactional(readOnly = true)
    public List<User> getUserByUserId( String id ) throws AccessDeniedException {
		List<User> result = userRepository.getUserByUserId(Long.valueOf(id));
        return result;
    }

}
