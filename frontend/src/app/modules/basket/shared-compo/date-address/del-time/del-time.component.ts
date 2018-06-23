import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Subscription } from 'rxjs/Subscription';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBar } from '@angular/material';
import { ShoppingRequest } from '../../../../../model/shoppingRequest';

import {
  ShareDataService,
  BasketDataService,
  RequestService,
} from '../../../../../services';

@Component({
  selector: 'app-del-time',
  templateUrl: './del-time.component.html',
  styleUrls: ['./del-time.component.scss']
})
export class DelTimeComponent implements OnInit, OnDestroy {

  @Input() selectedRequest: any;
  private subscription = new Subscription();
  //selectedMoment = new Date(+new Date() + 1.8e6);
  selectedMoment: any;
  minMoment = new Date(+new Date() + 1.8e6);
  deliveryTime: any;
  success: string;
  error: string;

  constructor(
    private shareDataService: ShareDataService,
    private basketDataService: BasketDataService,
    private requestService: RequestService,
    private datePipe: DatePipe,
    private translate: TranslateService,
    public snackBar: MatSnackBar,
  ) {
    this.subscription.add(translate.get('EDIT_SUCCESSFUL').subscribe((result: string) => {
      this.success = result;
    }));
    this.subscription.add(translate.get('WRONG_ERROR').subscribe((res: string) => {
      this.error = res;
    }));
  }

  ngOnInit() {
    //this.startAt = this.datePipe.transform(this.selectedMoment, 'yyyy-MM-ddTHH:mm:ss');
    //this.shareDataService.changeDateAdd(this.startAt);
    //this.selectedMoment = this.selectedRequest.deliveryTime;
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
  onSubmitDate() {
    this.deliveryTime = this.datePipe.transform(this.selectedMoment, 'yyyy-MM-ddTHH:mm:ss');
    const shoppingRequest: any = {
      id:this.selectedRequest.id,
      requestName:this.selectedRequest.requestName,
      deliveryTime: this.deliveryTime,
      estimatedTotalPrice: this.selectedRequest.estimatedTotalPrice,
      estimateTotalUnit: this.selectedRequest.estimateTotalUnit,
      shippingcost: this.selectedRequest.shippingcost,
      charge: this.selectedRequest.charge
    };
    this.subscription.add(this.requestService.updateRequest(shoppingRequest)
      .subscribe(data => {
        this.snackBar.open(this.success, 'X', {
          duration: 1000,
        });
        this.basketDataService.changeId(this.selectedRequest.id);
        this.shareDataService.changeSetTimeView(false);
      },
      error => {
        this.snackBar.open(this.error, 'X', {
          duration: 1000,
        });
      }));
  }

}
