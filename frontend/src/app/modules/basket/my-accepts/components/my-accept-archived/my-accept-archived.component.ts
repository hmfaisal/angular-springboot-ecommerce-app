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
  selector: 'app-my-accept-archived',
  templateUrl: './my-accept-archived.component.html',
  styleUrls: ['./my-accept-archived.component.scss']
})
export class MyAcceptArchivedComponent implements OnInit, OnDestroy {

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
    this.getMyArchivedAccepts(this.page);
    this.getMyArchivedAcceptCount();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getMyArchivedAccepts(page): void {
    this.spinnerService.show();
    this.subscription.add(this.acceptService.getMyArchivedAccept(page)
      .subscribe(data => {
        this.requests = data;
        this.spinnerService.hide();
      },
      error => {
        this.spinnerService.hide();
      }));
  }

  getMyArchivedAcceptCount(): void {
    this.subscription.add(this.acceptService.getMyArchivedAcceptCount()
      .subscribe(data => {
        this.total = data;
      },
      error => {
      }));
  }

}
