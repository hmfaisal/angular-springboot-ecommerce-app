import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class ShopformeDataService {

    private listSource = new BehaviorSubject<any>("empty");
    currentList = this.listSource.asObservable();
    private unitSource = new BehaviorSubject<number>(-1);
    currentUnit = this.unitSource.asObservable();
    private priceSource = new BehaviorSubject<number>(-1);
    currentPrice = this.priceSource.asObservable();

    private unlistedSource = new BehaviorSubject<any>("empty");
    currentUnlisted = this.unlistedSource.asObservable();
    private addListTriggerSource = new BehaviorSubject<boolean>(false);
    currentAddListTrigger = this.addListTriggerSource.asObservable();
    private addRequestTriggerSource = new BehaviorSubject<boolean>(false);
    currentAddRequestTrigger = this.addRequestTriggerSource.asObservable();
    private idSource = new BehaviorSubject<any>("empty");
    currentId = this.idSource.asObservable();
    private editListTriggerSource = new BehaviorSubject<boolean>(false);
    currentEditListTrigger = this.editListTriggerSource.asObservable();

    constructor() { }

    changeList(data:any) {
        this.listSource.next(data);
    }

    clearList() {
        this.listSource.next("empty");
    }
    
    changeUnit(data:number) {
        this.unitSource.next(data);
    }

    clearUnit() {
        this.unitSource.next(-1);
    }

    changePrice(data:number) {
        this.priceSource.next(data);
    }

    clearPrice() {
        this.priceSource.next(-1);
    }

    changeUnlisted(data:any) {
        this.unlistedSource.next(data);
    }

    clearUnlisted() {
        this.unlistedSource.next("empty");
    }

    changeAddListrigger(isAdded: boolean) {
        this.addListTriggerSource.next(isAdded);
    }

    clearAddListTrigger() {
        this.addListTriggerSource.next(false);
    }

    changeAddRequestTrigger(isAdded: boolean) {
        this.addRequestTriggerSource.next(isAdded);
    }

    clearAddRequestTrigger() {
        this.addRequestTriggerSource.next(false);
    }

    changeEditListrigger(isEdited: boolean) {
        this.editListTriggerSource.next(isEdited);
    }

    clearEditListTrigger() {
        this.editListTriggerSource.next(false);
    }

    changeId(id: string) {
        this.idSource.next(id);
    }

    clearId() {
        this.idSource.next("empty");
    }

    clear(){
        this.clearList();
        this.clearUnit();
        this.clearPrice();
        this.clearUnlisted();
        this.clearAddListTrigger();
        this.clearAddRequestTrigger();
        this.clearEditListTrigger();
        this.clearId();
    }

}