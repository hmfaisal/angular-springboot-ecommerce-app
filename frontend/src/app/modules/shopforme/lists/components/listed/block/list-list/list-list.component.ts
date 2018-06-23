import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { CustomValidators } from 'ng2-validation';
import { Observable, Subject } from 'rxjs';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBar } from '@angular/material';

import {
  ListService,
  ListProductService,
  ShopformeDataService,
  MenudataService
} from '../../../../../../../services';

@Component({
  selector: 'app-list-list',
  templateUrl: './list-list.component.html',
  styleUrls: ['./list-list.component.scss']
})
export class ListListComponent implements OnInit, OnDestroy {

  @Input() selectedList: any;
  @Input() total: number;
  @Input() price: number;
  @Input() cost: number;
  @Input()totalPrice:number;
  error: string;
  success: string;
  form: FormGroup;
  submitted = false;
  isFormView: boolean = false;
  private subscription = new Subscription();

  constructor(
    private formBuilder: FormBuilder,
    private listService: ListService,
    private listProductService: ListProductService,
    private shopformeDataService: ShopformeDataService,
    private menuDataService: MenudataService,
    private translate: TranslateService,
    public snackBar: MatSnackBar,
  ) {
    this.subscription.add(translate.get('WRONG_ERROR').subscribe((res: string) => {
      this.error = res;
    }));
    this.subscription.add(translate.get('EDIT_SUCCESSFUL').subscribe((result: string) => {
      this.success = result;
    }));
  }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  onEdit() {
    this.isFormView = true;
    this.form = this.formBuilder.group({
      id: [this.selectedList.id],
      listName: [this.selectedList.listName, Validators.compose([Validators.required])],
    });
  }

  onSubmit() {
    this.submitted = true;
    this.subscription.add(this.listService.updateList(this.form.value)
      .subscribe(data => {
        this.snackBar.open(this.success, 'X', {
          duration: 1000,
        });
        this.submitted = false;
        this.onClose();
        this.sendChange(true);
      },
      error => {
        this.submitted = false;
        this.snackBar.open(this.error, 'X', {
          duration: 1000,
        });
      }));
  }

  onClose() {
    this.isFormView = false;
  }

  sendChange(isEdited: boolean) {
    this.shopformeDataService.changeEditListrigger(isEdited);
  }

}
