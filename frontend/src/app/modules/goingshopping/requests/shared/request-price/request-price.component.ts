import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { 
  MenudataService,
} from '../../../../../services';

@Component({
  selector: 'app-request-price',
  templateUrl: './request-price.component.html',
  styleUrls: ['./request-price.component.scss']
})
export class RequestPriceComponent implements OnInit, OnDestroy {

  @Input() menuitem: any;
  private subscription = new Subscription();
  selectedMenu: string;
  public priceRange: number[] = [0, 300];
  selectedRange:any;

  sliderconfig: any = {
    behaviour: 'drag',
    connect: true,
    keyboard: true,
    step:1,
    margin: 1,
    range: {
      min: 0,
      max: 300
    },
    /*
    pips: {
      mode: 'steps',
      density: 2
    }
    */
  };


  constructor(
    private menuDataService: MenudataService,
  ) { }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  onChange(value: any) {
    this.selectedMenu = "PRICE";
    this.selectedRange=value;
    this.sendView(this.selectedMenu);
    setTimeout(() => {
      this.sendData(value);
    }, 1000);  
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }

  sendData(selectedData:any){
    this.menuDataService.changeData(selectedData);
  }

}
