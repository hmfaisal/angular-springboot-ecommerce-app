import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../../../shared/shared.module';
import { CommonCompoModule } from '../../../common-compo/common-compo.module';

import { NouisliderModule } from 'ng2-nouislider';

import { RequestPlaceComponent } from './request-place/request-place.component';
import { RequestPriceComponent } from './request-price/request-price.component';
import { RequestTimeComponent } from './request-time/request-time.component';

import { UserService } from '../../../../services/user.service';
import { RequestService } from '../../../../services/request.service';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    CommonCompoModule ,
    NouisliderModule
  ],
  declarations: [
    RequestPlaceComponent,
    RequestPriceComponent,
    RequestTimeComponent
],
  exports:[
    RequestPlaceComponent,
    RequestPriceComponent,
    RequestTimeComponent
  ],
  providers: [
    RequestService
  ],
})
export class RequestsSharedModule { }