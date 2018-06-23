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
  selector: 'app-my-request-confirmed',
  templateUrl: './my-request-confirmed.component.html',
  styleUrls: ['./my-request-confirmed.component.scss']
})
export class MyRequestConfirmedComponent implements OnInit, OnDestroy {

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
    this.getMyConfirmedRequests(this.page);
    this.getMyConfirmedRequestCount();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getMyConfirmedRequests(page): void {
    this.spinnerService.show();
    this.subscription.add(this.requestService.getMyConfirmedRequest(page)
      .subscribe(data => {
        this.requests = data;
        this.spinnerService.hide();
      },
      error => {
        this.spinnerService.hide();
      }));
  }

  getMyConfirmedRequestCount(): void {
    this.subscription.add(this.requestService.getMyConfirmedRequestCount()
    .subscribe(data => {
      this.total = data;
    },
    error => {
    }));
  }

}
