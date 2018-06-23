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
  selector: 'app-product-by-name',
  templateUrl: './product-by-name.component.html',
  styleUrls: ['./product-by-name.component.scss']
})
export class ProductByNameComponent implements OnInit, OnDestroy {

  @Input() name: string;
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
        this.name = value;
        this.getProductsCount(this.name, this.country);
        this.getProducts(this.page,this.name, this.country);
        this.getMyRequestsCount();
      }
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.menuDataService.clearData();
  }

  getProducts(page,name,country): void {
    this.spinnerService.show();
    this.subscription.add(this.productService.getByName(name, page, country)
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

  getProductsCount(name, country): void {
    this.subscription.add(this.productService.getByNameCount(name, country)
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
