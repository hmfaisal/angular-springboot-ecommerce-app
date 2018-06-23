import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import {SharedModule} from '../../shared/shared.module';

import { AuthRoutes } from './auth.routing';
import { ForgotComponent } from './forgot/forgot.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { UserService } from '../../services/user.service';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild(AuthRoutes),
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    ForgotComponent,
    LoginComponent,
    RegisterComponent
  ],
  providers:[
    UserService
  ]
})

export class AuthModule {}
