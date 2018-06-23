import { Component, Inject } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { APP_CONFIG, AppConfig } from './config/app.config';
import { MAppConfig } from './config/mapp.config';

import {
  UserService,
  AuthService,
  AppService,
  MetaService
} from './services';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  appConfig: any;
  template: string =
  `
  <link rel="stylesheet" href="">
  <div class="container-load">
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
    <div class="block-load"></div>
  </div>
  `

  constructor(
    @Inject(APP_CONFIG) appConfig: MAppConfig,
    private translate: TranslateService,
    private metaService: MetaService,
    private appService: AppService,
    private userService: UserService,
    private authService: AuthService,
  ) {
    this.metaService.setMeta();
    this.appService.checkBrowserFeatures();
    this.appConfig = appConfig;
    this.translate = translate;
    translate.addLangs(['en', 'fr', 'de']);
    translate.setDefaultLang('en');
    const browserLang: string = translate.getBrowserLang();
    translate.use(browserLang.match(/en|fr|de/) ? browserLang : 'en');
  }

  hasSignedIn() {
    return !!this.userService.currentUser;
  }

}