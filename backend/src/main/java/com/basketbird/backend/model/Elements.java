package com.basketbird.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.basketbird.backend.model.type.UnitType;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="elements")
@Getter
@Setter
@NoArgsConstructor
public class Elements extends BaseModel{
	private static final long serialVersionUID = 3169817607433999180L; 
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
	
	@NotNull
	@Column(unique=true, name="element_name",length=50)
    private String elementName;
	
	 @Column(name="unit",length=10)    
	 private int unit;
	 
	 @Column(name="unit_type")
	 @Enumerated(EnumType.STRING)
	 UnitType unitType;
}
