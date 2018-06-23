import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBar } from '@angular/material';
import { DialogService } from '../../../../services/dialog.service';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

import {
  ShareDataService,
  MenudataService,
  AcceptService,
  RequestService,
  ShoppingProductService,
  BasketDataService
} from '../../../../services';

@Component({
  selector: 'app-request-detail',
  templateUrl: './request-detail.component.html',
  styleUrls: ['./request-detail.component.scss']
})
export class RequestDetailComponent implements OnInit, OnDestroy {

  selectedRequest: any;
  requests: any[] = [];
  source: any;
  isRequestEdited: boolean;
  isProductEdited: boolean;
  submitted = false;
  id: string;
  page: number = 1;
  isRequestAdded: boolean;
  error: string;
  updateSuccess: string;
  deleteSuccess: string;
  cancelSuccess: string;
  acceptSuccess: string;
  deliverSuccess: string;
  confirmSuccess: string;
  selectedView: string;
  private subscription = new Subscription();

  constructor(
    private menuDataService: MenudataService,
    private shareDataService: ShareDataService,
    private basketDataService: BasketDataService,
    private shoppingProductService: ShoppingProductService,
    private acceptService: AcceptService,
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
    this.subscription.add(translate.get('UPDATE_SUCCESSFUL').subscribe((result: string) => {
      this.updateSuccess = result;
    }));
    this.subscription.add(translate.get('CANCEL_SUCCESSFUL').subscribe((result: string) => {
      this.cancelSuccess = result;
    }));
    this.subscription.add(translate.get('ACCEPT_SUCCESSFUL').subscribe((result: string) => {
      this.acceptSuccess = result;
    }));
    this.subscription.add(translate.get('DELIVER_SUCCESSFUL').subscribe((result: string) => {
      this.deliverSuccess = result;
    }));
    this.subscription.add(translate.get('CONFIRM_SUCCESSFUL').subscribe((result: string) => {
      this.confirmSuccess = result;
    }));
    this.subscription.add(translate.get('DELETE_SUCCESSFUL').subscribe((result: string) => {
      this.deleteSuccess = result;
    }));
  }

  ngOnInit() {
    this.getMyAllRequests();
    this.subscription.add(this.menuDataService.currentId.subscribe(value => {
      if (value != "empty" && value != "undefined") {
        this.id = value;
        this.getRequestById(this.id);
      }
    }));
    this.subscription.add(this.menuDataService.currentSource.subscribe(value => {
      if (value != "empty" && value != "undefined") {
        this.source = value;
      }
    }));
    this.subscription.add(this.basketDataService.currentupdateRequest.subscribe(value => {
      if (value != "empty" && value != "undefined") {
        this.id = value;
        this.getRequestById(this.id);
        if (this.selectedRequest && this.source == 'requester') {
          setTimeout(() => {
            this.updateByRequester(this.selectedRequest);
          }, 3000);
        }
      }
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.clear();
  }

  getRequestById(id): void {
    this.spinnerService.show();
    this.subscription.add(this.requestService.getRequest(id)
      .subscribe(data => {
        this.selectedRequest = data;
        this.spinnerService.hide();
      },
      error => {
        this.spinnerService.hide();
      }
      ));
  }

  getMyAllRequests(): void {
    this.subscription.add(this.requestService.getMyAllRequest()
    .subscribe(data => {
      this.requests = data;
    },
    error => {
    }));
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

  isMyRequest(req: any): boolean {
    if (this.source == "shopper") {
      for (let request of this.requests) {
        if (request.id == req.shoppingRequest.id) {
          return true;
        }
      }
    }
    return false;
  }

  updateByRequester(request: any) {
    const shoppingRequest: any = {
      id: request.shoppingRequest.id,
      requestName: request.shoppingRequest.requestName,
      deliveryTime: request.shoppingRequest.deliveryTime,
      estimatedTotalPrice: this.calculatePrice(request),
      estimateTotalUnit: this.calculateTotalItem(request),
      shippingcost: this.calculateShippingCost(request),
      charge: this.calculateCharge(request)
    };
    this.submitted = true;
    this.subscription.add(this.requestService.updateRequest(shoppingRequest)
      .subscribe(data => {
        this.menuDataService.changeId(this.selectedRequest.shoppingRequest.id);
        this.submitted = false;
      },
      error => {
        this.submitted = false;
      }));
  }

  onRemove(request: any) {
    this.subscription.add(this.dialogService
      .givePermission(request.shoppingRequest.requestName)
      .subscribe(selection => {
        if (selection == true && this.source == 'requester') {
          this.onRequestDelete(request);
        } else if (selection == true && this.source == 'shopper') {
          this.onAcceptDelete(request);
        }
      }));
  }

  onRequestDelete(request: any) {
    this.submitted = true;
    this.subscription.add(this.requestService.deleteRequest(request.shoppingRequest.id)
      .subscribe(
      data => {
        this.snackBar.open(this.deleteSuccess, 'X', {
          duration: 1000,
        });
        this.selectedView = "REQUEST";
        this.submitted = false;
        this.menuDataService.changeView(this.selectedView);
      },
      error => {
        this.submitted = false;
        this.snackBar.open(this.error, 'X', {
          duration: 1000,
        });
      }
      ));
  }

  onAcceptDelete(request: any) {
    this.submitted = false;
    this.subscription.add(this.acceptService.removeAccept(request.shoppingRequest)
      .subscribe(
      data => {
        this.snackBar.open(this.cancelSuccess, 'X', {
          duration: 1000,
        });
        this.selectedView = "ACCEPT";
        this.submitted = false;
        this.menuDataService.changeView(this.selectedView);
      },
      error => {
        this.submitted = false;
        this.snackBar.open(this.error, 'X', {
          duration: 1000,
        });
      },

    ));
  }

  onAccept(request: any) {
    this.submitted = true;
    this.subscription.add(this.acceptService.saveAccept(request.shoppingRequest)
      .subscribe(
      data => {
        this.snackBar.open(this.acceptSuccess, 'X', {
          duration: 1000,
        });
        //this.selectedView = "ACCEPT";
        this.submitted = false;
        this.router.navigate(['./basket']);
        this.menuDataService.changeView(this.selectedView);
      },
      error => {
        this.submitted = false;
        this.snackBar.open(this.error, 'X', {
          duration: 1000,
        });
      }
      ));

  }

  onDeliver(request: any) {
    this.submitted = true;
    this.subscription.add(this.acceptService.deliverAccept(request.shoppingRequest)
      .subscribe(
      data => {
        this.snackBar.open(this.deliverSuccess, 'X', {
          duration: 1000,
        });
        this.selectedView = "ACCEPT";
        this.submitted = false;
        this.menuDataService.changeView(this.selectedView);
      },
      error => {
        this.submitted = false;
        this.snackBar.open(this.error, 'X', {
          duration: 1000,
        });
      }
      ));

  }

  onConfirm(request: any) {
    this.submitted = true;
    this.subscription.add(this.requestService.confirmRequest(request.shoppingRequest)
      .subscribe(
      data => {
        this.snackBar.open(this.confirmSuccess, 'X', {
          duration: 1000,
        });
        this.selectedView = "REQUEST";
        this.submitted = false;
        this.menuDataService.changeView(this.selectedView);
      },
      error => {
        this.submitted = false;
        this.snackBar.open(this.error, 'X', {
          duration: 1000,
        });
      }
      ));

  }

  clear() {
    this.basketDataService.clearId();
    this.basketDataService.clearSource();
    this.basketDataService.clearUpdateRequestId();
  }

}
