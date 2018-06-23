import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Router } from '@angular/router';
import {
  ProductService,
  RequestService
} from '../../../../../services';
import { MenudataService } from '../../../../../services/menudata.service';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-product-by-category',
  templateUrl: './product-by-category.component.html',
  styleUrls: ['./product-by-category.component.scss']
})
export class ProductByCategoryComponent implements OnInit, OnDestroy {

  @Input() category: string;
  private subscription = new Subscription();
  products: any[] = [];
  total: any;
  page: number = 1;
  loading: boolean;
  requestTotal: any;

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
      if(value!="empty"){
        this.category=value;
        this.getProductsCount(this.category, this.country);
        this.getProducts(this.page, this.category,  this.country);
        this.getMyRequestsCount();
      }
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.menuDataService.clearData();
  }

  getProducts(page,category,country): void {
    //this.spinnerService.show();
    this.subscription.add(this.productService.getByCategory(category, page, country)
      .subscribe(data => {
        this.products = data;
        //this.spinnerService.hide();
      },
      error => {
        //this.spinnerService.hide();
      },
      () => {
        //this.spinnerService.hide();
      }));
  }

  getProductsCount(category, country): void {
    this.subscription.add(this.productService.getByCategoryCount(category, country)
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
