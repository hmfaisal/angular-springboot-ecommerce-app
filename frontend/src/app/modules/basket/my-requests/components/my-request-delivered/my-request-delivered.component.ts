import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import {
  RequestService,
  ShoppingProductService,
  BasketDataService
} from '../../../../../services';

@Component({
  selector: 'app-my-request-delivered',
  templateUrl: './my-request-delivered.component.html',
  styleUrls: ['./my-request-delivered.component.scss']
})
export class MyRequestDeliveredComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  requests: any[] = [];
  source: string = "requester";
  page: number = 1;
  total: number;

  constructor(
    private shoppingProductService: ShoppingProductService,
    private requestService: RequestService,
    private basketDataService: BasketDataService,
    private spinnerService: Ng4LoadingSpinnerService
  ) {

  }

  ngOnInit() {
    this.getMyDeliveredRequests(this.page);
    this.getMyDeliveredRequestCount();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getMyDeliveredRequests(page): void {
    this.spinnerService.show();
    this.subscription.add(this.requestService.getMyDeliveredRequest(page)
      .subscribe(data => {
        this.requests = data;
        this.spinnerService.hide();
      },
      error => {
        this.spinnerService.hide();
      }));
  }

  getMyDeliveredRequestCount(): void {
    this.subscription.add(this.requestService.getMyDeliveredRequestCount()
    .subscribe(data => {
      this.total = data;
    },
    error => {
    }));
  }

}
