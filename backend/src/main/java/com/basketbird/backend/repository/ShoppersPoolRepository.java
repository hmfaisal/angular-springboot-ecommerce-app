package com.basketbird.backend.repository;

import org.springframework.stereotype.Repository;

import com.basketbird.backend.model.ShoppersPool;

//@RepositoryRestResource(collectionResourceRel = "shoppers_pool", path = "live_shoppers")
@Repository
public interface ShoppersPoolRepository extends BaseRepository<ShoppersPool, Long>{

}