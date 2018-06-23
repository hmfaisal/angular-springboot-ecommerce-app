package com.basketbird.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Priority;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;

import com.basketbird.backend.model.type.CurrencyType;
import com.basketbird.backend.model.type.Market;
import com.basketbird.backend.model.type.ProductType;
import com.basketbird.backend.model.type.UnitType;
import com.basketbird.backend.repository.ShoppingListRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by Mohiuddin on 2/27/2017.
 */
@Entity
@Table(name="shoppinglist")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingList extends BaseModel{
	
	private static final long serialVersionUID = 3169817607433999180L; 
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true)
	private Long id;
	
	@NotNull
    @Column(name="name",length=50)
    private String listName;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "list")
    private List<ListProduct> productList = new ArrayList<>();
    
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "makerId")
    private User maker;
    
}
