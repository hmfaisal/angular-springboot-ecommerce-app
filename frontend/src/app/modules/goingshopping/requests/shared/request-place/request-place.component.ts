import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { 
  MenudataService,
  ShareDataService
} from '../../../../../services';

@Component({
  selector: 'app-request-place',
  templateUrl: './request-place.component.html',
  styleUrls: ['./request-place.component.scss']
})
export class RequestPlaceComponent implements OnInit, OnDestroy {

  @Input() menuitem: any;
  private subscription = new Subscription();
  selectedMenu: string;
  address:any;

  constructor(
    private menuDataService: MenudataService,
    private shareDataService: ShareDataService,
  ) { }

  ngOnInit() {
    this.shareDataService.clearAdrAdd();
    this.subscription.add(this.shareDataService.currentAddedAdr.subscribe(value => {
      this.address = value;
      if(this.address!='empty') {
        this.selectedMenu = "DISTANCE";
        this.sendView(this.selectedMenu);
        setTimeout(() => {
          this.sendData(this.address);
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
