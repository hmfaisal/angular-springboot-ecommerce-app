import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { DialogService } from '../../../../../services/dialog.service';
import {
  ShareDataService,
  BasketDataService,
  RequestService
} from '../../../../../services';

@Component({
  selector: 'app-delAddress-complete',
  templateUrl: './delAddress-complete.component.html',
  styleUrls: ['./delAddress-complete.component.scss']
})
export class DelAddressCompleteComponent implements OnInit, OnDestroy {

  @Input() deliveryAddress:any;
  @Input() selectedRequest:any;
  @Input() source: string;
  id:any;
  private subscription = new Subscription();

  constructor(
    private shareDataService: ShareDataService,
    private basketDataService: BasketDataService,
    private requestService: RequestService,
    private dialogService: DialogService
  ) { }

  ngOnInit() {
    this.subscription.add(this.basketDataService.currentAdrId.subscribe(value => {
      this.id = value;
      if(this.id!="empty" && this.id!="undefined"){
        this.getAddressById(this.id);
      }
    })); 
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getAddressById(id): void {
    this.subscription.add(this.requestService.getDeliveryAddress(id)
      .subscribe(
        deliveryAddress => this.deliveryAddress = deliveryAddress,
    ));
  }

  onEdit(){
    this.dialogService.saveDelAddress(this.deliveryAddress)
  }

}
