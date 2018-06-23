import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class ShareDataService {

    private dateAddSource = new BehaviorSubject<any>("empty");
    currentAddedDate = this.dateAddSource.asObservable();
    private adrAddSource = new BehaviorSubject<any>("empty");
    currentAddedAdr = this.adrAddSource.asObservable();
    private delAddressSource = new BehaviorSubject<any>("empty");
    currentDelAddress = this.delAddressSource.asObservable();
    private setTimeView = new BehaviorSubject<boolean>(false);
    currentSetTimeView = this.setTimeView.asObservable();
    private setDelAdrView = new BehaviorSubject<boolean>(false);
    currentDelAdrView= this.setDelAdrView.asObservable();
    private countrySource = new BehaviorSubject<any>("empty");
    currentCountry= this.countrySource.asObservable();
    private citySource = new BehaviorSubject<any>("empty");
    currentCity= this.citySource.asObservable();

    constructor() { }

    isEmptyObject(obj) {
        for(let prop in obj) {
           if (obj.hasOwnProperty(prop)) {
              return false;
           }
        }
        return true;
    }

    changeSetTimeView(isAdded: boolean) {
        this.setTimeView.next(isAdded);
    }

    clearSetTimeView() {
        this.setTimeView.next(false);
    }

    changeSetDelAdrView(isAdded: boolean) {
        this.setDelAdrView.next(isAdded);
    }

    clearSetDelAdrView() {
        this.setDelAdrView.next(false);
    }

    changeDateAdd(data: any) {
        this.dateAddSource.next(data);
    }

    clearDateAdd() {
        this.dateAddSource.next("empty");
    }

    changeAdrAdd(data: any) {
        this.adrAddSource.next(data);
    }

    clearAdrAdd() {
        this.adrAddSource.next("empty");
    }

    changeDelAddress(data: any) {
        this.delAddressSource.next(data);
    }

    clearDelAddress() {
        this.delAddressSource.next("empty");
    }

    changeCountry(data: any) {
        this.countrySource.next(data);
    }

    clearCountry() {
        this.countrySource.next("empty");
    }

    changeCity(data: any) {
        this.citySource.next(data);
    }

    clearCity() {
        this.citySource.next("empty");
    }

    clear(){
        this.clearSetTimeView();
        this.clearSetDelAdrView();
        this.clearDateAdd();
        this.clearAdrAdd();
        this.clearDelAddress();
        this.clearCountry();
        this.clearCity();
    }

}