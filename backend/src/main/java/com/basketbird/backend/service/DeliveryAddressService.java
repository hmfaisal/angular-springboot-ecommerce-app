package com.basketbird.backend.service;

import com.basketbird.backend.model.DeliveryAddress;
import com.basketbird.backend.model.ShoppingRequest;

public interface DeliveryAddressService {
	
	DeliveryAddress loadAddressById(String Id);
	DeliveryAddress loadDelAddressByRequest(ShoppingRequest request);
	DeliveryAddress saveRequestAddress(DeliveryAddress address,ShoppingRequest request);
	DeliveryAddress saveDeliveryAddress(DeliveryAddress address);
	DeliveryAddress save(DeliveryAddress address);

}
