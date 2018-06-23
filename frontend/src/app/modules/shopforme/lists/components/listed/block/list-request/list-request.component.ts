import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { DialogService } from '../../../../../../../services/dialog.service';
import { DeliveryAddress } from '../../../../../../../model/deliveryAddress';
import { Address } from '../../../../../../../model/address';

import {
  MenudataService,
  ShareDataService,
  ShopformeDataService,
  ListService,
  ListProductService
} from '../../../../../../../services';

@Component({
  selector: 'app-list-request',
  templateUrl: './list-request.component.html',
  styleUrls: ['./list-request.component.scss']
})
export class ListRequestComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  address:any;
  isTimeView:boolean;
  isDelAdrView:boolean;

  constructor(
    private shopformeDataService: ShopformeDataService,
    private shareDataService: ShareDataService,
    private dialogService: DialogService,
  ) {
  }

  ngOnInit() {
    this.subscription.add(this.shareDataService.currentAddedAdr.subscribe(value => {
      this.address = value;
      if(this.address!='empty') {
        this.dialogService.saveAddress(this.address);
      }
    }));
    this.subscription.add(this.shareDataService.currentSetTimeView.subscribe(value => {
      this.isTimeView = value;
    }));
    this.subscription.add(this.shareDataService.currentDelAdrView.subscribe(value => {
      this.isDelAdrView = value;
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.clear();
  }

  clear(){
    this.shareDataService.clearAdrAdd();
  }

}
