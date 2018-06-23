import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import {
  ProductService,
  RequestService
} from '../../../../../services';

@Component({
  selector: 'app-product-all',
  templateUrl: './product-all.component.html',
  styleUrls: ['./product-all.component.scss']
})
export class ProductAllComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  products: any[] = [];
  total: any;
  requestTotal: any;
  page: number = 1;
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

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  getProducts(page, country): void {
    this.spinnerService.show();
    this.subscription.add(this.productService.getAll(page, country)
      .subscribe(data => {
        this.products = data;
        this.spinnerService.hide();
      },
      error => {
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
