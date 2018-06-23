import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import {
  ProductService,
  RequestService
} from '../../../../../services';
import { Subscription } from 'rxjs/Subscription';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

@Component({
  selector: 'app-product-by-high',
  templateUrl: './product-by-high.component.html',
  styleUrls: ['./product-by-high.component.scss']
})
export class ProductByHighComponent implements OnInit, OnDestroy {

  products: any[] = [];
  total: any;
  page: number = 1;
  loading: boolean;
  private subscription = new Subscription();
  requestTotal: any;

  @Input() country;

  constructor(
    private productService: ProductService,
    private requestService: RequestService,
    private router: Router,
    private spinnerService: Ng4LoadingSpinnerService
  ) {
  }

  ngOnInit() {
    this.getProductsCount(this.country);
    this.getProducts(this.page, this.country);
    this.getMyRequestsCount();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getProducts(page, country): void {
    this.spinnerService.show();
    this.subscription.add(this.productService.getByPriceHigh(page, country)
      .subscribe(data => {
        this.products = data;
        this.spinnerService.hide();
      },
      error => {
        this.spinnerService.hide();
      },
      () => {
        this.spinnerService.hide();
      }));
  }

  getProductsCount(country): void {
    this.subscription.add(this.productService.getAllCount(country)
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
