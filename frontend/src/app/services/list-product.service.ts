import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable()
export class ListProductService {

    params: any;

    constructor(
        private apiService: ApiService,
        private config: ConfigService
    ) { }

    getListProduct(id:number) {
        return this.apiService.get(this.config.listProduct_url+ '/' + id);
    }

    getListProductCount(list) {
        return this.apiService.get(this.config.listProductCount_url,list);
    }

    getListProductUnlisted() {
        return this.apiService.get(this.config.listProductUnlisted_url);
    }

    getListProductUnlistedCount() {
        return this.apiService.get(this.config.listProductUnlistedCount_url);
    }

    saveListProduct(lproductwrapper){
        return this.apiService.post(this.config.listProductSave_url,lproductwrapper);
    }

    updateListProduct(lproduct){
        return this.apiService.post(this.config.listProductUpdate_url,lproduct);
    }

    deleteListProduct(id:number){
        return this.apiService.delete(this.config.listProductRemove_url+ '/' + id);
    }

    calculatePrice(lists:any):number{
        let price =0;
        for (let list of lists) {
            //let amount = list.estimateUnit;
            //let temprice= list.estimateUnitPrice*amount;
            price = price+list.estimateUnitPrice;
        }
        return price;
      }

}