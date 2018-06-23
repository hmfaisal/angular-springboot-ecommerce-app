import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { CustomValidators } from 'ng2-validation';
import { Observable, Subject } from 'rxjs';
//import 'rxjs/add/observable/interval';
import { ListProduct } from '../../../../../model/listProduct';
import { UNITS } from './../../../../../model/unitList';

import { DialogService } from '../../../../../services/dialog.service';
import {
  MenudataService,
  ListService,
  RequestService,
  ListProductService
} from '../../../../../services';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.scss']
})
export class ProductAddComponent implements OnInit, OnDestroy {

  @Input() menuitem: any;
  form: FormGroup;
  submitted = false;
  result: any;
  private subscription = new Subscription();
  product: any = {};
  requestTotal: any;
  units = UNITS;

  constructor(
    private formBuilder: FormBuilder,
    private dialogService: DialogService,
    private listService: ListService,
    private requestService: RequestService,
    private menuDataService: MenudataService,
  ) { }

  ngOnInit() {
    this.getMyRequestsCount();
    this.form = this.formBuilder.group({
      productName: ['', Validators.compose([Validators.required])],
      productImageUrl: [''],
      estimateUnitPrice: ['', Validators.compose([CustomValidators.number, CustomValidators.min(0)])],
      estimateUnit: ['', Validators.compose([CustomValidators.number, CustomValidators.min(0)])],
      unitType: [''],
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
  onSubmit() {
    this.submitted = true;
  }

  onSaveToList() {
    const listProduct: any = {
      productName: this.form.value.productName,
      productImageUrl: this.form.value.productImageUrl,
      estimateUnitPrice: this.form.value.estimateUnitPrice,
      estimateUnit: this.form.value.estimateUnit,
      unitType: this.form.value.unitType
    };
    this.menuDataService.changeMenuTrigger(false);
    this.subscription.add(this.dialogService
      .addProductToList(listProduct, this.product)
      .subscribe(res => this.result = res));
  }

  onSaveToRequest() {
    const shoppingProduct: any = {
      productName: this.form.value.productName,
      productImageUrl: this.form.value.productImageUrl,
      estimateUnitPrice: this.form.value.estimateUnitPrice,
      estimateUnit: this.form.value.estimateUnit,
      unitType: this.form.value.unitType
    };
    this.menuDataService.changeMenuTrigger(false);
    this.subscription.add(this.dialogService
      .addProductToRequest(shoppingProduct, this.product)
      .subscribe(res => this.result = res));
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
