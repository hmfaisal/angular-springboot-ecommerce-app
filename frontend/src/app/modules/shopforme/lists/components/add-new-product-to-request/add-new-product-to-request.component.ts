import { ShoppingRequest } from './../../../../../model/shoppingRequest';
import { Component, OnInit, OnDestroy, Input, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { MatSnackBar } from '@angular/material';
import { ShoppingProductWrapper } from '../../../../../model/shoppingProductWrapper';
import { TranslateService } from '@ngx-translate/core';
import {
  RequestService,
  ShoppingProductService,
  ShopformeDataService
} from '../../../../../services';

@Component({
  selector: 'app-add-new-product-to-request',
  templateUrl: './add-new-product-to-request.component.html',
  styleUrls: ['./add-new-product-to-request.component.scss']
})
export class AddNewProductToRequestComponent implements OnInit, OnDestroy {

  submitted = false;
  error: string;
  success: string;
  private subscription = new Subscription();
  requests: any[] = [];
  selectedRequest: any;
  updatedRequest: any;
  page: number = 1;
  total: number;
  productImageUrl: string;

  constructor(
    private shoppingProductService: ShoppingProductService,
    private requestService: RequestService,
    private shopformeDataService: ShopformeDataService,
    private translate: TranslateService,
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<AddNewProductToRequestComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.subscription.add(translate.get('WRONG_ERROR').subscribe((res: string) => {
      this.error = res;
    }));
    this.subscription.add(translate.get('ADD_SUCCESSFUL').subscribe((result: string) => {
      this.success = result;
    }));
  }

  ngOnInit() {
    this.getMyRequests(this.page);
    this.getMyRequestsCount();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  onSelectionChange(entry) {
    this.selectedRequest = entry;
  }

  onSubmit() {

    const shoppingProductWrapper: any = {
      request: this.selectedRequest.shoppingRequest,
      shoppingProduct: this.data.shoppingProduct,
      product: this.data.product
    };
    this.submitted = true;
    this.subscription.add(this.shoppingProductService.saveShoppingProduct(shoppingProductWrapper)
      .subscribe(data => {
        this.getRequestById(this.selectedRequest.shoppingRequest.id);
        setTimeout(() => {
          this.updateRequest(this.updatedRequest);
        }, 3000);
      },
      error => {
        this.submitted = false;
        this.dialogRef.close();
        this.snackBar.open(this.error, 'X', {
          duration: 1000,
        });
      }));
  }

  updateRequest(request: any) {
    const shoppingRequest: any = {
      id: request.shoppingRequest.id,
      requestName: request.shoppingRequest.requestName,
      deliveryTime: request.shoppingRequest.deliveryTime,
      estimatedTotalPrice: this.calculatePrice(request),
      estimateTotalUnit: this.calculateTotalItem(request),
      shippingcost: this.calculateShippingCost(request),
      charge: this.calculateCharge(request)
    };

    this.subscription.add(this.requestService.updateRequest(shoppingRequest)
      .subscribe(data => {
        this.dialogRef.close();
        this.snackBar.open(this.success, 'X', {
          duration: 1000,
        });

        this.sendChange(true);
      },
      error => {
      }));
  }

  getMyRequests(page): void {
    this.subscription.add(this.requestService.getMyActiveRequest(page)
      .subscribe(data => {
        this.requests = data;
      },
      error => {
      }));
  }

  getMyRequestsCount(): void {
    this.subscription.add(this.requestService.getMyActiveRequestCount()
      .subscribe(data => {
        this.total = data;
      },
      error => {
      }));
  }

  getRequestById(id): void {
    this.subscription.add(this.requestService.getRequest(id)
    .subscribe(data => {
      this.updatedRequest = data;
    },
    error => {
    }));
  }

  imageDisplayCondition(shoppingProduct: any) {
    this.productImageUrl = '';
    for (let sp of shoppingProduct) {
      if (sp.productImageUrl != '') {
        this.productImageUrl = sp.productImageUrl;
        return true;
      }
    }
    return true;
  }

  calculatePrice(request: any): number {
    let price = this.requestService.calculatePrice(request.shoppingProduct);
    return price;
  }

  calculateTotalItem(request: any): number {
    let total = this.requestService.calculateTotalItem(request.shoppingProduct);
    return total;
  }

  calculateShippingCost(request: any): number {
    let cost = this.requestService.calculateShippingCost(request.shoppingProduct);
    return cost;
  }

  calculateCharge(request: any): number {
    let cost = this.requestService.calculateShippingCost(request.shoppingProduct);
    let charge = this.requestService.calculateCharge(cost);
    return charge;
  }

  sendChange(isAdded: boolean) {
    this.shopformeDataService.changeAddRequestTrigger(isAdded);
  }

  onDialogClose() {
    this.dialogRef.close();
  }

}
