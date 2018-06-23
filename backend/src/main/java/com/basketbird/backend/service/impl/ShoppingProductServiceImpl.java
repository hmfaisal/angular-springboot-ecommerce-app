package com.basketbird.backend.service.impl;

import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.ShoppingProduct;
import com.basketbird.backend.model.ShoppingProductWrap;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.User;
import com.basketbird.backend.model.type.RequestStatus;
import com.basketbird.backend.model.type.ShoppingStatus;
import com.basketbird.backend.repository.ListProductRepository;
import com.basketbird.backend.repository.ProductRepository;
import com.basketbird.backend.repository.ShoppingListRepository;
import com.basketbird.backend.repository.ShoppingProductRepository;
import com.basketbird.backend.repository.ShoppingRequestRepository;
import com.basketbird.backend.service.HelperService;
import com.basketbird.backend.service.ShoppingProductService;
import com.basketbird.backend.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShoppingProductServiceImpl implements ShoppingProductService{

	@Autowired
	public ShoppingRequestRepository shoppingRequestRepository;

	@Autowired
	public ShoppingListRepository shoppingListRepository;

	@Autowired
	public ListProductRepository listProductRepository;

	@Autowired
	public ShoppingProductRepository shoppingProductRepository;

	@Autowired
	public ProductRepository productRepository;
	
	@Autowired
	private HelperService helperService;

	@Autowired
	private UserService userService;

	private final static int PAGESIZE = 20;

	@Override
	@Transactional(readOnly = true)
	public ShoppingProduct loadProductById(String id) throws AccessDeniedException {
		ShoppingProduct sp = this.shoppingProductRepository.findById(Long.valueOf(id));
		/*
		Product p = null;
		if (sp !=null){
			if (sp.getProduct()!=null){
				p = this.productRepository.findOne(sp.getProduct().getId());
			}
		}
		return this.returnSpWrap(sp, p);
		*/
		return sp;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingProduct> loadProductByRequest(ShoppingRequest request) throws AccessDeniedException {

		//List<ShoppingProductWrap> result = new ArrayList<>();
		List<ShoppingProduct> sp = this.shoppingProductRepository.findByRequest(request);
		/*
		if(sp!=null){
			sp.forEach(item -> {
				Product p = null;
				if (item.getProduct()!=null){
					p = this.productRepository.findOne(item.getProduct().getId());
				}
				ShoppingProductWrap res = returnSpWrap(item, p);
				result.add(res);
			});
		}
		
		return result;
		*/
		return sp;
	}

	//add product to request
	public ShoppingProduct saveSPWrapper(ShoppingRequest request, ShoppingProduct sproduct, Product product) {

		ShoppingProduct sp = this.helperService.getShoppingProduct(sproduct);
		ShoppingRequest req = this.helperService.getShoppingRequest(request);
		Product p = this.helperService.getProduct(product);

		if (sp == null && req!=null && this.canRequesterAdd(req)) {
			sproduct.setRequest(req);
			sproduct.setProduct(p);
			sproduct.setMaker(this.userService.currentUser());
			//sproduct.setActualUnit(sproduct.getEstimateUnit());
			//sproduct.setActualUnitPrice(sproduct.getEstimateUnitPrice());
			return this.saveProduct(sproduct);
		}else {
			return null;
		}
	}

	public ShoppingProduct saveProduct(ShoppingProduct sp) {
		return this.shoppingProductRepository.save(sp);
	}

	@Override
	public ShoppingProduct updateProduct(ShoppingProduct sproduct) {

		ShoppingProduct sp = this.helperService.getShoppingProduct(sproduct);
		User currentUser = this.userService.currentUser();
		if (sp != null) {
			ShoppingRequest req = sp.getRequest();
			if(currentUser == sp.getMaker()){
				if(this.canRequesterEdit(req)){
					sproduct.setProduct(sp.getProduct());
			    		sproduct.setRequest(sp.getRequest());
			    		sproduct.setActualUnit(sp.getActualUnit());
					sproduct.setActualUnitPrice(sp.getActualUnitPrice());
					sproduct.setWhenCreated(sp.getWhenCreated());
					sproduct.setMaker(currentUser);
					return this.saveProduct(sproduct);
				}

			}else{
				if(this.canShopperEdit(req)){
					//sproduct = sproduct.editProductByShopper(sp);
					sproduct.setProduct(sp.getProduct());
			    	sproduct.setProductName(sp.getProductName());
					sproduct.setProductImageUrl(sp.getProductImageUrl());
					sproduct.setEstimateUnit(sp.getEstimateUnit());
					sproduct.setEstimateUnitPrice(sp.getEstimateUnitPrice());
					sproduct.setWhenCreated(sp.getWhenCreated());
					sproduct.setRequest(sp.getRequest());
					sproduct.setMaker(sp.getMaker());
					return this.saveProduct(sproduct);
				}
			}
		}
		return null; 
	}

	//Saving Product when creating request first time
	@Override
	public ShoppingProduct saveRequestProduct(ListProduct lproduct, ShoppingRequest request) {
		User currentUser = this.userService.currentUser();
		ShoppingProduct sproduct = new ShoppingProduct();
		sproduct = sproduct.getSProductButId(lproduct);
		sproduct.setMaker(currentUser);
		sproduct.setRequest(request);
		return this.saveProduct(sproduct);
	}

	@Override
	public void removeProduct(String productId) {
		ShoppingProduct sp = shoppingProductRepository.findOne(Long.valueOf(productId));
		User currentUser = this.userService.currentUser();
		if (sp != null && currentUser == sp.getMaker() ) {
			ShoppingRequest req = sp.getRequest();
			if(this.canRequesterRemove(req)){
				this.shoppingProductRepository.delete(Long.valueOf(productId));
			}
		}
	}

	private boolean canRequesterEdit(ShoppingRequest request) {

		User currentUser = this.userService.currentUser();
		if (request != null ) {
			ShoppingRequest req = this.shoppingRequestRepository.findOne(request.getId());
			if (req != null && currentUser == req.getRequester() && currentUser!=req.getShopper()) {
				if ( req.getRequestStatus()==RequestStatus.REQUESTED ) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canShopperEdit(ShoppingRequest request) {

		User currentUser = this.userService.currentUser();
		if (request != null ) {
			ShoppingRequest req = this.shoppingRequestRepository.findOne(request.getId());
			if (req != null && currentUser != req.getRequester() && currentUser==req.getShopper()) {
				if ( req.getRequestStatus()!=RequestStatus.CONFIRMED && req.getShoppingStatus()==ShoppingStatus.ACCEPTED) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canRequesterRemove(ShoppingRequest request) {

		User currentUser = this.userService.currentUser();
		if (request != null ) {
			ShoppingRequest req = this.shoppingRequestRepository.findOne(request.getId());
			if (req != null && currentUser == req.getRequester() && currentUser!=req.getShopper()) {
				if ( req.getRequestStatus()==RequestStatus.REQUESTED && req.getShoppingStatus()==null) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canRequesterAdd(ShoppingRequest request) {
		User currentUser = this.userService.currentUser();
		if (request != null && currentUser == request.getRequester() && request.getShoppingStatus()!=ShoppingStatus.DELIVERED) {
			return true;
		}
		return false;
	}

	private ShoppingProductWrap returnSpWrap(ShoppingProduct sp, Product p) throws AccessDeniedException {
		ShoppingProductWrap result = new ShoppingProductWrap();
		result.setShoppingProduct(sp);
		result.setProduct(p);
		return result;
	}

}
