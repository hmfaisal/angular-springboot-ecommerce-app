import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { DatePipe } from '@angular/common';
import {
  ShareDataService,
  RequestService,
  BasketDataService
} from '../../../../../services';
@Component({
  selector: 'app-time-complete',
  templateUrl: './time-complete.component.html',
  styleUrls: ['./time-complete.component.scss']
})
export class TimeCompleteComponent implements OnInit, OnDestroy {

  @Input() selectedRequest:any;
  @Input() source: string;
  id:string ;
  private subscription = new Subscription();
  
  constructor(
    private shareDataService: ShareDataService,
    private basketDataService: BasketDataService,
    private requestService: RequestService,
    private datePipe: DatePipe,
  ) { }

  ngOnInit() {

  }

  onEdit(){
    this.shareDataService.changeSetTimeView(true);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
