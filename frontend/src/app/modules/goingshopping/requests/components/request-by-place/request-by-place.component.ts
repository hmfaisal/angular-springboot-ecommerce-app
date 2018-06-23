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
  selector: 'app-request-by-place',
  templateUrl: './request-by-place.component.html',
  styleUrls: ['./request-by-place.component.scss']
})
export class RequestByPlaceComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  hasData: any;
  status: string = "REQUESTED";
  source: string = "shopper";
  requests: any[] = [];
  page: number = 1;
  total: number;
  address: any;

  @Input() country;
  @Input() city;

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
        this.city = value.city;
        this.country = value.country;
        this.getRequests(this.page, this.status, this.country, this.city);
        this.getRequestCount(this.status, this.country, this.city);
      }
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.menuDataService.clearData();
  }

  getRequests(page: number, status: any, country: String, city: String): void {
    //this.spinnerService.show();
    this.subscription.add(this.requestService.getRequestActive(page, status, country, city)
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

  getRequestCount(status: any, country: String, city: String): void {
    this.subscription.add(this.requestService.getRequestActiveCount(status, country, city)
      .subscribe(data => {
        this.total = data;
      },
      error => {
      }));
  }

}
