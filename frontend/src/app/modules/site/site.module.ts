import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';

import { SiteComponent } from './site.component';
import { PolicyComponent } from './components/policy/policy.component';
import { CopyrightComponent } from './components/copyright/copyright.component';
import { TermsComponent } from './components/terms/terms.component';
import { SiteRoutes } from './site.routing';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(SiteRoutes),
    SharedModule,
  ],
  declarations: [ 
    SiteComponent,
    PolicyComponent,
    CopyrightComponent,
    TermsComponent,
  ],
  exports:[

  ]
})

export class SiteModule {}
