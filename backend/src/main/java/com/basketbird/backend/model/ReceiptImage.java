package com.basketbird.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.*;

import com.basketbird.backend.model.type.Market;
import com.basketbird.backend.model.type.ProductType;
import com.basketbird.backend.model.type.ShopItemPriority;
import com.basketbird.backend.model.type.UnitType;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Mohiuddin on 3/5/2017.
 */
@Entity
@Table(name="receipt_image")
@Getter
@Setter
@NoArgsConstructor
public class ReceiptImage extends BaseModel{  
	
	private static final long serialVersionUID = 3169817607433999180L; 
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;
	
    @Lob
    byte[] picture;
}