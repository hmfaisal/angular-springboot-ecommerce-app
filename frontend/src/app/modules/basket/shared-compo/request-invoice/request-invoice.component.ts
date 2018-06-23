import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { Observable, Subject } from 'rxjs';

import {
  RequestService,
  ShoppingProductService,
  BasketDataService
} from '../../../../services';

@Component({
  selector: 'app-request-invoice',
  templateUrl: './request-invoice.component.html',
  styleUrls: ['./request-invoice.component.scss']
})
export class RequestInvoiceComponent implements OnInit, OnDestroy {

  @Input() selectedRequest: any;
  private subscription = new Subscription();
  totalPrice:number;

  constructor(
    private requestService: RequestService,
    private shoppingProductService: ShoppingProductService,
    private basketDataService: BasketDataService,
  ) { 

  }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  calculateTotalPrice(price:number,cost:number):number{
    this.totalPrice= price+cost;
    return this.totalPrice;
  }


}
