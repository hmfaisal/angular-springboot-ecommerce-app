import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

import {
  ProductService,
  ListService,
  ListProductService,
  ShopformeDataService
} from '../../../../../services';

@Component({
  selector: 'app-myList',
  templateUrl: './myList.component.html',
  styleUrls: ['./myList.component.scss']
})
export class MyListComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  lists: any;
  unlists: any;
  total: number;
  unListTotal: number;
  page: number = 1;
  unListPage: number = 1;
  isListAdded: boolean;

  constructor(
    private listProductService: ListProductService,
    private listService: ListService,
    private shopformeDataService: ShopformeDataService,
    private spinnerService: Ng4LoadingSpinnerService
  ) {}

  ngOnInit() {
    this.subscription.add(this.shopformeDataService.currentAddListTrigger.subscribe(value => {
      this.isListAdded = value;
      if (this.isListAdded == true) {
        this.getMyLists(this.page);
        this.getMyListsCount();
        this.getMyUnlisted();
        this.getMyUnlistedCount();
      }
    }));
    this.getMyLists(this.page);
    this.getMyListsCount();
    this.getMyUnlisted();
    this.getMyUnlistedCount();
  }

  ngOnDestroy(): void {
    this.clear();
    this.subscription.unsubscribe();
  }

  getMyLists(page): void {
    this.spinnerService.show();
    this.subscription.add(this.listService.getMyList(page)
      .subscribe(data => {
        this.lists = data;
        this.spinnerService.hide();
      },
      error => {
        this.spinnerService.hide();
      }));
  }

  getMyListsCount(): void {
    this.subscription.add(this.listService.getMyListCount()
      .subscribe(data => {
        this.total = data;
      },
      error => {
      }));
  }

  getMyUnlisted(): void {
    this.subscription.add(this.listProductService.getListProductUnlisted()
      .subscribe(data => {
        this.unlists = data;
      },
      error => {
      }));
  }

  getMyUnlistedCount(): void {
    this.subscription.add(this.listProductService.getListProductUnlistedCount()
      .subscribe(data => {
        this.unListTotal = data;
      },
      error => {
      }));
  }

  clear() {
    this.shopformeDataService.clearAddListTrigger();
  }

}
