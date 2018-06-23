import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable()
export class ListService {

    params: any;
    
    constructor(
        private apiService: ApiService,
        private config: ConfigService
    ) { }

    getList(id: string) {
        return this.apiService.get(this.config.list_url + '/' + id);
    }

    getMyList(pageNumber: number) {
        this.params = {
            p: pageNumber,
        };
        return this.apiService.get(this.config.mylist_url, this.params);
    }

    getMyListCount() {
        return this.apiService.get(this.config.mylistCount_url);
    }

    saveList(listwrapper) {
        return this.apiService.post(this.config.listSave_url, listwrapper);
    }

    updateList(list) {
        return this.apiService.post(this.config.listUpdate_url, list);
    }

    deleteList(id: number) {
        return this.apiService.delete(this.config.listRemove_url+ '/' + id);
    }

    calculatePrice(listProduct: any):number {
        let price = 0;
        for (let lp of listProduct) {
            //let amount = lp.estimateUnit;
            //let temprice= lp.estimateUnitPrice*amount;
            price = price+lp.estimateUnitPrice;
        }
        return price;
    }

    calculateShippingCost(listProduct: any):number {
        let price = 4;
        /*
        for (let lp of listProduct) {
            let amount = lp.estimateUnit;
            let temprice= lp.estimateUnitPrice*amount;
            price = price+temprice;
        }
        */
        return price;
    }

    calculateCharge(cost: number):number {
        let price = 0;
        /*
        for (let lp of listProduct) {
            let amount = lp.estimateUnit;
            let temprice= lp.estimateUnitPrice*amount;
            price = price+temprice;
        }
        */
        return price;
    }

    calculateTotalItem(listProduct: any):number {
        let total = 0;
        for (let lp of listProduct) {
            total++;
        }
        return total;
    }


}