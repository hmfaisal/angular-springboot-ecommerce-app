import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable()
export class RequestService {

    params: any;
    
    constructor(
        private apiService: ApiService,
        private config: ConfigService
    ) { }

    calculatePrice(shoppingProduct: any):number {
        let price = 0;
        for (let sp of shoppingProduct) {
            //let amount = sp.estimateUnit;
            //let temprice= sp.estimateUnitPrice*amount;
            price = price+sp.estimateUnitPrice;
        }
        return price;
    }

    calculateShippingCost(shoppingProduct: any):number {
        let price = 4;
        /*
        for (let sp of shoppingProduct) {
            let amount = sp.estimateUnit;
            let temprice= sp.estimateUnitPrice*amount;
            price = price+temprice;
        }
        */
        return price;
    }

    calculateCharge(cost: number):number {
        let price = 0;
        return price;
    }

    calculateTotalItem(shoppingProduct: any):number {
        let total = 0;
        for (let sp of shoppingProduct) {
            total++;
        }
        return total;
    }

    getRequest(id: string) {
        return this.apiService.get(this.config.request_url + '/' + id);
    }

    getMyAllRequest() {
        return this.apiService.get(this.config.myAllRequest_url);
    }

    getMyActiveRequest(pageNumber: number) {
        this.params = {
            p: pageNumber,
        };
        return this.apiService.get(this.config.myActiveRequest_url, this.params);
    }

    getMyActiveRequestCount() {
        return this.apiService.get(this.config.myActiveRequestCount_url);
    }

    getMyArchivedRequest(pageNumber: number) {
        this.params = {
            p: pageNumber,
        };
        return this.apiService.get(this.config.myArchivedRequest_url, this.params);
    }

    getMyArchivedRequestCount() {
        return this.apiService.get(this.config.myArchivedRequestCount_url);
    }

    getMyDeliveredRequest(pageNumber: number) {
        this.params = {
            p: pageNumber,
        };
        return this.apiService.get(this.config.myDeliveredRequest_url, this.params);
    }

    getMyDeliveredRequestCount() {
        return this.apiService.get(this.config.myDeliveredRequestCount_url);
    }

    getMyConfirmedRequest(pageNumber: number) {
        this.params = {
            p: pageNumber,
        };
        return this.apiService.get(this.config.myConfirmedRequest_url, this.params);
    }

    getMyConfirmedRequestCount() {
        return this.apiService.get(this.config.myConfirmedRequestCount_url);
    }

    getRequestActive(pageNumber: number,status:String,country:String,city:String) {
        this.params = {
            p: pageNumber,
            requestStatus:status,
            country:country,
            city:city
        };
        return this.apiService.get(this.config.requestActive_url, this.params);
    }

    getRequestActiveCount(status:String,country:String,city:String) {
        this.params = {
            requestStatus:status,
            country:country,
            city:city
        };
        return this.apiService.get(this.config.requestActiveCount_url, this.params);
    }

    getRequestActiveAll(status:String,country:String,city:String) {
        this.params = {
            requestStatus:status,
            country:country,
            city:city
        };
        return this.apiService.get(this.config.requestActive_url, this.params);
    }

    getRequestByTime(pageNumber: number,toTime:any,country:String,city:String) {
        this.params = {
            p: pageNumber,
            toTime:toTime,
            country:country,
            city:city
        };
        return this.apiService.get(this.config.requestByTime_url, this.params);
    }

    getRequestByTimeCount(toTime:any,country:String,city:String) {
        this.params = {
            toTime:toTime,
            country:country,
            city:city
        };
        return this.apiService.get(this.config.requestByTimeCount_url, this.params);
    }

    getRequestByDistance(pageNumber: number,lng:String,lat:String,dist:String) {
        this.params = {
            p: pageNumber,
            lng:lng,
            lat:lat,
            dist:dist
        };
        return this.apiService.get(this.config.requestByDistance_url, this.params);
    }

    getRequestByRange(pageNumber: number,min:any,max:any,country:String,city:String) {
        this.params = {
            p: pageNumber,
            min:min,
            max:max,
            country:country,
            city:city
        };
        return this.apiService.get(this.config.requestByRange_url, this.params);
    }

    getRequestByRangeCount(min:any,max:any,country:String,city:String) {
        this.params = {
            min:min,
            max:max,
            country:country,
            city:city
        };
        return this.apiService.get(this.config.requestByRangeCount_url, this.params);
    }

    saveRequest(requestwrapper) {
        return this.apiService.post(this.config.requestSave_url, requestwrapper);
    }

    updateRequest(request) {
        return this.apiService.post(this.config.requestUpdate_url, request);
    }

    deleteRequest(id: number) {
        return this.apiService.delete(this.config.requestRemove_url+ '/' + id);
    }

    confirmRequest(request) {
        return this.apiService.post(this.config.requestConfirm_url, request);
    }

    getDeliveryAddress(id: string) {
        return this.apiService.get(this.config.deliveryAddress_url + '/' + id);
    }

    updateDeliveryAddress(address) {
        return this.apiService.post(this.config.deliveryAddressUpdate_url, address);
    }

}
