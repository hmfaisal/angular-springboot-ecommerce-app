import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import {
  MenudataService,
  ProductService
} from '../../../../../services';

@Component({
  selector: 'app-product-range',
  templateUrl: './product-range.component.html',
  styleUrls: ['./product-range.component.scss']
})
export class ProductRangeComponent implements OnInit, OnDestroy {

  @Input() menuitem: any;
  private subscription = new Subscription();
  selectedMenu: string;
  public priceRange: number[] = [0, 300];
  selectedRange:any;

  sliderconfig: any = {
    behaviour: 'drag',
    connect: true,
    keyboard: true,
    margin: 1,
    step:1,
    range: {
      min: 0,
      max: 300
    }
  };

  constructor(
    private menuDataService: MenudataService,
    private productService: ProductService,
  ) { }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  onChange(value: any) {
    this.selectedMenu = "PRICE";
    this.sendView(this.selectedMenu);
    setTimeout(() => {
      this.sendData(value);
    }, 1000);
  }

  sendView(selectedView: string) {
    this.menuDataService.changeView(selectedView);
  }

  sendData(selectedData: any) {
    this.menuDataService.changeData(selectedData);
  }

}
