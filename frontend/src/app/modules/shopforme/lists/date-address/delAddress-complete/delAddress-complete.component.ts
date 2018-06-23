import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import {
  ShareDataService,
} from '../../../../../services';

@Component({
  selector: 'app-delAddress-complete',
  templateUrl: './delAddress-complete.component.html',
  styleUrls: ['./delAddress-complete.component.scss']
})
export class DelAddressCompleteComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  deliveryAddress:any;

  constructor(
    private shareDataService: ShareDataService,
  ) { }

  ngOnInit() {
    this.subscription.add( this.shareDataService.currentDelAddress.subscribe(value => {
      this.deliveryAddress = value;
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  onAdrEdit(){
    this.shareDataService.clearDelAddress();
    this.shareDataService.changeSetDelAdrView(false);
  }

}
