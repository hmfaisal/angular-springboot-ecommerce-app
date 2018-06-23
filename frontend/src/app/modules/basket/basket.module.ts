import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import { SharedModule } from '../../shared/shared.module';
import { MyAcceptsModule } from './my-accepts/my-accepts.module';
import { MyRequestsModule } from './my-requests/my-requests.module';
import { SharedCompoModule } from './shared-compo/shared-compo.module';

import { BasketComponent } from './basket.component';
import { SecondaryMenuComponent } from './secondary-menu/secondary-menu.component';
import { BasketRoutes } from './basket.routing';

import {
  BasketDataService,
} from '../../services';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    MyAcceptsModule,
    MyRequestsModule,
    SharedCompoModule,
    RouterModule.forChild(BasketRoutes),
  ],
  declarations: [
    BasketComponent ,
    SecondaryMenuComponent,
  ],
  providers: [
    BasketDataService,
  ]
})
export class BasketModule { }