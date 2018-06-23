package com.basketbird.backend.service.impl;

import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.UserAddress;
import com.basketbird.backend.model.UserInfo;
import com.basketbird.backend.model.DeliveryAddress;
import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.repository.ProductRepository;
import com.basketbird.backend.repository.ShoppingListRepository;
import com.basketbird.backend.repository.ShoppingProductRepository;
import com.basketbird.backend.repository.ShoppingRequestRepository;
import com.basketbird.backend.repository.UserAddressRepository;
import com.basketbird.backend.repository.UserInfoRepository;
import com.basketbird.backend.service.HelperService;
import com.basketbird.backend.service.ListProductService;
import com.basketbird.backend.repository.DeliveryAddressRepository;
import com.basketbird.backend.repository.ListProductRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HelperServiceImpl implements HelperService{
	
	@Autowired
	private ShoppingListRepository shoppingListRepository;

	@Autowired
	private ListProductRepository listProductRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	public ShoppingProductRepository shoppingProductRepository;
	
	@Autowired
	public ShoppingRequestRepository shoppingRequestRepository;
	
	@Autowired
	public DeliveryAddressRepository deliveryAddressRepository;
	
	@Autowired
    private UserInfoRepository userInfoRepository;
	
	@Autowired
	public UserAddressRepository userAddressRepository;
	
	public ListProduct getListProduct(ListProduct lproduct){
    	if(lproduct.getId()!=null){
    		return this.listProductRepository.findOne(lproduct.getId());
    	}else{
    		return null;
    	}
    }
	
	public Product getProduct(Product product){
    	if(product.getId()!=null){
    		return this.productRepository.findOne(product.getId());
    	}else{
    		return null;
    	}
    }
	
	public ShoppingList getShoppingList(ShoppingList list){
    	if(list.getId()!=null){
    		return this.shoppingListRepository.findOne(list.getId());
    	}else{
    		return null;
    	}
    }
	
	public ShoppingProduct getShoppingProduct(ShoppingProduct sproduct){
    	if(sproduct.getId()!=null){
    		return this.shoppingProductRepository.findOne(sproduct.getId());
    	}else{
    		return null;
    	}
    }
	
	public ShoppingRequest getShoppingRequest(ShoppingRequest request){
    	if(request.getId()!=null){
    		return this.shoppingRequestRepository.findOne(request.getId());
    	}else{
    		return null;
    	}
    }
	
	public DeliveryAddress getDeliveryAddress(DeliveryAddress address){
    	if(address.getId()!=null){
    		return this.deliveryAddressRepository.findOne(address.getId());
    	}else{
    		return null;
    	}
    }
	
	public UserAddress getUserAddress(UserAddress address){
    	if(address.getId()!=null){
    		return this.userAddressRepository.findOne(address.getId());
    	}else{
    		return null;
    	}
    }
	
	public UserInfo getUserInfo(UserInfo info){
    	if(info.getId()!=null){
    		return this.userInfoRepository.findOne(info.getId());
    	}else{
    		return null;
    	}
    }

}
