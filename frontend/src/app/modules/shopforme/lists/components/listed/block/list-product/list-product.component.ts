import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { CustomValidators } from 'ng2-validation';
import { Observable, Subject } from 'rxjs';
//import 'rxjs/add/observable/interval';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBar } from '@angular/material';
import { UNITS } from '../../../../../../../model/unitList';

import { DialogService } from '../../../../../../../services/dialog.service';
import {
  ListService,
  ListProductService,
  ShopformeDataService
} from '../../../../../../../services';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.scss']
})
export class ListProductComponent implements OnInit, OnDestroy {

  @Input() listProduct: any;
  error: string;
  success: string;
  delete: string;
  form: FormGroup;
  submitted = false;
  isFormView: boolean = false;
  isPermit: boolean;
  units=UNITS;
  private subscription = new Subscription();

  constructor(
    private formBuilder: FormBuilder,
    private listService: ListService,
    private listProductService: ListProductService,
    private shopformeDataService: ShopformeDataService,
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

  onEdit() {
    this.isFormView = true;
    this.form = this.formBuilder.group({
      id: [this.listProduct.id],
      productName: [this.listProduct.productName, Validators.compose([Validators.required])],
      productImageUrl: [this.listProduct.productImageUrl],
      estimateUnitPrice: [this.listProduct.estimateUnitPrice, Validators.compose([CustomValidators.number, CustomValidators.min(0)])],
      estimateUnit: [this.listProduct.estimateUnit, Validators.compose([CustomValidators.number, CustomValidators.min(0)])],
      unitType:[this.listProduct.unitType],
    });
  }

  onRemove() {
    this.subscription.add(this.dialogService
      .givePermission(this.listProduct.productName)
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
    this.subscription.add(this.listProductService.deleteListProduct(this.listProduct.id)
      .subscribe(
        error => {
          this.snackBar.open(this.error, 'X', {
            duration: 1000,
          });
          this.submitted = false;
        },
        () => {
          this.snackBar.open(this.delete, 'X', {
            duration: 1000,
          });
          this.submitted = false;
          this.sendChange(true);
        }
      ));
  }

  onSubmit() {
    this.submitted = true;
    this.subscription.add(this.listProductService.updateListProduct(this.form.value)
      .subscribe(data => {
        this.snackBar.open(this.success, 'X', {
          duration: 1000,
        });
        this.submitted = false;
        this.onClose();
        this.sendChange(true);
      },
      error => {
        this.submitted = false;
        this.snackBar.open(this.error, 'X', {
          duration: 1000,
        });
      }));
  }

  onClose() {
    this.isFormView = false;
  }

  sendChange(isEdited: boolean) {
    this.shopformeDataService.changeEditListrigger(isEdited);
  }


}
