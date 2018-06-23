package com.basketbird.backend.model;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user_info")
@Getter
@Setter
@NoArgsConstructor
public class UserInfo extends BaseModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;
	
	@OneToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;
	
	@Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname; 
    
    @Column(name="image")    
    private String profileImageUrl;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = false,mappedBy = "info")
    private UserAddress address;

}
