import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import {
  MenudataService,
  ShopformeDataService,
  ListService,
  ListProductService
} from '../../../../../../services';

@Component({
  selector: 'app-list-single',
  templateUrl: './list-single.component.html',
  styleUrls: ['./list-single.component.scss']
})
export class ListSingleComponent implements OnInit, OnDestroy {

  @Input() singleList: any;
  productImageUrl: string;
  private subscription = new Subscription();
  total: number;
  price:number ;
  selectedView:string;
  selectedList:any;

  constructor(
    private menuDataService: MenudataService,
    private shopformeDataService: ShopformeDataService,
    private listService: ListService,
    private listProductService: ListProductService,
  ) { }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  imageDisplayCondition(listProduct: any) {
    this.productImageUrl = '';
    for (let lp of listProduct) {
      if (lp.productImageUrl != '') {
        this.productImageUrl = lp.productImageUrl;
        return true;
      }
    }
    return true;
  }

  calculatePrice(listProduct: any){
    this.price = this.listService.calculatePrice(listProduct);
    return true;
  }

  calculateTotalItem(listProduct: any){
    this.total =this.listService.calculateTotalItem(listProduct);
    return true;
  }

  selectList(list:any) {
    this.selectedView = "LISTSINGLE";
    let li = list.shoppingList.id;
    this.selectedList = String(li);
    this.sendData(this.selectedList);
    this.sendView(this.selectedView);
    
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }

  sendData(selectedId:string){
    this.menuDataService.changeData(selectedId);
  }

}
