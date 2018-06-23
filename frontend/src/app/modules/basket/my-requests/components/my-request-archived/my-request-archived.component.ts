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
  selector: 'app-my-request-archived',
  templateUrl: './my-request-archived.component.html',
  styleUrls: ['./my-request-archived.component.scss']
})
export class MyRequestArchivedComponent implements OnInit, OnDestroy {

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
    this.getMyArchivedRequests(this.page);
    this.getMyArchivedRequestCount();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getMyArchivedRequests(page): void {
    this.spinnerService.show();
    this.subscription.add(this.requestService.getMyArchivedRequest(page)
      .subscribe(data => {
        this.requests = data;
        this.spinnerService.hide();
      },
      error => {
        this.spinnerService.hide();
      }));
  }

  getMyArchivedRequestCount(): void {
    this.subscription.add(this.requestService.getMyArchivedRequestCount()
    .subscribe(data => {
      this.total = data;
    },
    error => {
    }));
  }

}
