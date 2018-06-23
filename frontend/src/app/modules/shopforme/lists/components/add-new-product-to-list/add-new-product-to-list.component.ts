import { Component, OnInit, OnDestroy, Input, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Observable, Subject } from 'rxjs';
//import 'rxjs/add/observable/interval';
import { MatSnackBar } from '@angular/material';
import { ListProduct } from './../../../../../model/listProduct';
import { ListProductWrapper } from '../../../../../model/listProductWrapper';
import { TranslateService } from '@ngx-translate/core';
import {
  ListService,
  ListProductService,
  ShopformeDataService
} from '../../../../../services';

@Component({
  selector: 'app-add-new-product-to-list',
  templateUrl: './add-new-product-to-list.component.html',
  styleUrls: ['./add-new-product-to-list.component.scss']
})
export class AddNewProductToListComponent implements OnInit, OnDestroy {

  submitted = false;
  error: string;
  success: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  private subscription = new Subscription();
  lists: any[] = [];
  shoppingList = {};
  page: number = 1;
  total: number;
  productImageUrl: string;

  constructor(
    private listProductService: ListProductService,
    private listService: ListService,
    private shopformeDataService: ShopformeDataService,
    private translate: TranslateService,
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<AddNewProductToListComponent>,
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
    this.getMyLists(this.page);
    this.getMyListsCount();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  onSelectionChange(entry) {
    this.shoppingList = entry;
  }

  onSubmit() {

    const listProductWrapper: ListProductWrapper = {
      shoppingList: this.shoppingList,
      listProduct: this.data.listProduct,
      product: this.data.product
    };
    this.submitted = true;
    this.subscription.add(this.listProductService.saveListProduct(listProductWrapper)
      .subscribe(data => {
        this.sendChange(true);
        this.dialogRef.close();
        this.snackBar.open(this.success, 'X', {
          duration: 1000,
        });
      },
      error => {
        this.submitted = false;
        this.dialogRef.close();
        this.snackBar.open(this.error, 'X', {
          duration: 1000,
        });
      }));
  }

  getMyLists(page): void {
    this.subscription.add(this.listService.getMyList(page)
      .subscribe(data => {
        this.lists = data;
      },
      error => {
      }));
  }

  getMyListsCount(): void {
    this.subscription.add(this.listService.getMyListCount()
      .subscribe(data => {
        this.total = data;
      },
      error => {
      }));
  }

  imageDisplayCondition(listProduct: any) {
    this.productImageUrl = '';
    for (let lp of listProduct) {
      if (lp.productImageUrl != '') {
        this.productImageUrl = lp.productImageUrl;
        return true;
      }
    }
    return true;
  }

  sendChange(isAdded: boolean) {
    this.shopformeDataService.changeAddListrigger(isAdded);
  }

  onDialogClose() {
    this.dialogRef.close();
  }

}
