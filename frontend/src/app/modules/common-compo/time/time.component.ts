import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Subscription } from 'rxjs/Subscription';
import {
  ShareDataService,
} from '../../../services';

@Component({
  selector: 'app-time',
  templateUrl: './time.component.html',
  styleUrls: ['./time.component.scss']
})
export class TimeComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  //selectedMoment = new Date(+new Date() + 1.8e6);
  selectedMoment:any;
  minMoment = new Date(+new Date() + 1.8e6);
  startAt: any;

  constructor(
    private shareDataService: ShareDataService,
    private datePipe: DatePipe,
  ) { 
  }

  ngOnInit() {
    //this.startAt = this.datePipe.transform(this.selectedMoment, 'yyyy-MM-ddTHH:mm:ss');
    //this.shareDataService.changeDateAdd(this.startAt);
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
  onSubmitDate() {
    this.startAt = this.datePipe.transform(this.selectedMoment, 'yyyy-MM-ddTHH:mm:ss');
    setTimeout(()=>{   
      this.shareDataService.changeDateAdd(this.startAt); 
      this.shareDataService.changeSetTimeView(true);
    },1000);
    
  }

}
