package com.basketbird.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.basketbird.backend.model.User;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserRepository extends BaseRepository<User, Long> {
	
	User findById(Long id);
	//@Query("select u FROM User u WHERE u.username=:username")
	User findByUsername(String username);
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
    User findByProviderIdAndProviderUserId(String providerId, String providerUserId);
    
    @Query("select new map(u.id as id,u.username as username, u.email as email) FROM User u WHERE u.id=:id")
    List<User> getUserByUserId(@Param("id")long id);
}

