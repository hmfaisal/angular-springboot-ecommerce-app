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
  selector: 'app-my-request-active',
  templateUrl: './my-request-active.component.html',
  styleUrls: ['./my-request-active.component.scss']
})
export class MyRequestActiveComponent implements OnInit, OnDestroy {

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
    this.getMyActiveRequests(this.page);
    this.getMyActiveRequestCount();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getMyActiveRequests(page): void {
    this.spinnerService.show();
    this.subscription.add(this.requestService.getMyActiveRequest(page)
      .subscribe(data => {
        this.requests = data;
        this.spinnerService.hide();
      },
      error => {
        this.spinnerService.hide();
      }));
  }

  getMyActiveRequestCount(): void {
    this.subscription.add(this.requestService.getMyActiveRequestCount()
    .subscribe(data => {
      this.total = data;
    },
    error => {
    }));
  }

}
