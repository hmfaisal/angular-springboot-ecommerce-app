import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import { GoingShoppingComponent } from './goingshopping.component';
import { SecondaryMenuComponent } from './secondary-menu/secondary-menu.component';

import { SharedModule } from '../../shared/shared.module';
import { RequestsSharedModule } from './requests/shared/requests-shared.module';
import { RequestsModule } from './requests/requests.module';
import { SharedCompoModule } from '../basket/shared-compo/shared-compo.module';

import { GoingShoppingRoutes } from './goingshopping.routing';

import {GoingShoppingDataService} from '../../services';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    RequestsSharedModule,
    RequestsModule,
    SharedCompoModule,
    RouterModule.forChild(GoingShoppingRoutes),
  ],
  declarations: [
    GoingShoppingComponent,
    SecondaryMenuComponent,
  ],
  providers: [
    GoingShoppingDataService,
  ]
})

export class GoingShoppingModule {}