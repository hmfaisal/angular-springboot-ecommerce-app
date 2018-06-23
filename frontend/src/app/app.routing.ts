import { Routes } from '@angular/router';

import { LoginGuard } from './guards';
import { GuestGuard } from './guards';

import { AppLayoutComponent } from './core/app-layout/app-layout.component';
import { AuthLayoutComponent } from './core/auth-layout/auth-layout.component';

export const AppRoutes: Routes = [{
  path: '',
  component: AppLayoutComponent,
  children: [{
    path: '',
    loadChildren: './modules/home/home.module#HomeModule'
  }, {
    path: 'shopforme',
    loadChildren: './modules/shopforme/shopforme.module#ShopForMeModule',
    canActivate: [LoginGuard]
  }, {
    path: 'goingshopping',
    loadChildren: './modules/goingshopping/goingshopping.module#GoingShoppingModule',
    canActivate: [LoginGuard]
  },{
    path: 'basket',
    loadChildren: './modules/basket/basket.module#BasketModule',
    canActivate: [LoginGuard]
  }, {
    path: 'user',
    loadChildren: './modules/user/user.module#UserModule',
    canActivate: [LoginGuard]
  }, {
    path: 'session',
    loadChildren: './modules/session/session.module#SessionModule',
  }, {
    path: 'site',
    loadChildren: './modules/site/site.module#SiteModule',
  }]
}, {
  path: '',
  component: AuthLayoutComponent,
  children: [{
    path: 'auth',
    loadChildren: './modules/auth/auth.module#AuthModule',
    canActivate: [GuestGuard]
  }]
},{
  path: '**',
  redirectTo: 'session/404'
}];