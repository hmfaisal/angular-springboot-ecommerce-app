import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

import {
  AcceptService,
  RequestService,
  ShoppingProductService,
  BasketDataService
} from '../../../../../services';

@Component({
  selector: 'app-my-accept-active',
  templateUrl: './my-accept-active.component.html',
  styleUrls: ['./my-accept-active.component.scss']
})
export class MyAcceptActiveComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  requests: any[] = [];
  source: string = "shopper"
  page: number = 1;
  total: number;

  constructor(
    private shoppingProductService: ShoppingProductService,
    private acceptService: AcceptService,
    private requestService: RequestService,
    private basketDataService: BasketDataService,
    private spinnerService: Ng4LoadingSpinnerService
  ) {

  }

  ngOnInit() {
    this.getMyActiveAccepts(this.page);
    this.getMyActiveAcceptCount();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getMyActiveAccepts(page): void {
    this.spinnerService.show();
    this.subscription.add(this.acceptService.getMyActiveAccept(page)
      .subscribe(data => {
        this.requests = data;
        this.spinnerService.hide();
      },
      error => {
        this.spinnerService.hide();
      }));
  }

  getMyActiveAcceptCount(): void {
    this.subscription.add(this.acceptService.getMyActiveAcceptCount()
      .subscribe(data => {
        this.total = data;
      },
      error => {
      }));
  }

}
