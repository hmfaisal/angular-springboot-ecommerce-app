import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
import { DateAddressModule } from './date-address/date-address.module';
import { CommonCompoModule } from '../../common-compo/common-compo.module';
import { AgmCoreModule } from '@agm/core';
import { RequestService } from '../../../services/request.service';
import { DialogService } from '../../../services/dialog.service';
import { RequestSingleComponent } from './request-single/request-single.component';
import { RequestDetailComponent } from './request-detail/request-detail.component';
import { RequestInvoiceComponent } from './request-invoice/request-invoice.component';
import { RequestDeliveryComponent } from './request-delivery/request-delivery.component';
import { RequestProductComponent } from './request-product/request-product.component';
import { RequestMapSingleComponent } from './request-map-single/request-map-single.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AgmCoreModule,
    ReactiveFormsModule,
    SharedModule,
    CommonCompoModule,
    DateAddressModule,
  ],
  declarations: [
    RequestSingleComponent,
    RequestDetailComponent,
    RequestInvoiceComponent,
    RequestDeliveryComponent,
    RequestProductComponent,
    RequestMapSingleComponent
],
  exports:[
    RequestSingleComponent,
    RequestDetailComponent,
    RequestInvoiceComponent,
    RequestDeliveryComponent,
    RequestProductComponent,
    RequestMapSingleComponent
  ],
  providers: [

  ],
})
export class SharedCompoModule { }