import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import {
  MenudataService,
  ListService,
  ListProductService,
  ShopformeDataService
} from '../../../../../../services';

@Component({
  selector: 'app-my-unlisted',
  templateUrl: './my-unlisted.component.html',
  styleUrls: ['./my-unlisted.component.scss']
})
export class MyUnlistedComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  @Input() singleList: any;
  @Input() total: number;
  productImageUrl: string;
  price:number;
  selectedView:string;
  selectedList:any;

  constructor(
    private listProductService: ListProductService,
    private menuDataService: MenudataService,
    private shopformeDataService: ShopformeDataService,
  ) { 

  }

  ngOnInit() {  
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  imageDisplayCondition(lists:any) {
    this.productImageUrl = '';
    for (let list of lists) {
      if (list.productImageUrl != '') {
        this.productImageUrl = list.productImageUrl;
        return true;
      }
    }
    return true;
  }

  calculatePrice(lists:any){
    this.price =this.listProductService.calculatePrice(lists);
    return true;
  }

  selectList(list:any) {
    this.selectedView = "UNLISTED";
    this.selectedList = list;
    this.sendView(this.selectedView);
    this.sendData(this.selectedList);
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }

  sendData(selectedData:any){
    this.shopformeDataService.changeUnlisted(selectedData);
  }

}
