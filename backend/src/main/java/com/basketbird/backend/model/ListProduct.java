package com.basketbird.backend.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.basketbird.backend.model.type.CurrencyType;
import com.basketbird.backend.model.type.Market;
import com.basketbird.backend.model.type.ProductType;
import com.basketbird.backend.model.type.UnitType;
import com.basketbird.backend.repository.ListProductRepository;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="listproduct")
@Getter
@Setter
public class ListProduct extends BaseModel{
	
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
    @JoinColumn(name = "listId")
    private ShoppingList list ;
    
    @Column(name="prodref",length=100)    
    private String productImageUrl;
    
    @NotNull
    @Column(name="estunitprice")    
    private double estimateUnitPrice;
    
    @NotNull
    @Column(name="estunit",length=10)    
    private int estimateUnit; 
    
    @Column(name="unit_type")
    private String unitType;

}
