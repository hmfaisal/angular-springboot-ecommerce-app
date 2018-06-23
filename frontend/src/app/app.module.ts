import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClient} from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { BidiModule } from '@angular/cdk/bidi';
import { APP_CONFIG, AppConfig } from './config/app.config';
import { ProgressBarService } from './services/progress-bar.service';
import { ProgressInterceptor } from './shared/interceptors/progress.interceptor';
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';

import { AppRoutes } from './app.routing';
import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { CoreModule } from './core/core.module';
import { AgmCoreModule } from '@agm/core';
import { LoginGuard, GuestGuard } from './guards';
import {
  ApiService,
  AuthService,
  UserService,
  ConfigService,
  AppService,
  MetaService,
  TimeService,
  GeocodingApiService ,
  MenudataService,
  ShareDataService,
  ListService,
  ListProductService,
  RequestService,
  AcceptService,
  ShoppingProductService,
  UserInfoService
} from './services';


export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

export function initUserFactory(userService: UserService) {
    return () => userService.initUser();
}

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    SharedModule,
    CoreModule,
    RouterModule.forRoot(AppRoutes),
    FormsModule,
    ReactiveFormsModule ,
    HttpClientModule,  
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAznme6ZNoGVfMRlVMBRUuOQpSXSO8gUUQ',
      libraries: ['places'],
      language: 'en-US',
    }),
    Ng4LoadingSpinnerModule.forRoot(),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      }
    }),
    BidiModule 
  ],
  providers: [
    LoginGuard,
    GuestGuard,
    AuthService,
    ApiService,
    UserService,
    ConfigService,
    AppService,
    TimeService,
    GeocodingApiService,
    MenudataService,
    ShareDataService,
    ListService,
    ListProductService,
    RequestService,
    AcceptService,
    ShoppingProductService,
    UserInfoService,
    MetaService,
    {
      provide: APP_CONFIG, 
      useValue: AppConfig
    },
    {
      provide: APP_INITIALIZER,
      useFactory: initUserFactory,
      deps: [UserService],
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS, 
      useClass: ProgressInterceptor, 
      multi: true, 
      deps: [ProgressBarService]
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }