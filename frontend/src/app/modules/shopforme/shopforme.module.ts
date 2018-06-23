import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import { ShopForMeComponent } from './shopforme.component';
import { SecondaryMenuComponent } from './secondary-menu/secondary-menu.component';;

import { SharedModule } from '../../shared/shared.module';
import { ProductsSharedModule } from './products/shared/products-shared.module';
import { ProductsModule } from './products/products.module';
import { ListsModule } from './lists/lists.module';

import { ShopForMeRoutes } from './shopforme.routing';

import { DialogService } from '../../services/dialog.service';
import {
  ShopformeDataService,
} from '../../services';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    ProductsModule,
    ProductsSharedModule ,
    ListsModule,
    RouterModule.forChild(ShopForMeRoutes),
  ],
  declarations: [
    ShopForMeComponent,
    SecondaryMenuComponent
],
  providers: [
    ShopformeDataService,
    DialogService
  ]
})

export class ShopForMeModule {}