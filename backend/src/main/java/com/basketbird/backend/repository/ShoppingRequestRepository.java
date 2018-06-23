package com.basketbird.backend.repository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.basketbird.backend.model.ShoppingList;
import com.basketbird.backend.model.ShoppingRequest;
import com.basketbird.backend.model.User;
import com.basketbird.backend.model.type.RequestStatus;


public interface ShoppingRequestRepository extends BaseRepository<ShoppingRequest,Long> {
	
	ShoppingRequest findById(Long id);
	List<ShoppingRequest> findByRequesterOrderByDeliveryTimeAsc(Pageable pageable,User requester);
	List<ShoppingRequest> findByShopperOrderByDeliveryTimeAsc(Pageable pageable,User shopper);
	List<ShoppingRequest> findByRequester(User requester);
	List<ShoppingRequest> findByShopper(User shopper);
	
	@Query(value="select r FROM ShoppingRequest r JOIN r.deliveryaddress a WHERE (r.requestStatus =:requestStatus) AND (lower(a.country) like lower(:country) OR :country is null OR :country='') AND (lower(a.city) like lower(:city) OR :city is null OR :city='') ORDER BY r.deliveryTime ASC")
	List<ShoppingRequest> findByRequestStatusOrderByDeliveryTimeAsc(Pageable pageable, @Param("requestStatus") RequestStatus requestStatus, @Param("country") String country, @Param("city") String city);
	
	@Query(value="select r FROM ShoppingRequest r JOIN r.deliveryaddress a WHERE (r.requestStatus =:requestStatus) AND (lower(a.country) like lower(:country) OR :country is null OR :country='') AND (lower(a.city) like lower(:city) OR :city is null OR :city='') ORDER BY r.deliveryTime DESC")
	List<ShoppingRequest> findByRequestStatusOrderByDeliveryTimeDesc(@Param("requestStatus") RequestStatus requestStatus,@Param("country") String country, @Param("city") String city);
	
	@Query(value="select r FROM ShoppingRequest r JOIN r.deliveryaddress a WHERE (r.requestStatus =:requestStatus) AND (lower(a.country) like lower(:country) OR :country is null OR :country='') AND (lower(a.city) like lower(:city) OR :city is null OR :city='') ORDER BY r.deliveryTime ASC")
	List<ShoppingRequest> findByRequestStatus(@Param("requestStatus") RequestStatus requestStatus,@Param("country") String country, @Param("city") String city);
	
	@Query("select r FROM ShoppingRequest r JOIN r.deliveryaddress a WHERE (r.deliveryTime >=:fromTime) AND (r.deliveryTime<=:toTime) AND ((lower(a.country) like lower(:country) OR :country is null OR :country='') OR (lower(a.city) LIKE CONCAT('%',lower(:city),'%')  OR :city is null OR :city='')) ORDER BY r.deliveryTime ASC")
	List<ShoppingRequest> findByTimeRange(@Param("fromTime") LocalDateTime fromTime, @Param("toTime") LocalDateTime toTime,@Param("country") String country, @Param("city") String city);
	
	@Query("select r FROM ShoppingRequest r JOIN r.deliveryaddress a WHERE (r.deliveryTime >=:fromTime) AND (r.deliveryTime<=:toTime) AND ((lower(a.country) like lower(:country) OR :country is null OR :country='') OR (lower(a.city) LIKE CONCAT('%',lower(:city),'%')  OR :city is null OR :city='')) ORDER BY r.deliveryTime ASC")
	List<ShoppingRequest> findByDeliveryTimeRange(Pageable pageable,@Param("fromTime") LocalDateTime fromTime, @Param("toTime") LocalDateTime toTime,@Param("country") String country, @Param("city") String city);
	
	@Query("select r FROM ShoppingRequest r JOIN r.deliveryaddress a WHERE  (r.estimatedTotalPrice>=:min) AND (r.estimatedTotalPrice<=:max) AND ((lower(a.country) like lower(:country) OR :country is null OR :country='') OR (lower(a.city) LIKE CONCAT('%',lower(:city),'%')  OR :city is null OR :city=''))")
	List<ShoppingRequest> findByPriceRange(@Param("min") double min, @Param("max") double max,@Param("country") String country, @Param("city") String city);
	
	@Query("select r FROM ShoppingRequest r JOIN r.deliveryaddress a WHERE  (r.estimatedTotalPrice>=:min) AND (r.estimatedTotalPrice<=:max) AND ((lower(a.country) like lower(:country) OR :country is null OR :country='') OR (lower(a.city) LIKE CONCAT('%',lower(:city),'%')  OR :city is null OR :city=''))")
	List<ShoppingRequest> findByRange(Pageable pageable,@Param("min") double min, @Param("max") double max,@Param("country") String country, @Param("city") String city);
	
	//TODO
	static final String HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(a.latitude)) * cos(radians(a.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(a.latitude))))";
	@Query(value="select r FROM ShoppingRequest r JOIN r.deliveryaddress a WHERE " +HAVERSINE_PART+ "< :distance ORDER BY "  +HAVERSINE_PART+ "DESC")
	List<ShoppingRequest> requestByDistance(Pageable pageable,@Param("latitude") final double latitude, @Param("longitude") final double longitude, @Param("distance") final double distance);
	//TODO
	
	
	Long countByRequester(User requester);
	Long countByShopper(User shopper);
	
	@Query(value="select count(r) FROM ShoppingRequest r JOIN r.deliveryaddress a WHERE (r.requestStatus =:requestStatus) AND (lower(a.country) like lower(:country) OR :country is null OR :country='') AND (lower(a.city) like lower(:city) OR :city is null OR :city='')")
	Long countByRequestStatus(@Param("requestStatus") RequestStatus requestStatus,@Param("country") String country, @Param("city") String city);
	
	@Query("select DISTINCT r.currency FROM ShoppingRequest r")
	List<ShoppingRequest> getCurrency();
	
	
}
