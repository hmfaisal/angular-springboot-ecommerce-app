package com.basketbird.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Hfaisal on 2/03/2018.
 */
@Entity
@Table(name="user_address")
@Getter
@Setter
@NoArgsConstructor

public class UserAddress extends BaseModel {
	
	private static final long serialVersionUID = 3169817607433999180L; 
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;
	
	@Column(name = "streetno")
    private String streetNumber;
	
	@Column(name = "streetname")
    private String streetName;
	
	@NotNull
	@Column(name = "postcode")
    private String postcode;
    
	@Column(name = "city")
    private String city;
    
	@Column(name = "state")
    private String state;
    
	@NotNull
	@Column(name = "country")
    private String country;
	
	@Column(name = "latitude")
    private double latitude;
	
	@Column(name = "longitude")
    private double longitude;   
	
	@Column(name = "url")
    private String url;
	
	@Column(name = "additional")
    private String additional;
    
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "makerId")
    private User maker;
    
    @OneToOne()
    @JoinColumn(name = "infoId")
    private UserInfo info ;

}
