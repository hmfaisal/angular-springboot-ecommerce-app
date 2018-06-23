import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
import { ListsModule } from '../lists/lists.module';

import { ProductAllComponent } from './components/product-all/product-all.component';
import { ProductSingleComponent } from './components/product-single/product-single.component';
import { ProductByCategoryComponent } from './components/product-by-category/product-by-category.component';
import { ProductByShopComponent } from './components/product-by-shop/product-by-shop.component';
import { ProductByRangeComponent } from './components/product-by-range/product-by-range.component';
import { ProductByHighComponent } from './components/product-by-high/product-by-high.component';
import { ProductByLowComponent } from './components/product-by-low/product-by-low.component';
import { ProductByNameComponent } from './components/product-by-name/product-by-name.component';

import { ProductsRoutes } from './products.routing';

import { ProductService } from '../../../services/product.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    //RouterModule.forChild(ProductsRoutes),
    SharedModule,
    ListsModule
  ],
  declarations: [
    ProductAllComponent,
    ProductSingleComponent,
    ProductByCategoryComponent,
    ProductByShopComponent,
    ProductByRangeComponent,
    ProductByHighComponent,
    ProductByLowComponent,
    ProductByNameComponent
  ],
  exports:[
    ProductAllComponent,
    ProductSingleComponent,
    ProductByCategoryComponent,
    ProductByShopComponent,
    ProductByRangeComponent,
    ProductByHighComponent,
    ProductByLowComponent,
    ProductByNameComponent
  ],
  providers: [
    ProductService
  ]
})
export class ProductsModule { }