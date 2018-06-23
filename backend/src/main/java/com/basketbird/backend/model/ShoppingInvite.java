package com.basketbird.backend.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.*;

import com.basketbird.backend.model.type.InviteStatus;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="shoppinginvite")
@Getter
@Setter
@NoArgsConstructor

public class ShoppingInvite extends BaseModel{
	
	private static final long serialVersionUID = 3169817607433999180L; 
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL,targetEntity = ShoppingRequest.class)
    User request;
    
    @OneToOne(cascade = CascadeType.ALL,targetEntity = User.class)
    User shopper;
    
    @Enumerated
    @Column(name="invitestatus", nullable = true)
    InviteStatus inviteStatus;
    
}
