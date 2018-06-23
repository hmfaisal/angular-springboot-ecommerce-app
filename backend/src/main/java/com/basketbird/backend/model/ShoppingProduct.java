package com.basketbird.backend.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.basketbird.backend.model.type.UnitType;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="shoppingproduct")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingProduct extends BaseModel{
	
	private static final long serialVersionUID = 3169817607433999180L; 
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;
	
	@NotNull
    @Column(name="name",length=50)
    private String productName;
	
	@NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "makerId")
    private User maker;
    
	@OneToOne() 
	@JoinColumn(name = "productId")
	private Product product ;
	
    @ManyToOne()
    @JoinColumn(name = "requestId")
    private ShoppingRequest request ;
    
    @Column(name="prodref",length=100)    
    private String productImageUrl;
    
    @NotNull
    @Column(name="estunitprice")    
    private double estimateUnitPrice;
    
    @Column(name="actunitprice")    
    private double actualUnitPrice;
    
    @NotNull
    @Column(name="estunit",length=10)    
    private int estimateUnit; 
    
    @Column(name="unit_type")
    private String unitType;
    
    @Column(name="actunit",length=10)    
    private int actualUnit; 
    
    public ShoppingProduct editProductByRequester(ShoppingProduct sproduct){
    	sproduct.setProduct(sproduct.getProduct());
    	sproduct.setRequest(sproduct.getRequest());
    	sproduct.setActualUnit(sproduct.getActualUnit());
		sproduct.setActualUnitPrice(sproduct.getActualUnitPrice());
		sproduct.setWhenCreated(sproduct.getWhenCreated());
		return sproduct;
    }
    
    public ShoppingProduct editProductByShopper(ShoppingProduct sproduct){
    	sproduct.setProduct(sproduct.getProduct());
    	sproduct.setProductName(sproduct.getProductName());
		sproduct.setProductImageUrl(sproduct.getProductImageUrl());
		sproduct.setEstimateUnit(sproduct.getEstimateUnit());
		sproduct.setEstimateUnitPrice(sproduct.getEstimateUnitPrice());
		sproduct.setUnitType(sproduct.getUnitType());
		sproduct.setWhenCreated(sproduct.getWhenCreated());
		sproduct.setRequest(sproduct.getRequest());
		sproduct.setMaker(sproduct.getMaker());
		return sproduct;
    }
    
    public ShoppingProduct getSProductButId(ListProduct lp){
    	
    	ShoppingProduct sproduct = new ShoppingProduct();
    	sproduct.setProduct(lp.getProduct());
		sproduct.setProductName(lp.getProductName());
		sproduct.setProductImageUrl(lp.getProductImageUrl());
		sproduct.setEstimateUnit(lp.getEstimateUnit());
		sproduct.setEstimateUnitPrice(lp.getEstimateUnitPrice());
		sproduct.setUnitType(lp.getUnitType());
    	return sproduct;
    }
    
}
