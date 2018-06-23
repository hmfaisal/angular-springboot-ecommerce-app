import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy{

  isShow:boolean =false;
  private subscription = new Subscription();
  
  ngOnInit() {
    setTimeout(()=>{   
      this.isShow=true;
    },3000);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
