import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';

import {
  BasketDataService,
  ShareDataService
} from '../../../../services';

@Component({
  selector: 'app-request-delivery',
  templateUrl: './request-delivery.component.html',
  styleUrls: ['./request-delivery.component.scss']
})
export class RequestDeliveryComponent implements OnInit, OnDestroy {

  @Input() selectedRequest:any;
  @Input() deliveryAddress:any;
  @Input() source: string;
  private subscription = new Subscription();
  address:any;
  isTimeView:boolean = false;

  constructor(
    private shareDataService: ShareDataService,
    private basketDataService: BasketDataService,
  ) { }

  ngOnInit() {
    this.subscription.add(this.shareDataService.currentSetTimeView.subscribe(value => {
      this.isTimeView = value;
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
