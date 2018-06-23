import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { CustomValidators } from 'ng2-validation';
import { Observable, Subject } from 'rxjs';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBar } from '@angular/material';
import { UNITS } from '../../../../model/unitList';

import { DialogService } from '../../../../services/dialog.service';
import {
  RequestService,
  ShoppingProductService,
  BasketDataService
} from '../../../../services';

@Component({
  selector: 'app-request-product',
  templateUrl: './request-product.component.html',
  styleUrls: ['./request-product.component.scss']
})
export class RequestProductComponent implements OnInit, OnDestroy {

  @Input() shoppingProduct: any;
  @Input() shoppingRequest: any;
  @Input() source: string;
  error: string;
  success: string;
  delete: string;
  form: FormGroup;
  submitted = false;
  isRequesterFormView : boolean = false;
  isShopperFormView : boolean = false;
  isPermit: boolean;
  totalEstPrice:number;
  totalActPrice:number;
  units=UNITS;
  private subscription = new Subscription();

  constructor(
    private formBuilder: FormBuilder,
    private requestService: RequestService,
    private shoppingProductService: ShoppingProductService,
    private basketDataService: BasketDataService,
    private translate: TranslateService,
    public snackBar: MatSnackBar,
    private dialogService: DialogService,
  ) { 
    this.subscription.add(translate.get('WRONG_ERROR').subscribe((res: string) => {
      this.error = res;
    }));
    this.subscription.add(translate.get('EDIT_SUCCESSFUL').subscribe((result: string) => {
      this.success = result;
    }));
    this.subscription.add(translate.get('DELETE_SUCCESSFUL').subscribe((result: string) => {
      this.delete = result;
    }));
  }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  onRequesterEdit() {
    this.isRequesterFormView = true;
    this.form = this.formBuilder.group({
      id: [this.shoppingProduct.id],
      productName: [this.shoppingProduct.productName, Validators.compose([Validators.required])],
      productImageUrl: [this.shoppingProduct.productImageUrl],
      estimateUnitPrice: [this.shoppingProduct.estimateUnitPrice, Validators.compose([CustomValidators.number, CustomValidators.min(0)])],
      estimateUnit: [this.shoppingProduct.estimateUnit, Validators.compose([CustomValidators.number, CustomValidators.min(0)])],
      unitType:[this.shoppingProduct.unitType],
    });
  }

  onShopperEdit() {
    this.isShopperFormView = true;
    this.form = this.formBuilder.group({
      id: [this.shoppingProduct.id],
      actualUnitPrice: [this.shoppingProduct.actualUnitPrice, Validators.compose([CustomValidators.number, CustomValidators.min(0)])],
      actualUnit: [this.shoppingProduct.actualUnit, Validators.compose([CustomValidators.number, CustomValidators.min(0)])],
    });
  }

  onRemove() {
    this.subscription.add(this.dialogService
      .givePermission(this.shoppingProduct.productName)
      .subscribe(selection => {
        if (selection) {
          this.isPermit = selection;
          if (this.isPermit == true) {
            this.onDelete();
          }
        } else {
          // User clicked 'Cancel' or clicked outside the dialog
        }
      }));
  }

  onDelete() {
    this.submitted = true;
    this.subscription.add(this.shoppingProductService.deleteShoppingProduct(this.shoppingProduct.id)
      .subscribe(
        data => {
          this.snackBar.open(this.delete, 'X', {
            duration: 1000,
          });
          this.submitted = false;
          this.basketDataService.changeUpdateRequestId(this.shoppingRequest.id);
        },
        error => {
          this.snackBar.open(this.error, 'X', {
            duration: 1000,
          });
          this.submitted = false;
        },
        
      ));
  }

  onSubmit() {
    this.submitted = true;
    this.subscription.add(this.shoppingProductService.updateShoppingProduct(this.form.value)
      .subscribe(data => {
        this.snackBar.open(this.success, 'X', {
          duration: 1000,
        });
        this.onClose();
        this.basketDataService.changeUpdateRequestId(this.shoppingRequest.id);
        this.submitted = false;
      },
      error => {
        this.submitted = false;
        this.snackBar.open(this.error, 'X', {
          duration: 1000,
        });
      }));
  }

  onClose() {
    this.isRequesterFormView = false;
    this.isShopperFormView = false;
  }

}
