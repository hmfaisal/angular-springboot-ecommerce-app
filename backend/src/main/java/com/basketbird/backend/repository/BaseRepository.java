package com.basketbird.backend.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basketbird.backend.model.BaseModel;
@NoRepositoryBean
public interface BaseRepository<T extends BaseModel,ID extends Serializable> extends JpaRepository<T, ID> {
	//List<T> findByWhenCreated(@Param("CreationTime") String creationTime);
	//List<T> findByWhenUpdated(@Param("UpdateTime") String whenUpdated);
	Optional<T> findById(@Param("id") String id);
	
	
}
