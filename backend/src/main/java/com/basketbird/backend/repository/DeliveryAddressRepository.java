package com.basketbird.backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basketbird.backend.model.DeliveryAddress;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ShoppingRequest;


public interface DeliveryAddressRepository extends BaseRepository<DeliveryAddress,Long>{
	
	DeliveryAddress findById(Long id);
	DeliveryAddress findByRequest(ShoppingRequest request);

}
