package com.basketbird.backend.model;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.basketbird.backend.helper.LocalDateTimeConverter;
import com.basketbird.backend.model.type.CurrencyType;
import com.basketbird.backend.model.type.Market;
import com.basketbird.backend.model.type.RequestStatus;
import com.basketbird.backend.model.type.ShoppingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="request")
@Getter
@Setter
@NoArgsConstructor

public class ShoppingRequest extends BaseModel {
	
	private static final long serialVersionUID = 3169817607433999180L; 
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;
	
	@Column(name="name",length=50)
    private String requestName;

	@NotNull
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "requesterId")
	private User requester;
    
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "shopperId")
    private User shopper;
    
    @NotNull
    @Enumerated
    @Column(name="requeststatus")
    private RequestStatus requestStatus;
    
    @Enumerated
    @Column(name="shoppingstatus")
    private ShoppingStatus shoppingStatus;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "request")
    private List<ShoppingProduct> shoppingProduct = new ArrayList<>();
    
    @Enumerated
    @Column(name="marketname") 
    private Market marketName;
    
    @NotNull
    @Column(name="deliverytime") 
    @Convert(converter = LocalDateTimeConverter.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime deliveryTime;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = false,mappedBy = "request")
    private DeliveryAddress deliveryaddress;
    
    @Column(name="radious")
    int radious;
    
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = ReceiptImage.class)
    private List<ReceiptImage> shoppingReceiptImageList;
    
    @NotNull
    @Column(name="estpricetotal")
    private double estimatedTotalPrice;
    
    @Column(name="actpricetotal")
    private double actualTotalPrice;
    
    @NotNull
    @Column(name="estunittotal",length=10)    
    private int estimateTotalUnit; 
    
    @Column(name="actunittotal",length=10)    
    private int actualTotalUnit;
    
    @Column(name="shippingcost")
    private double shippingcost;
    
    @Column(name="charge")
    private double charge;
    
    @Column(name="currency")
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    
    public ShoppingRequest requesterData(ShoppingRequest req){;
    	req.setShoppingProduct(req.getShoppingProduct());
    	req.setShopper(req.getShopper());
    	req.setShoppingStatus(req.getShoppingStatus());
    	req.setActualTotalUnit(req.getActualTotalUnit());
    	req.setActualTotalPrice(req.getActualTotalPrice());
    	return req;
    }
    
    public ShoppingRequest shopperData(ShoppingRequest req){
    	req.setRequester(req.getRequester());
    	req.setRequestStatus(req.getRequestStatus());
    	req.setShoppingProduct(req.getShoppingProduct());
    	req.setEstimateTotalUnit(req.getEstimateTotalUnit());
    	req.setEstimatedTotalPrice(req.getEstimatedTotalPrice());
    	req.setDeliveryTime(req.getDeliveryTime());
    	req.setDeliveryaddress(req.getDeliveryaddress());
    	req.setCurrency(req.getCurrency());
    	req.setMarketName(req.getMarketName());
    	return req;
    }
    
}
