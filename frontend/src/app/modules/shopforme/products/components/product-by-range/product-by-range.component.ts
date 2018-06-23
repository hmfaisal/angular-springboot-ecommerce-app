import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Router } from '@angular/router';
import {
  ProductService,
  RequestService
} from '../../../../../services';
import { MenudataService } from '../../../../../services/menudata.service';
import { Subscription } from 'rxjs/Subscription';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

@Component({
  selector: 'app-product-by-range',
  templateUrl: './product-by-range.component.html',
  styleUrls: ['./product-by-range.component.scss']
})
export class ProductByRangeComponent implements OnInit, OnDestroy {

  @Input() min: number;
  @Input() max: number;
  private subscription = new Subscription();
  products: any[] = [];
  total: any;
  requestTotal: any;
  page: number = 1;
  loading: boolean;

  @Input() country;

  constructor(
    private productService: ProductService,
    private requestService: RequestService,
    private router: Router,
    private menuDataService: MenudataService,
    private spinnerService: Ng4LoadingSpinnerService
  ) {

  }

  ngOnInit() {
    this.subscription.add(this.menuDataService.currentData.subscribe(value => {
      if(value!='empty'){
        this.min = value[0];
        this.max = value[1];
        this.getProductsCount(this.min, this.max, this.country);
        this.getProducts(this.page, this.min, this.max, this.country);
        this.getMyRequestsCount();
      }
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.menuDataService.clearData();
  }

  getProducts(page,min, max, country): void {
    this.subscription.add(this.productService.getByPriceRange(min, max, page, country)
      .subscribe(data => {
        this.products = data;
      },
      error => {
      },
      () => {
      }));
  }

  getProductsCount(min, max, country): void {
    this.subscription.add(this.productService.getByPriceRangeCount(min, max, country)
      .subscribe(data => {
        this.total = data;
      },
      error => {
      }));
  }

  getMyRequestsCount(): void {
    this.subscription.add(this.requestService.getMyActiveRequestCount()
      .subscribe(data => {
        this.requestTotal = data;
      },
      error => {
      }));
  }

}
