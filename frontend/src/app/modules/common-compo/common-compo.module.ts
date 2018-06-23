import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';

import { TimeComponent } from './time/time.component';
import { AddressComponent } from './address/address.component';
import { PermissionDialogComponent } from './permissionDialog/permissionDialog.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule
  ],
  declarations: [
    TimeComponent,
    AddressComponent,
    PermissionDialogComponent,
],
  exports:[
    TimeComponent,
    AddressComponent,
    PermissionDialogComponent,
  ],
  providers: [
    DatePipe,
  ],
  entryComponents: [
    PermissionDialogComponent,
  ]
})
export class CommonCompoModule { }