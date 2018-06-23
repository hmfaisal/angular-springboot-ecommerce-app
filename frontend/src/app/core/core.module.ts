import {NgModule, Optional, SkipSelf} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {throwIfAlreadyLoaded} from './module-import-guard';
import { ClickOutsideModule } from 'ng-click-outside';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';

import {ProgressBarService} from '../services/progress-bar.service';

import {HeaderComponent} from './header/header.component';
import {SidemenuComponent} from './sidemenu/sidemenu.component';
import {AppLayoutComponent} from './app-layout/app-layout.component';
import {AuthLayoutComponent} from './auth-layout/auth-layout.component';

import {SharedModule} from '../shared/shared.module';

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true,
  wheelSpeed: 2,
  wheelPropagation: true,
  minScrollbarLength: 20
};

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    ClickOutsideModule,
    SharedModule,
    PerfectScrollbarModule
  ],
  exports: [
    HeaderComponent,
    SidemenuComponent,
    AppLayoutComponent,
    AuthLayoutComponent,
  ],
  declarations: [
    HeaderComponent,
    SidemenuComponent,
    AppLayoutComponent,
    AuthLayoutComponent,
],
  providers: [
    {
      provide: PERFECT_SCROLLBAR_CONFIG,
      useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
    },
    ProgressBarService,
  ]
})

export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    throwIfAlreadyLoaded(parentModule, 'CoreModule');
  }
}