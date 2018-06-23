import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';

import { HomeComponent } from './home.component';
import { LoaderComponent } from './components/loader/loader.component';
import { CoverComponent } from './components/cover/cover.component';
import { DualComponent } from './components/dual/dual.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeRoutes } from './home.routing';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(HomeRoutes),
    SharedModule,
  ],
  declarations: [ 
    HomeComponent,
    LoaderComponent,
    CoverComponent,
    DualComponent,
    FooterComponent
  ],
  exports:[

  ]
})

export class HomeModule {}
