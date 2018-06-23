import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { DatePipe } from '@angular/common';
import { Subscription } from 'rxjs/Subscription';
import {
  MenudataService,
  RequestService,
  ShoppingProductService,
  BasketDataService
} from '../../../../services';

@Component({
  selector: 'app-request-map-single',
  templateUrl: './request-map-single.component.html',
  styleUrls: ['./request-map-single.component.scss']
})
export class RequestMapSingleComponent implements OnInit, OnDestroy {

  @Input() singleRequest: any;
  @Input() source: string;
  productImageUrl: string;
  private subscription = new Subscription();
  selectedView:string;
  selectedRequest:any;
  totalPrice:any;
  zoom: number = 11;
  mapUrl:any;

  constructor(
    private menuDataService:MenudataService,
    private shoppingProductService: ShoppingProductService,
    private requestService: RequestService,
    private basketDataService: BasketDataService,
    private datePipe: DatePipe,
  ) { }

  ngOnInit() {
    
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  calculateTotalPrice(price:number,cost:number):number{
    this.totalPrice= price+cost;
    return this.totalPrice;
  }

  selectRequest(request:any) {
    this.selectedView = "REQUESTSINGLE";
    let req = request.shoppingRequest.id;
    this.selectedRequest = String(req);
    this.sendData(this.selectedRequest);
    this.sendView(this.selectedView);
    
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }

  sendData(selectedId:string){
    this.menuDataService.changeId(selectedId);
    this.menuDataService.changeSource(this.source);
  }

}
