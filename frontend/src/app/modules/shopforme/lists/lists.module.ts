import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
import { DateAddressModule } from './date-address/date-address.module';
import { CommonCompoModule } from '../../common-compo/common-compo.module';

//import { ListsRoutes } from './lists.routing';
import { AddNewProductToListComponent } from './components/add-new-product-to-list/add-new-product-to-list.component';
import { AddNewProductToRequestComponent } from './components/add-new-product-to-request/add-new-product-to-request.component';
import { ListSingleComponent } from './components/listed/list-single/list-single.component';
import { ListDetailComponent } from './components/listed/block/list-detail/list-detail.component';
import { MyListComponent } from './components/myList/myList.component';
import { MyUnlistedComponent } from './components/unlisted/my-unlisted/my-unlisted.component';
import { UnlistedDetailComponent } from './components/unlisted/unlisted-detail/unlisted-detail.component';
import { ListProductComponent } from './components/listed/block/list-product/list-product.component';
import { ListListComponent } from './components/listed/block/list-list/list-list.component';
import { ListRequestComponent } from './components/listed/block/list-request/list-request.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    DateAddressModule,
    CommonCompoModule
  ],
  declarations: [
    AddNewProductToListComponent,
    AddNewProductToRequestComponent,
    MyListComponent,
    MyUnlistedComponent,
    ListSingleComponent ,
    ListDetailComponent,
    UnlistedDetailComponent,
    ListProductComponent,
    ListListComponent,
    ListRequestComponent,
  ],
  exports:[
    AddNewProductToListComponent,
    AddNewProductToRequestComponent,
    MyListComponent,
    MyUnlistedComponent,
    ListSingleComponent ,
    ListDetailComponent,
    UnlistedDetailComponent,
    ListProductComponent,
    ListListComponent,
    ListRequestComponent,
  ],
  providers:[
  ],
  entryComponents: [
    AddNewProductToListComponent,
    AddNewProductToRequestComponent
  ]
})
export class ListsModule { }