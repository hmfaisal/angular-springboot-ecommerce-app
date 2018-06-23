import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import {
  RequestService,
  ShoppingProductService,
  GoingShoppingDataService,
  MenudataService
} from '../../../../../services';

@Component({
  selector: 'app-request-by-time',
  templateUrl: './request-by-time.component.html',
  styleUrls: ['./request-by-time.component.scss']
})
export class RequestByTimeComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  @Input() time: any;
  @Input() country;
  @Input() city;
  source: string = "shopper";
  requests: any[] = [];
  page: number = 1;
  total: number;

  constructor(
    private shoppingProductService: ShoppingProductService,
    private requestService: RequestService,
    private goingShoppingDataService: GoingShoppingDataService,
    private menuDataService: MenudataService,
    private spinnerService: Ng4LoadingSpinnerService
  ) { }


  ngOnInit() {
    this.subscription.add(this.menuDataService.currentData.subscribe(value => {
      if (value != "empty") {
        this.time = value;
        this.getRequests(this.page, this.time, this.country, this.city);
        this.getRequestCount(this.time, this.country, this.city);
      }
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.menuDataService.clearData();
  }

  getRequests(page, time: any, country: String, city: String): void {
    //this.spinnerService.show();
    this.subscription.add(this.requestService.getRequestByTime(page, time, country, city)
      .subscribe(data => {
        this.requests = data;
        //this.spinnerService.hide();
      },
      error => {
        //this.spinnerService.hide();
      },
      () => {
        //this.spinnerService.hide();
      }));
  }

  getRequestCount(time: any, country: String, city: String): void {
    this.subscription.add(this.requestService.getRequestByTimeCount(time, country, city)
      .subscribe(data => {
        this.total = data;
      },
      error => {
      }));
  }

}
