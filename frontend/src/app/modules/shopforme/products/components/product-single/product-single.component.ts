import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Subscription } from 'rxjs/Subscription';
import {
  ProductService,
} from '../../../../../services';
import { DialogService } from '../../../../../services/dialog.service';
import { ListProduct } from '../../../../../model/listProduct';

@Component({
  selector: 'app-product-single',
  templateUrl: './product-single.component.html',
  styleUrls: ['./product-single.component.scss']
})
export class ProductSingleComponent implements OnInit, OnDestroy {

  @Input() singleProduct: any;
  @Input() requestTotal:any;
  clickedProduct: any;
  result: any;
  product: any;
  private subscription = new Subscription();

  constructor(
    private productService: ProductService,
    private dialogService: DialogService,
  ) { }

  ngOnInit() {
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


  addToList(product: any) {
    this.product = product;
    const listProduct: any={
      productName: product.productName,
      productImageUrl:product.productImageUrl,
      estimateUnitPrice:product.estimateUnitPrice,
      estimateUnit:product.unit,
      unitType:product.unitType
    };
    this.subscription.add(this.dialogService
      .addProductToList(listProduct,this.product)
      .subscribe(res => this.result = res));
  }

  addToRequest(product: any) {
    this.product = product;
    const shoppingProduct: any={
      productName: product.productName,
      productImageUrl:product.productImageUrl,
      estimateUnitPrice:product.estimateUnitPrice,
      estimateUnit:product.unit,
      unitType:product.unitType
    };
    this.subscription.add(this.dialogService
      .addProductToRequest(shoppingProduct,this.product)
      .subscribe(res => this.result = res));
  }

}
