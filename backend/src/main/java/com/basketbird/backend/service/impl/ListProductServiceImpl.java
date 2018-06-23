package com.basketbird.backend.service.impl;

import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ListProduct;
import com.basketbird.backend.model.ListProductWrap;
import com.basketbird.backend.model.User;
import com.basketbird.backend.repository.ProductRepository;
import com.basketbird.backend.repository.ShoppingListRepository;
import com.basketbird.backend.repository.ListProductRepository;
import com.basketbird.backend.service.HelperService;
import com.basketbird.backend.service.ListProductService;
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
public class ListProductServiceImpl implements ListProductService {

	@Autowired
	private ShoppingListRepository shoppingListRepository;

	@Autowired
	private ListProductRepository listProductRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private HelperService helperService;

	@Autowired
	private UserService userService;

	private final static int PAGESIZE = 10;

	@Override
	@Transactional(readOnly = true)
	public ListProduct loadProductById(String id) throws AccessDeniedException {
		Product p = null;
		ListProduct lp = this.listProductRepository.findById(Long.valueOf(id));
		/*
		if (lp !=null){
			if (lp.getProduct()!=null){
				p = this.productRepository.findOne(lp.getProduct().getId());
			}
		}
		return this.returnLpWrap(lp, p);
		*/
		return lp;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ListProduct> loadUnlistedProductByMaker() throws AccessDeniedException {

		//List<ListProductWrap> result = new ArrayList<>();
		User productMaker = this.userService.currentUser();
		//PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
		List<ListProduct> lp = this.listProductRepository.findUnlistedByMaker(productMaker);
		
		/*
		if(lp !=null){
			lp.forEach(item -> {
				Product p = null;
				if (item.getProduct()!=null){
					p = this.productRepository.findOne(item.getProduct().getId());
				}
				ListProductWrap res = returnLpWrap(item, p);
				result.add(res);
			});
		}
		return result;
		*/
		return lp;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ListProduct> loadProductByList(ShoppingList list) throws AccessDeniedException {

		//List<ListProductWrap> result = new ArrayList<>();
		List<ListProduct> lp = this.listProductRepository.findByList(list);
		/*
		if(lp!=null){
			lp.forEach(item -> {
				Product p = null;
				if (item.getProduct()!=null){
					p = this.productRepository.findOne(item.getProduct().getId());
				}
				ListProductWrap res = returnLpWrap(item, p);
				result.add(res);
			});
		}
		return result;
		*/
		return lp;
	}

	public ListProduct saveLPWrapper(ShoppingList list, ListProduct lproduct, Product product) {

		ListProduct lp = this.helperService.getListProduct(lproduct);
		ShoppingList l = this.helperService.getShoppingList(list);
		Product p = this.helperService.getProduct(product) ;
		if(lp==null){
			lproduct.setList(l);
			lproduct.setProduct(p);
			lproduct.setMaker(this.userService.currentUser());
			return this.saveProduct(lproduct);
		}
		return null;
	}

	public ListProduct saveProduct(ListProduct lp) {
		return this.listProductRepository.save(lp);
	}

	@Override
	public ListProduct updateProduct(ListProduct lproduct) {

		ListProduct lp = this.helperService.getListProduct(lproduct);

		if (lp!=null && canEdit(lp)) {
			lproduct.setList(lp.getList());
			lproduct.setMaker(lp.getMaker());
			lproduct.setProduct(lp.getProduct());
			lproduct.setWhenCreated(lp.getWhenCreated());
			return this.saveProduct(lproduct);
		} else {
			return null;
		}
	}

	@Override
	public ListProduct updateListProduct(ListProduct lproduct, ShoppingList l) {

		ListProduct lp = this.helperService.getListProduct(lproduct);

		if (lp!=null && canUpdateByList(lp)) {
			lp.setList(l);
			return this.saveProduct(lp);
		} else {
			return null;
		}
	}

	@Override
	public void removeProduct(String productId) {
		ListProduct lp = listProductRepository.findOne(Long.valueOf(productId));
		if (lp!=null && canEdit(lp)) {
			this.listProductRepository.delete(Long.valueOf(productId));
		}
	}

	private boolean canEdit(ListProduct lp) {
		//ListProduct lp = this.listProductRepository.findOne(lproduct.getId());
		User currentUser = this.userService.currentUser();
		if (currentUser == lp.getMaker()) {
			ShoppingList list = lp.getList();
			if (list == null) {
				return true;
			} else {
				ShoppingList l = this.shoppingListRepository.findOne(list.getId());
				if (currentUser == l.getMaker()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean canUpdateByList(ListProduct lp) {
		//ListProduct lp = this.listProductRepository.findOne(lproduct.getId());
		User currentUser = this.userService.currentUser();
		if (currentUser == lp.getMaker()) {
			ShoppingList list = lp.getList();
			if (list == null) {
				return true;
			} 
		}
		return false;
	}
	
	private ListProductWrap returnLpWrap(ListProduct lp, Product p) throws AccessDeniedException {
		ListProductWrap result = new ListProductWrap();
		result.setListProduct(lp);
		result.setProduct(p);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Long loadUnlistedProductByMakerTotal() throws AccessDeniedException {
		User productMaker = this.userService.currentUser();
		Long result = this.listProductRepository.countUnlistedByMaker(productMaker);
		return result;
	}

}
