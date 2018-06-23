import { Component, NgZone, OnInit, OnDestroy, ViewChild, HostListener, Inject } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { MenuItems } from '../../shared/menu-items/menu-items';
import { APP_CONFIG, AppConfig } from '../../config/app.config';
import { MAppConfig } from '../../config/mapp.config';

@Component({
  selector: 'auth-layout',
  templateUrl: './auth-layout.component.html',
  styleUrls: ['./auth-layout.component.scss']
})
export class AuthLayoutComponent implements OnInit{

  appConfig: any;
  config: SwiperOptions = AppConfig.swipeOption;


  constructor(
    @Inject(APP_CONFIG) appConfig: MAppConfig,
    public menuItems: MenuItems,
    public translate: TranslateService,
  ) {
    this.appConfig = appConfig;
    const browserLang: string = translate.getBrowserLang();
    translate.use(browserLang.match(/en|de|fr/) ? browserLang : 'en');
  }

  changeLanguage(language: string): void {
    this.translate.use(language);
  }

  ngOnInit(){

  }

}
