package com.basketbird.backend.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.basketbird.backend.model.type.CurrencyType;
import com.basketbird.backend.model.type.Market;
import com.basketbird.backend.model.type.ProductType;
import com.basketbird.backend.model.type.UnitType;
import com.basketbird.backend.repository.ProductRepository;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;

/**
 * Created by Mohiuddin on 2/27/2017.
 */

@Entity
@Table(name="product")
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseModel {
	
	private static final long serialVersionUID = 3169817607433999180L; 
			
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;
	
	@NotNull
	@Column(name="productname",length=500)
    private String productName;
    
    @Column(name="shopref")
    @Enumerated(EnumType.STRING)
    private Market shopReference;
    
    @Column(name="estunitprice")    
    private int estimateUnitPrice;
    
    @Column(name="prodref")    
    private String productImageUrl;
    
    @Column(name="unit")    
    private int unit;    
    
    @Column(name="companyproductid")
    private String companyProductId;
    
    @Column(name="unit_type")
    @Enumerated(EnumType.STRING)
    UnitType unitType;
    
    @Column(name="productgroup")
    @Enumerated(EnumType.STRING)
    ProductType category; 
    
    @Column(name = "parentId")
    Long parentID;    
    
    String country;
    
    @Column(name="currency")
    @Enumerated(EnumType.STRING)
    private CurrencyType Currency;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "product_elements",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "element_id", referencedColumnName = "id"))
    List<Elements> elements;
   
}
