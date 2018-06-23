package com.basketbird.backend.repository;

import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.basketbird.backend.model.BaseModel;


@Configuration
public class RepositoryConfig extends RepositoryRestMvcConfiguration{
	private static final Logger log = LoggerFactory.getLogger(RepositoryConfig.class);
	@Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
            	Reflections reflections = new Reflections("com.basketbird.backend.model");

            	 Set<Class<? extends BaseModel>> allClasses = reflections.getSubTypesOf(BaseModel.class);
            	 allClasses.parallelStream().forEach(c-> {
            		 //log.info("************class: "+c.getSimpleName());
            		 config.exposeIdsFor(c);
            	 });
            	 //config.exposeIdsFor(BaseModel.class);
            	 //config.setDefaultMediaType(MediaType.APPLICATION_JSON);
            }
        };

    }
}
