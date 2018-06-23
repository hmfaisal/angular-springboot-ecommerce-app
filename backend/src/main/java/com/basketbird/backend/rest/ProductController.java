package com.basketbird.backend.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basketbird.backend.service.ProductService;
import com.basketbird.backend.model.Product;
import com.basketbird.backend.model.type.Market;
import com.basketbird.backend.model.type.ProductType;


@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class ProductController {
	
	@Autowired
    private ProductService productService;
	
	@RequestMapping(method = GET, value = "/product/all")
	public List<Product> getAll(@RequestParam(name = "p", defaultValue = "1") int pageNumber,@RequestParam(value= "country",required=false) String country) throws URISyntaxException{
		return this.productService.loadAll(pageNumber,country);
	}
	
	@RequestMapping( method = GET, value = "/product/{productId}" )
    public List<Product> loadById( @PathVariable String productId ) throws URISyntaxException{
        return this.productService.loadProductByProductId( productId );
    }
	
	@RequestMapping( method = GET, value = "/product/name" )
    public List<Product> loadByName( @RequestParam(name = "p", defaultValue = "1") int pageNumber, @RequestParam(value= "name",required=false) String name, @RequestParam(value="country", required=false) String country) throws URISyntaxException{
        return this.productService.loadProductByProductName( pageNumber, name ,country);
    }
	
	@RequestMapping( method = GET, value = "/product/country" )
    public List<Product> loadByCountry( @RequestParam(name = "p", defaultValue = "1") int pageNumber, @RequestParam(value="country") String country) throws URISyntaxException{
        return this.productService.loadProductByCountry( pageNumber, country );
    }
	
	@RequestMapping( method = GET, value = "/product/shop" )
    public List<Product> loadByShop( @RequestParam(name = "p", defaultValue = "1") int pageNumber, @RequestParam("shop") Market shop, @RequestParam(value="country", required=false) String country) throws URISyntaxException{
        return this.productService.loadProductByShop( pageNumber, shop, country );
    }
	
	@RequestMapping( method = GET, value = "/product/range" )
    public List<Product> loadByPriceRange( @RequestParam(name = "p", defaultValue = "1") int pageNumber, @RequestParam("min") int min, @RequestParam("max") int max, @RequestParam(value="country", required=false) String country) throws URISyntaxException{
        return this.productService.loadProductByPriceRange( pageNumber, min, max ,country);
    }
	
	@RequestMapping( method = GET, value = "/product/low" )
    public List<Product> loadByPriceLow( @RequestParam(name = "p", defaultValue = "1") int pageNumber, @RequestParam(value="country", required=false) String country) throws URISyntaxException{
        return this.productService.loadProductByPriceLow( pageNumber ,country);
    }
	
	@RequestMapping( method = GET, value = "/product/high" )
    public List<Product> loadByPriceHigh( @RequestParam(name = "p", defaultValue = "1") int pageNumber, @RequestParam(value="country", required=false) String country) throws URISyntaxException{
        return this.productService.loadProductByPriceHigh( pageNumber ,country);
    }
	
	@RequestMapping( method = GET, value = "/product/category" )
    public List<Product> loadByCategory( @RequestParam(name = "p", defaultValue = "1") int pageNumber, @RequestParam("category") ProductType category, @RequestParam(value="country", required=false) String country) throws URISyntaxException{
        return this.productService.loadProductByCategory( pageNumber, category ,country);
    }
	
	@RequestMapping( method = GET, value = "/product/category/all" )
    public List<?> getCategory( ) throws URISyntaxException{
        return this.productService.getCategory();
    }
	
	@RequestMapping( method = GET, value = "/product/brand/all" )
    public List<?> getBrand( ) throws URISyntaxException{
        return this.productService.getBrand();
    }
	
	@RequestMapping( method = GET, value = "/product/all/count" )
    public Long getTotal(@RequestParam(value= "country",required=false) String country ) throws URISyntaxException{
        return this.productService.getAllTotal(country);
    }
	
	@RequestMapping( method = GET, value = "/product/name/count" )
    public Long getByNameTotal(@RequestParam(value= "name",required=false) String name, @RequestParam(value= "country",required=false) String country ) throws URISyntaxException{
        return this.productService.getByNameTotal(name,country);
    }
	
	@RequestMapping( method = GET, value = "/product/shop/count" )
    public Long getByShopTotal(@RequestParam("shop") Market shop, @RequestParam(value= "country",required=false) String country ) throws URISyntaxException{
        return this.productService.getByShopTotal(shop,country);
    }
	
	@RequestMapping( method = GET, value = "/product/range/count" )
    public Long getByRangeTotal(@RequestParam("min") int min, @RequestParam("max") int max, @RequestParam(value= "country",required=false) String country ) throws URISyntaxException{
        return this.productService.getByRangeTotal(min,max,country);
    }
	
	@RequestMapping( method = GET, value = "/product/category/count" )
    public Long getByCategoryTotal(@RequestParam("category") ProductType category, @RequestParam(value= "country",required=false) String country ) throws URISyntaxException{
        return this.productService.getByCategoryTotal(category,country);
    }
}
