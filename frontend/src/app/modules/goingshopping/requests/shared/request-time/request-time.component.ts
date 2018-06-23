import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { 
  MenudataService,
  ShareDataService
} from '../../../../../services';

@Component({
  selector: 'app-request-time',
  templateUrl: './request-time.component.html',
  styleUrls: ['./request-time.component.scss']
})
export class RequestTimeComponent implements OnInit, OnDestroy {

  @Input() menuitem: any;
  private subscription = new Subscription();
  selectedMenu: string;
  time:any;

  constructor(
    private menuDataService: MenudataService,
    private shareDataService: ShareDataService,
  ) { }

  ngOnInit() {
    this.shareDataService.clearDateAdd();
    this.subscription.add(this.shareDataService.currentAddedDate.subscribe(value => {
      this.time = value;
      if(this.time!='empty') {
        this.selectedMenu = "TIME";  
        this.sendView(this.selectedMenu);
        setTimeout(() => {
          this.sendData(this.time);
        }, 1000); 
      }
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }

  sendData(selectedData:any){
    this.menuDataService.changeData(selectedData);
  }

}
