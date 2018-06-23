package com.basketbird.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.User;
import com.basketbird.backend.model.type.Market;
import com.basketbird.backend.model.type.ProductType;
/**
 * Created by Hasan Mahmud on 4/12/2017.
 */

public interface ProductRepository extends BaseRepository<Product,Long>{
	
	List<Product> findById(Long id);
	
	@Query("select p FROM Product p WHERE (lower(p.country) like lower(:country) OR :country is null OR :country='')")
	List<Product> findByAll( Pageable pageable, @Param("country") String country );
	
	@Query("select p FROM Product p WHERE (lower(p.productName) like lower(concat('%', :productName,'%')) OR :productName is null OR :productName='') AND (lower(p.country) like lower(:country) OR :country is null OR :country='')")
	List<Product> findByProductName( Pageable pageable, @Param("productName") String productName , @Param("country") String country);
	
	@Query("select p FROM Product p WHERE lower(p.country) like lower(:country)")
	List<Product> findByCountry( Pageable pageable, @Param("country") String country );
	
	@Query("select p FROM Product p WHERE (p.shopReference IN (:shop)) AND (lower(p.country) like lower(:country) OR :country is null OR :country='')")
	List<Product> findByShop( Pageable pageable, @Param("shop") Market shop , @Param("country") String country);
	
	@Query("select p FROM Product p WHERE  (p.estimateUnitPrice>=:min) AND (p.estimateUnitPrice<=:max) AND (lower(p.country) like lower(:country) OR :country is null OR :country='')")
	List<Product> findByPriceRange( Pageable pageable, @Param("min") int min, @Param("max") int max , @Param("country") String country);
	
	@Query("select p FROM Product p WHERE (lower(p.country) like lower(:country) OR :country is null OR :country='') ORDER BY p.estimateUnitPrice ASC")
	List<Product> findByPriceLow( Pageable pageable , @Param("country") String country);
	
	@Query("select p FROM Product p WHERE (lower(p.country) like lower(:country) OR :country is null OR :country='') ORDER BY p.estimateUnitPrice DESC")
	List<Product> findByPriceHigh( Pageable pageable , @Param("country") String country);
	
	@Query("select p FROM Product p WHERE (p.category IN (:category)) AND (lower(p.country) like lower(:country) OR :country is null OR :country='')")
	List<Product> findByCategory( Pageable pageable, @Param("category") ProductType category , @Param("country") String country);
	
	@Query("select DISTINCT p.category FROM Product p")
	List<?> getCategory();
	
	@Query("select count(p) FROM Product p WHERE (lower(p.country) like lower(:country) OR :country is null OR :country='')")
	Long getTotal( @Param("country") String country);
	
	@Query("select count(p) FROM Product p WHERE (p.productName LIKE CONCAT('%',:productName,'%') OR :productName is null OR :productName='') AND (lower(p.country) like lower(:country) OR :country is null OR :country='')")
	Long findByNameTotal( @Param("productName") String productName , @Param("country") String country);
	
	@Query("select count(p) FROM Product p WHERE (p.shopReference IN (:shop)) AND (lower(p.country) like lower(:country) OR :country is null OR :country='')")
	Long findByShopTotal( @Param("shop") Market shop , @Param("country") String country);
	
	@Query("select count(p) FROM Product p WHERE (p.estimateUnitPrice>=:min) AND (p.estimateUnitPrice<=:max) AND (lower(p.country) like lower(:country) OR :country is null OR :country='')")
	Long findByPriceRangeTotal( @Param("min") int min, @Param("max") int max , @Param("country") String country);
	
	@Query("select count(p) FROM Product p WHERE (p.category IN (:category)) AND (lower(p.country) like lower(:country) OR :country is null OR :country='')")
	Long findByCategoryTotal( @Param("category") ProductType category , @Param("country") String country);
	
    
}
