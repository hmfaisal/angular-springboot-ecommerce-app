import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { SharedModule } from '../../../../shared/shared.module';

import { AddressDialogComponent } from './addressDialog/addressDialog.component';
import { TimeCompleteComponent } from './time-complete/time-complete.component';
import { DelAddressCompleteComponent } from './delAddress-complete/delAddress-complete.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
  ],
  declarations: [
    AddressDialogComponent,
    TimeCompleteComponent,
    DelAddressCompleteComponent
],
  exports:[
    AddressDialogComponent,
    TimeCompleteComponent,
    DelAddressCompleteComponent
  ],
  providers: [
    DatePipe,
  ],
  entryComponents: [
    AddressDialogComponent
  ]
})
export class DateAddressModule { }