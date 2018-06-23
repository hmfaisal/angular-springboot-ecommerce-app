import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
import { SharedCompoModule } from '../shared-compo/shared-compo.module';

import { MyRequestComponent } from './components/my-request/my-request.component';
import { MyRequestActiveComponent } from './components/my-request-active/my-request-active.component';
import { MyRequestArchivedComponent } from './components/my-request-archived/my-request-archived.component';
import { MyRequestDeliveredComponent } from './components/my-request-delivered/my-request-delivered.component';
import { MyRequestConfirmedComponent } from './components/my-request-confirmed/my-request-confirmed.component';

import { DialogService } from '../../../services/dialog.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    SharedCompoModule
  ],
  declarations: [
    MyRequestComponent,
    MyRequestActiveComponent,
    MyRequestArchivedComponent,
    MyRequestDeliveredComponent,
    MyRequestConfirmedComponent 
  ],
  exports:[
    MyRequestComponent,
    MyRequestActiveComponent,
    MyRequestArchivedComponent,
    MyRequestDeliveredComponent,
    MyRequestConfirmedComponent 
  ],
  providers: [
    DialogService
  ],
  entryComponents: [
    MyRequestActiveComponent,
    MyRequestArchivedComponent,
    MyRequestDeliveredComponent,
    MyRequestConfirmedComponent ,
  ],
})
export class MyRequestsModule { }