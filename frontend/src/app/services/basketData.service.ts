import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class BasketDataService {

    constructor() { }

    private idSource = new BehaviorSubject<any>("empty");
    currentId = this.idSource.asObservable();
    private source = new BehaviorSubject<any>("empty");
    currentSource = this.source.asObservable();
    private updateRequestSource = new BehaviorSubject<any>("empty");
    currentupdateRequest= this.updateRequestSource.asObservable();
    private adrIdSource = new BehaviorSubject<any>("empty");
    currentAdrId = this.adrIdSource.asObservable();


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

    changeUpdateRequestId(id: string) {
        this.updateRequestSource.next(id);
    }

    clearUpdateRequestId() {
        this.updateRequestSource.next("empty");
    }

    changeAdrId(id: string) {
        this.adrIdSource.next(id);
    }

    clearAdrId() {
        this.adrIdSource.next("empty");
    }

    clear(){
        this.clearId();
        this.clearSource();
        this.clearUpdateRequestId();
        this.clearAdrId();
    }
}