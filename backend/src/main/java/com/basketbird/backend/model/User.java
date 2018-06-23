package com.basketbird.backend.model;

import com.basketbird.backend.model.type.CurrencyType;
import com.basketbird.backend.model.type.Market;
import com.basketbird.backend.model.type.ProductType;
import com.basketbird.backend.model.type.UnitType;
import com.basketbird.backend.model.type.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hasan.mahmud on 2017-08-29.
 */

@Entity
@Table(name="api_user")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseModel implements UserDetails,SocialUserDetails{
	
	private static final long serialVersionUID = 3169817607433999180L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
	private Long id;
    
    @NotNull
    @Column(name = "username", unique = true)
    private String username;
    
    @Column(name="email", length=50, unique = true)
    private String email;
    
    @Column(name = "password")
    private String password;
   
    @Column(name = "confirmationToken")
	private String confirmationToken;
    
    @NotNull
	@JsonIgnore
	@Column(name = "providerid")
	private String providerId;
    
    @NotNull
	@JsonIgnore
	@Column(name = "provideruserid")
	private String providerUserId;
    
	@JsonIgnore
	@Column(name = "accesstoken")
	private String accessToken;
    
    @Transient
	private long expires;

    @Column(name = "accountexpired")
    private boolean accountExpired;

    @Column(name = "accountlocked")
    private boolean accountLocked;

    @Column(name = "credentialsexpired")
    private boolean credentialsExpired;

    @Column(name = "accountenabled")
    private boolean accountEnabled;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<UserAuthority> authorities;
    
    //@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	//private Set<ShoppingList> shoppingList = new HashSet<ShoppingList>();;
    
    @Override
	@JsonIgnore
	public Set<UserAuthority> getAuthorities() {
		return authorities;
	}

	// Use Roles as external API
	public Set<UserRole> getRoles() {
		Set<UserRole> roles = EnumSet.noneOf(UserRole.class);
		if (authorities != null) {
			for (UserAuthority authority : authorities) {
				roles.add(UserRole.valueOf(authority));
			}
		}
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		for (UserRole role : roles) {
			grantRole(role);
		}
	}

	public void grantRole(UserRole role) {
		if (authorities == null) {
			authorities = new HashSet<UserAuthority>();
		}
		authorities.add(role.asAuthorityFor(this));
	}

	public void revokeRole(UserRole role) {
		if (authorities != null) {
			authorities.remove(role.asAuthorityFor(this));
		}
	}

	public boolean hasRole(UserRole role) {
		return authorities.contains(role.asAuthorityFor(this));
	}

	@Override
	@JsonIgnore
	public String getUserId() {
		return id.toString();
	}
		
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return !accountEnabled;
	}

}
