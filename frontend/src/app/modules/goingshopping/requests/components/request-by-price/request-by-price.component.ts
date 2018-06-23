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
  selector: 'app-request-by-price',
  templateUrl: './request-by-price.component.html',
  styleUrls: ['./request-by-price.component.scss']
})
export class RequestByPriceComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  @Input() min: number;
  @Input() max: number;
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
      if(value!='empty'){
        this.min = value[0];
        this.max = value[1];
        this.getRequests(this.page, this.min, this.max, this.country, this.city);
        this.getRequestCount(this.min, this.max, this.country, this.city);
      }
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.menuDataService.clearData();
  }

  getRequests(page,min, max, country: String, city: String): void {
    this.subscription.add(this.requestService.getRequestByRange(page, min, max, country, city)
      .subscribe(data => {
        this.requests = data;
      },
      error => {
      },
      () => {
      }));
  }

  getRequestCount(min, max, country: String, city: String): void {
    this.subscription.add(this.requestService.getRequestByRangeCount(min, max, country, city)
      .subscribe(data => {
        this.total = data;
      },
      error => {
      }));
  }

}
