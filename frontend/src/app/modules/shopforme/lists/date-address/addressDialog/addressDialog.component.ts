import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Subscription } from 'rxjs/Subscription';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomValidators } from 'ng2-validation';
import { Observable, Subject } from 'rxjs';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBar } from '@angular/material';

import { DeliveryAddress } from '../../../../../model/deliveryAddress';
import { Address } from '../../../../../model/address';

import {
  ShareDataService,
  GeocodingApiService
} from '../../../../../services';

@Component({
  selector: 'app-addressDialog',
  templateUrl: './addressDialog.component.html',
  styleUrls: ['./addressDialog.component.scss']
})
export class AddressDialogComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  error: string;
  success: string;
  form: FormGroup;
  submitted = false;
  deliveryAddress:any;
  lat:any;
  lng:any;

  constructor(
    private shareDataService: ShareDataService,
    private geocodingAPIService:GeocodingApiService,
    private formBuilder: FormBuilder,
    private translate: TranslateService,
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<AddressDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public address: Address
  ) { 
    this.subscription.add(translate.get('WRONG_ERROR').subscribe((res: string) => {
      this.error = res;
    }));
    this.subscription.add(translate.get('ADD_SUCCESSFUL').subscribe((result: string) => {
      this.success = result;
    }));
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      streetNumber: [this.address.streetNumber||'', Validators.compose([Validators.required])],
      streetName: [this.address.streetName||'', Validators.compose([Validators.required])],
      postcode: [this.address.postcode||'', Validators.compose([Validators.required])],
      city: [this.address.city||'', Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z \-\']+')])],
      state: [this.address.state||'', Validators.compose([Validators.required])],
      country: [this.address.country],
      latitude: [this.address.latitude || ''],
      longitude: [this.address.longitude || ''],
      url: [this.address.url || ''],
      firstname: ['', Validators.compose([Validators.required])],
      lastname: ['', Validators.compose([Validators.required])],
      additional: [''],
      contact: [''],
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
  
  updateLatLngFromAddress(form:any) {
    this.geocodingAPIService
      .findFromAddress(form.value.streetName, form.value.postcode, form.value.city, form.value.city, form.value.state , form.value.country)
      .subscribe(response => {
        if (response.status == 'OK') {
          this.lat = response.results[0].geometry.location.lat;
          this.lng = response.results[0].geometry.location.lng;
          
        } 
      });
  }
  

  onSubmit(){
    this.updateLatLngFromAddress(this.form);

    setTimeout(()=>{ 
      this.form.patchValue({
        latitude: this.lat, 
        longitude:this.lng
      });
      this.deliveryAddress = JSON.parse(JSON.stringify(this.form.value));
      this.shareDataService.changeDelAddress(this.deliveryAddress);
      this.dialogRef.close();
    },1000);
    setTimeout(()=>{    
      this.shareDataService.changeSetDelAdrView(true);
    },1000); 
  }

  onDialogClose() {
    this.dialogRef.close();
  }

}
