import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
import { CommonCompoModule } from '../../common-compo/common-compo.module';
import { SharedCompoModule } from '../../basket/shared-compo/shared-compo.module';

import { RequestActiveComponent } from './components/request-active/request-active.component';
import { RequestByPlaceComponent } from './components/request-by-place/request-by-place.component';
import { RequestByPriceComponent } from './components/request-by-price/request-by-price.component';
import { RequestByTimeComponent } from './components/request-by-time/request-by-time.component';

import { 
  RequestService,
  BasketDataService
} from '../../../services';
import { DialogService } from '../../../services/dialog.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    CommonCompoModule,
    SharedCompoModule
  ],
  declarations: [
    RequestActiveComponent,
    RequestByPlaceComponent,
    RequestByPriceComponent,
    RequestByTimeComponent
  ],
  exports:[
    RequestActiveComponent,
    RequestByPlaceComponent,
    RequestByPriceComponent,
    RequestByTimeComponent
  ],
  providers: [
    BasketDataService,
    RequestService,
    DialogService
  ],
})
export class RequestsModule { }