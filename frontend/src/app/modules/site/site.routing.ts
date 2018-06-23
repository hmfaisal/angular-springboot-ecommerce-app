import { Routes } from '@angular/router';

import { PolicyComponent } from './components/policy/policy.component';
import { CopyrightComponent } from './components/copyright/copyright.component';
import { TermsComponent } from './components/terms/terms.component';

export const SiteRoutes: Routes = [
  {
    path: '',
    children: [{
      path: 'policy',
      component: PolicyComponent
    },{
      path: 'copyright',
      component: CopyrightComponent
    },{
        path: 'terms',
        component: TermsComponent
      }]
  }
];
