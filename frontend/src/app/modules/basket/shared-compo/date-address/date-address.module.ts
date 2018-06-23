import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { SharedModule } from '../../../../shared/shared.module';

import { DelAddressDialogComponent } from './delAddressDialog/delAddressDialog.component';
import { DelTimeComponent } from './del-time/del-time.component';
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
    DelAddressDialogComponent,
    DelTimeComponent,
    TimeCompleteComponent,
    DelAddressCompleteComponent
],
  exports:[
    DelAddressDialogComponent,
    DelTimeComponent,
    TimeCompleteComponent,
    DelAddressCompleteComponent
  ],
  providers: [
    DatePipe,
  ],
  entryComponents: [
    DelAddressDialogComponent
  ]
})
export class DateAddressModule { }