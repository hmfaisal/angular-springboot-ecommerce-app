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
  selector: 'app-product-by-shop',
  templateUrl: './product-by-shop.component.html',
  styleUrls: ['./product-by-shop.component.scss']
})
export class ProductByShopComponent implements OnInit, OnDestroy {

  @Input() shop: string;
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
        this.shop = value;
        this.getProductsCount(this.shop, this.country);
        this.getProducts(this.page, this.shop, this.country);
        this.getMyRequestsCount();
      }
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.menuDataService.clearData();
  }

  getProducts(page, shop, country): void {
    this.spinnerService.show();
    this.subscription.add(this.productService.getByShop(shop, page, country)
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

  getProductsCount(shop, country): void {

    this.subscription.add(this.productService.getByShopCount(shop, country)
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
