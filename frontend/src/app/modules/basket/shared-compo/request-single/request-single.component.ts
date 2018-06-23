import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Subscription } from 'rxjs/Subscription';
import {
  MenudataService,
  RequestService,
  ShoppingProductService,
  BasketDataService
} from '../../../../services';

@Component({
  selector: 'app-request-single',
  templateUrl: './request-single.component.html',
  styleUrls: ['./request-single.component.scss']
})
export class RequestSingleComponent implements OnInit, OnDestroy  {

  @Input() singleRequest: any;
  @Input() source: string;
  productImageUrl: string;
  private subscription = new Subscription();
  selectedView:string;
  selectedRequest:any;
  totalPrice:number;

  constructor(
    private menuDataService:MenudataService,
    private shoppingProductService: ShoppingProductService,
    private requestService: RequestService,
    private basketDataService: BasketDataService,
  ) { }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  imageDisplayCondition(shoppingProduct: any) {
    this.productImageUrl = '';
    for (let sp of shoppingProduct) {
      if (sp.productImageUrl != '') {
        this.productImageUrl = sp.productImageUrl;
        return true;
      }
    }
    return true;
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
