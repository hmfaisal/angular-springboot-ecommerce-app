import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import {
  RequestService,
  ShoppingProductService,
  GoingShoppingDataService
} from '../../../../../services';

@Component({
  selector: 'app-request-active',
  templateUrl: './request-active.component.html',
  styleUrls: ['./request-active.component.scss']
})
export class RequestActiveComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  status: string = "REQUESTED";
  source: string = "shopper";
  requests: any[] = [];
  page: number = 1;
  total: number;
  @Input() country;
  city = "";
  constructor(
    private shoppingProductService: ShoppingProductService,
    private requestService: RequestService,
    private goingShoppingDataService: GoingShoppingDataService,
    private spinnerService: Ng4LoadingSpinnerService
  ) { }


  ngOnInit() {
    this.getRequests(this.page, this.status, this.country, this.city);
    this.getRequestCount(this.status, this.country, this.city);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getRequests(page: number, status: any, country: String, city: String): void {

    this.spinnerService.show();
    this.subscription.add(this.requestService.getRequestActive(page, status, country, city)
      .subscribe(data => {
        this.requests = data;
        this.spinnerService.hide();
      },
      error => {
        this.spinnerService.hide();
      },
      () => {
        this.spinnerService.hide();
      }));
  }

  getRequestCount(status: any, country: String, city: String): void {
    this.subscription.add(this.requestService.getRequestActiveCount(status, country, city)
      .subscribe(data => {
        this.total = data;
      },
      error => {
      }));
  }

}
