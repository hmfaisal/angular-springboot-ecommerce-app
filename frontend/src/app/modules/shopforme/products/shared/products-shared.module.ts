import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../../../shared/shared.module';

import { ProductSortComponent } from './product-sort/product-sort.component';
import { ProductSearchComponent } from './product-search/product-search.component';
import { ProductCategoryComponent } from './product-category/product-category.component';
import { ProductBrandComponent } from './product-brand/product-brand.component';
import { ProductRangeComponent } from './product-range/product-range.component';
import { ProductAddComponent } from './product-add/product-add.component';

import { UserService } from '../../../../services/user.service';
import { ProductService } from '../../../../services/product.service';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule
  ],
  declarations: [
    ProductSortComponent,
    ProductSearchComponent,
    ProductCategoryComponent,
    ProductBrandComponent,
    ProductRangeComponent,
    ProductAddComponent
],
  exports:[
    ProductSortComponent,
    ProductSearchComponent,
    ProductCategoryComponent,
    ProductBrandComponent,
    ProductRangeComponent,
    ProductAddComponent
  ],
  providers: [
    ProductService
  ],
})
export class ProductsSharedModule { }