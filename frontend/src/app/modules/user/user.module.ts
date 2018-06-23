import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import {SharedModule} from '../../shared/shared.module';

import { UserRoutes } from './user.routing';
import { ProfileComponent } from './profile/profile.component';
import { HistoryComponent } from './history/history.component';
import { SettingsComponent } from './settings/settings.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild(UserRoutes),
  ],
  declarations: [
    ProfileComponent,
    HistoryComponent,
    SettingsComponent
]
})
export class UserModule { }