package com.basketbird.backend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.basketbird.backend.model.type.Market;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="shopping")
@Getter
@Setter
@NoArgsConstructor
public class ShoppersPool extends BaseModel{
	
	private static final long serialVersionUID = 3169817607433999180L; 
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;
    
    @OneToOne(cascade = CascadeType.ALL,targetEntity = User.class)
    User shopper;	
    
    //Market markets;
    
    @Column(name="shoppingtimefrom", nullable = true) 
    Date shoppingTimeFrom;
    
    @Column(name="shoppingtimeto", nullable = true)
    Date shoppingTimeTo;
    
    @OneToOne(cascade = CascadeType.ALL,targetEntity = DeliveryAddress.class)
    DeliveryAddress shoppingAddress;
    
    @Enumerated
    @Column(name="marketname", nullable = true) 
    Market marketName;
    
    @Column(name="radious", nullable = true)
    int radious;
    
    @Column(name="isactive", nullable = true)
    boolean active;
    
    
}