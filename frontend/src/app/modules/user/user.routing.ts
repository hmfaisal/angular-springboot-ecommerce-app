import { Routes } from '@angular/router';

import { HistoryComponent } from './history/history.component';
import { ProfileComponent } from './profile/profile.component';
import { SettingsComponent } from './settings/settings.component';

export const UserRoutes: Routes = [
  {
    path: '',
    children: [{
      path: 'history',
      component: HistoryComponent
    }, {
      path: 'profile',
      component: ProfileComponent
    }, {
      path: 'settings',
      component: SettingsComponent
    }]
  }
];
