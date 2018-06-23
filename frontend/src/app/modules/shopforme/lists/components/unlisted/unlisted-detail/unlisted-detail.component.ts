import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Observable, Subject } from 'rxjs';
import 'rxjs/add/observable/interval';
import { MatSnackBar } from '@angular/material';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { TranslateService } from '@ngx-translate/core';
import { ListWrapper } from '../../../../../../model/listWrapper';
import {
  MenudataService,
  ShopformeDataService,
  ListService,
  ListProductService
} from '../../../../../../services';

@Component({
  selector: 'app-unlisted-detail',
  templateUrl: './unlisted-detail.component.html',
  styleUrls: ['./unlisted-detail.component.scss']
})
export class UnlistedDetailComponent implements OnInit, OnDestroy {

  @Input() selectedList: any[];
  pagedList: any[];
  isListAdded: boolean;
  isListEdited: boolean;
  total: number;
  price: number;
  page: number = 1;
  error: string;
  success:string;
  form: FormGroup;
  submitted = false;
  isFormView: boolean = false;
  listName: string = "Untitled"+Math.floor((Math.random() * 100) + 1) ;
  shoppingList:any;
  selectedView:string;
  private subscription = new Subscription();

  constructor(
    private formBuilder: FormBuilder,
    private shopformeDataService: ShopformeDataService,
    private listProductService: ListProductService,
    private menuDataService: MenudataService,
    private listService: ListService,
    private translate: TranslateService,
    public snackBar: MatSnackBar,
    private spinnerService: Ng4LoadingSpinnerService
  ) {
    this.subscription.add(translate.get('WRONG_ERROR').subscribe((res: string) => {
      this.error= res;
    }));
    this.subscription.add(translate.get('ADD_SUCCESSFUL').subscribe((result: string) => {
      this.success = result;
    }));
  }

  ngOnInit() {
    this.subscription.add(this.shopformeDataService.currentUnlisted.subscribe(value => {
      this.selectedList = value;
      this.getMyUnlistedCount();
    }));
    this.subscription.add(this.shopformeDataService.currentAddListTrigger.subscribe(value => {
      this.isListAdded = value;
      if (this.isListAdded == true) {
        this.getMyUnlisted();
        this.getMyUnlistedCount();
      }
    }));
    this.subscription.add(this.shopformeDataService.currentEditListTrigger.subscribe(value => {
      this.isListEdited = value;
      if(this.isListEdited==true){
        this.getMyUnlisted();
        this.getMyUnlistedCount();
      }
    }));

    this.form = this.formBuilder.group({
      listName: [this.listName, Validators.compose([Validators.required])],
    });
  }

  ngOnDestroy(): void {
    this.clear();
    this.subscription.unsubscribe();
  }

  getMyUnlisted(): void {
    this.spinnerService.show();
    this.subscription.add(this.listProductService.getListProductUnlisted()
    .subscribe(data => {
      this.selectedList = data;
      this.spinnerService.hide();
    }));

  }

  getMyUnlistedCount(): void {
    this.subscription.add(this.listProductService.getListProductUnlistedCount()
      .subscribe(
      unListTotal => this.total = unListTotal
      ));
  }

  calculatePrice(lists: any) {
    this.price = this.listProductService.calculatePrice(lists);
    return true;
  }

  onSubmit() {
    this.shoppingList = JSON.parse(JSON.stringify(this.form.value)); 
    const listWrapper: ListWrapper = {
      shoppingList: this.shoppingList,
      listProduct: this.selectedList,
    };
    this.submitted = true;
    this.subscription.add(this.listService.saveList(listWrapper)
      .subscribe(data => {
        this.snackBar.open(this.success,'X', {
          duration: 1000,
        });
        this.submitted = false;
        this.selectedView = "LISTS";
        this.sendView(this.selectedView);
      },
      error => {
        this.submitted = false;
        this.snackBar.open(this.error,'X', {
          duration: 1000,
        });
      }));
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }

  clear(){
    this.shopformeDataService.clearAddListTrigger();
    this.shopformeDataService.clearEditListTrigger();
    this.shopformeDataService.clearId();
  }
  

}

