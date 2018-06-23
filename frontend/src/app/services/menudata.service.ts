import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class MenudataService {

    private viewSource = new BehaviorSubject<string>("empty");
    currentView = this.viewSource.asObservable();
    private menuTriggerSource = new BehaviorSubject<boolean>(false);
    currentMenuTrigger = this.menuTriggerSource.asObservable();
    private moreMenuTriggerSource = new BehaviorSubject<boolean>(false);
    currentMoreMenuTrigger = this.moreMenuTriggerSource.asObservable();
    private fmenuTriggerSource = new BehaviorSubject<boolean>(false);
    currentFMenuTrigger = this.fmenuTriggerSource.asObservable();
    private dataSource = new BehaviorSubject<any>("empty");
    currentData = this.dataSource.asObservable();

    private idSource = new BehaviorSubject<any>("empty");
    currentId = this.idSource.asObservable();
    private source = new BehaviorSubject<any>("empty");
    currentSource = this.source.asObservable();
    
    constructor(
    ) { }

    changeView(view:string) {
        this.viewSource.next(view);
    }

    clearView() {
        this.viewSource.next("empty");
    }
    
    changeMenuTrigger(isShow:boolean) {
        this.menuTriggerSource.next(isShow);
    }

    clearMenuTrigger() {
        this.menuTriggerSource.next(false);
    }

    changeFMenuTrigger(isShow:boolean) {
        this.fmenuTriggerSource.next(isShow);
    }

    clearFMenuTrigger() {
        this.fmenuTriggerSource.next(false);
    }

    changeMoreMenuTrigger(isShow:boolean) {
        this.moreMenuTriggerSource.next(isShow);
    }

    clearMoreMenuTrigger() {
        this.moreMenuTriggerSource.next(false);
    }

    changeData(data:any) {
        this.dataSource.next(data);
    }

    clearData() {
        this.dataSource.next("empty");
    }

    changeId(id: string) {
        this.idSource.next(id);
    }

    clearId() {
        this.idSource.next("empty");
    }

    changeSource(id: string) {
        this.source.next(id);
    }

    clearSource() {
        this.source.next("empty");
    }

    clear(){
        this.clearView();
        this.clearMenuTrigger();
        this.clearFMenuTrigger();
        this.clearMoreMenuTrigger();
        this.clearData();
        this.clearId();
        this.clearSource();
    }


}