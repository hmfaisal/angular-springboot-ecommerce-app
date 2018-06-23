import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable()
export class ShoppingProductService {

    params: any;

    constructor(
        private apiService: ApiService,
        private config: ConfigService
    ) { }

    getShoppingProduct(id:number) {
        return this.apiService.get(this.config.shoppingProduct_url+ '/' + id);
    }

    saveShoppingProduct(sproductwrapper){
        return this.apiService.post(this.config.shoppingProductSave_url,sproductwrapper);
    }

    updateShoppingProduct(sproduct){
        return this.apiService.post(this.config.shoppingProductUpdate_url,sproduct);
    }

    deleteShoppingProduct(id:number){
        return this.apiService.delete(this.config.shoppingProductRemove_url+ '/' + id);
    }

}