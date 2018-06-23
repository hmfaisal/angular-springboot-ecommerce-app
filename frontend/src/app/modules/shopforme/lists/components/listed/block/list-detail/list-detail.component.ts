import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBar } from '@angular/material';
import { DialogService } from '../../../../../../../services/dialog.service';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

import { ListWrapper } from '../../../../../../../model/listWrapper';
import { DeliveryAddress } from '../../../../../../../model/deliveryAddress';
import { ShoppingRequest } from '../../../../../../../model/shoppingRequest';
import { RequestWrapper } from '../../../../../../../model/requestWrapper';

import {
  ShopformeDataService,
  ShareDataService,
  MenudataService,
  ListService,
  ListProductService,
  RequestService,
} from '../../../../../../../services';

@Component({
  selector: 'app-list-detail',
  templateUrl: './list-detail.component.html',
  styleUrls: ['./list-detail.component.scss']
})
export class ListDetailComponent implements OnInit, OnDestroy {

  selectedList: any;
  pagedList: any[];
  id: string;
  total: number;
  price: number;
  cost: number;
  charge: number;
  totalPrice: number;
  page: number = 1;

  isListAdded: boolean;
  isListEdited: boolean;

  isPermit: boolean;
  selectedView: string;
  submitted:boolean;

  deliveryTime: any = "";
  deliveryAddress: any = {};
  dateError: string;
  addressError: string;
  error: string;
  success: string;
  delete: string;
  private subscription = new Subscription();

  constructor(
    private menuDataService: MenudataService,
    private shareDataService: ShareDataService,
    private shopformeDataService: ShopformeDataService,
    private listService: ListService,
    private requestService: RequestService,
    private translate: TranslateService,
    public snackBar: MatSnackBar,
    private router: Router,
    private dialogService: DialogService,
    private spinnerService: Ng4LoadingSpinnerService
  ) {
    this.subscription.add(translate.get('WRONG_ERROR').subscribe((res: string) => {
      this.error = res;
    }));
    this.subscription.add(translate.get('DATE_ERROR').subscribe((res: string) => {
      this.dateError = res;
    }));
    this.subscription.add(translate.get('ADDRESS_ERROR').subscribe((res: string) => {
      this.addressError = res;
    }));
    this.subscription.add(translate.get('REQUEST_SUCCESSFUL').subscribe((result: string) => {
      this.success = result;
    }));
    this.subscription.add(translate.get('DELETE_SUCCESSFUL').subscribe((result: string) => {
      this.delete = result;
    }));
  }

  ngOnInit() {
    this.subscription.add(this.menuDataService.currentData.subscribe(value => {
      this.id = value;
      if (this.id != "empty" && this.id != "undefined") {
        this.getListById(this.id);
      }
    }));
    this.subscription.add(this.shopformeDataService.currentAddListTrigger.subscribe(value => {
      this.isListAdded = value;
      if (this.isListAdded == true) {
        this.getListById(this.id);
      }
    }));
    this.subscription.add(this.shopformeDataService.currentEditListTrigger.subscribe(value => {
      this.isListEdited = value;
      if (this.isListEdited == true) {
        this.getListById(this.id);
      }
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.clear();
  }

  getListById(id): void {
    this.spinnerService.show();
    this.subscription.add(this.listService.getList(id)
      .subscribe(data => {
        this.selectedList = data;
        this.spinnerService.hide();
      },
      error => {
        this.spinnerService.hide();
      }));
  }

  calculatePrice(list: any) {
    this.price = this.listService.calculatePrice(list.listProduct);
    return this.price;
  }

  calculateShippingCost(list: any) {
    this.cost = this.listService.calculateShippingCost(list.listProduct);
    this.calculateCharge(this.cost);
    return this.cost;
  }

  calculateCharge(cost: number) {
    this.charge = this.listService.calculateCharge(cost);
  }

  calculateTotalPrice() {
    this.totalPrice = this.price + this.cost;
    return this.totalPrice;
  }

  calculateTotalItem(list: any) {
    this.total = this.listService.calculateTotalItem(list.listProduct);
    return this.total;
  }

  onSubmit() {
    this.subscription.add(this.shareDataService.currentAddedDate.subscribe(value => {
      this.deliveryTime = value;
      if (this.deliveryTime == 'empty') {
        this.snackBar.open(this.dateError, 'X', {
          duration: 2000,
        });
      }
    }));
    this.subscription.add(this.shareDataService.currentDelAddress.subscribe(value => {
      this.deliveryAddress = value;
      if (this.deliveryAddress == 'empty') {
        this.snackBar.open(this.addressError, 'X', {
          duration: 2000,
        });
      }
    }));
    if (this.deliveryAddress != 'empty' && this.deliveryTime != 'empty') {
      const shoppingRequest: ShoppingRequest = {
        deliveryTime: this.deliveryTime,
        estimatedTotalPrice: this.price,
        estimateTotalUnit: this.total,
        shippingcost: this.cost,
        charge: this.charge
      };
      const shoppingRequestWrapper: RequestWrapper = {
        shoppingList: this.selectedList.shoppingList,
        shoppingRequest: shoppingRequest,
        deliveryAddress: this.deliveryAddress
      };
      this.submitted = true;
      this.subscription.add(this.requestService.saveRequest(shoppingRequestWrapper)
        .subscribe(data => {
          this.snackBar.open(this.success, 'X', {
            duration: 1000,
          });
          this.submitted = false;
          this.router.navigate(['/basket']);
        },
        error => {
          this.snackBar.open(this.error, 'X', {
            duration: 1000,
          });
          this.submitted = false;
        }));
    }

  }

  onRemove(list: any) {
    this.subscription.add(this.dialogService
      .givePermission(list.shoppingList.listName)
      .subscribe(selection => {
        if (selection) {
          this.isPermit = selection;
          if (this.isPermit == true) {
            this.onDelete(list);
          }
        } else {
          // User clicked 'Cancel' or clicked outside the dialog
        }
      }));
  }

  onDelete(list: any) {
    this.subscription.add(this.listService.deleteList(list.shoppingList.id)
      .subscribe(
      () => {
        this.snackBar.open(this.delete, 'X', {
          duration: 1000,
        });
        this.selectedView = "LISTS";
        this.sendView(this.selectedView);
      },
      error => {
        this.snackBar.open(this.error, 'X', {
          duration: 1000,
        });
      },
    ));
  }

  sendView(selectedView: string) {
    this.menuDataService.changeView(selectedView);
  }

  clear() {
    this.shopformeDataService.clearAddListTrigger();
    this.shopformeDataService.clearEditListTrigger();
    this.shopformeDataService.clearId();
    this.shareDataService.clearDateAdd();
    this.shareDataService.clearDelAddress();
    this.shareDataService.clearSetTimeView();
    this.shareDataService.clearSetDelAdrView();
  }

}
