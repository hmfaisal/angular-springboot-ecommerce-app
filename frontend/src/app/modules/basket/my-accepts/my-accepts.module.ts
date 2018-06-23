import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
import { SharedCompoModule } from '../shared-compo/shared-compo.module';
import { MyAcceptComponent } from './components/my-accept/my-accept.component';
import { MyAcceptActiveComponent } from './components/my-accept-active/my-accept-active.component';
import { MyAcceptArchivedComponent } from './components/my-accept-archived/my-accept-archived.component';

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
    MyAcceptComponent,
    MyAcceptActiveComponent,
    MyAcceptArchivedComponent
  ],
  exports:[
    MyAcceptComponent,
    MyAcceptActiveComponent,
    MyAcceptArchivedComponent
  ],
  providers: [
    DialogService
  ],
  entryComponents: [
    MyAcceptActiveComponent,
    MyAcceptArchivedComponent
  ]
})
export class MyAcceptsModule { }