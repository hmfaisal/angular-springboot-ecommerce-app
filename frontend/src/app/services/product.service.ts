import { Injectable } from '@angular/core';
import { HttpHeaders, HttpParams } from '@angular/common/http';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable()
export class ProductService {

    currentUser;
    params:any;
    
    constructor(
        private apiService: ApiService,
        private config: ConfigService
    ) { }

    getAll(pageNumber:any,country:string) {
        this.params = {
            p:pageNumber,
            country:country,
        };
        return this.apiService.get(this.config.allproduct_url,this.params);
    }

    getAllCount(country:string) {
        this.params = {
            country:country,
        };
        return this.apiService.get(this.config.allproductCount_url,this.params);
    }

    getByName(name:string,pageNumber:any,country:string) {
        this.params = {
            p:pageNumber,
            name:name,
            country:country,
        };
        return this.apiService.get(this.config.productByName_url,this.params);
    }

    getByNameCount(name:string,country:string) {
        this.params = {
            name:name,
            country:country,
        };
        return this.apiService.get(this.config.productByNameCount_url,this.params);
    }

    getByShop(shop:string,pageNumber:any,country:string) {
        this.params = {
            p:pageNumber,
            shop:shop,
            country:country,
        };
        return this.apiService.get(this.config.productByShop_url,this.params);
    }

    getByShopCount(shop:string,country:string) {
        this.params = {
            shop:shop,
            country:country,
        };
        return this.apiService.get(this.config.productByShopCount_url,this.params);
    }

    getByPriceLow(pageNumber:any,country:string) {
        this.params = {
            p:pageNumber,
            country:country,
        };
        return this.apiService.get(this.config.productByLow_url,this.params);
    }

    getByPriceHigh(pageNumber:any,country:string) {
        this.params = {
            p:pageNumber,
            country:country,
        };
        return this.apiService.get(this.config.productByHigh_url,this.params);
    }

    getByPriceRange(min:number,max:number,pageNumber:any,country:string) {
        this.params = {
            p:pageNumber,
            min:min,
            max:max,
            country:country,
        };
        return this.apiService.get(this.config.productByRange_url,this.params);
    }

    getByPriceRangeCount(min:number,max:number,country:string) {
        this.params = {
            min:min,
            max:max,
            country:country,
        };
        return this.apiService.get(this.config.productByRangeCount_url,this.params);
    }

    getByCategory(category:string,pageNumber:any,country:string) {
        this.params = {
            p:pageNumber,
            category:category,
            country:country,
        };
        return this.apiService.get(this.config.productByCategory_url,this.params);
    }

    getByCategoryCount(category:string,country:string) {
        this.params = {
            category:category,
            country:country,
        };
        return this.apiService.get(this.config.productByCategoryCount_url,this.params);
    }

    getCategory() {
        return this.apiService.get(this.config.productCategory_url);
    }

    getBrand() {
        return this.apiService.get(this.config.productBrand_url);
    }



}