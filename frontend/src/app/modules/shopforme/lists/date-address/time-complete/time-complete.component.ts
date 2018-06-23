import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { DatePipe } from '@angular/common';
import {
  ShareDataService,
} from '../../../../../services';
@Component({
  selector: 'app-time-complete',
  templateUrl: './time-complete.component.html',
  styleUrls: ['./time-complete.component.scss']
})
export class TimeCompleteComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  deliveryTime:any;

  constructor(
    private shareDataService: ShareDataService,
    private datePipe: DatePipe,
  ) { }

  ngOnInit() {
    this.subscription.add( this.shareDataService.currentAddedDate.subscribe(value => {
      this.deliveryTime = value;
    }));
  }

  onTimeEdit(){
    this.shareDataService.clearDateAdd();
    this.shareDataService.changeSetTimeView(false);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
