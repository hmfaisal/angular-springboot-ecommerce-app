import { Component, NgZone, OnInit, OnDestroy, ViewChild, HostListener, Inject } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { MenuItems } from '../../shared/menu-items/menu-items';
import { Subscription } from 'rxjs/Subscription';
//import 'rxjs/add/operator/filter';
import { TranslateService } from '@ngx-translate/core';
import { APP_CONFIG, AppConfig } from '../../config/app.config';
import { MAppConfig } from '../../config/mapp.config';
import { PerfectScrollbarConfigInterface, PerfectScrollbarDirective } from 'ngx-perfect-scrollbar';

import {
  UserService,
  AuthService,
  ProgressBarService,
} from '../../services';

//const SMALL_WIDTH_BREAKPOINT = 960;

@Component({
  selector: 'app-layout',
  templateUrl: './app-layout.component.html',
  styleUrls: ['./app-layout.component.scss']
})
export class AppLayoutComponent implements OnInit, OnDestroy {

 // mediaMatcher: MediaQueryList = matchMedia(`(max-width: ${SMALL_WIDTH_BREAKPOINT}px)`);
  private _router: Subscription;
  today: number = Date.now();
  url: string;
  dir = 'ltr';
  sidePanelOpened;
  appConfig: any;
  sconfig: SwiperOptions = AppConfig.swipeOption;
  progressBarMode: string;

  @ViewChild('sidemenu') sidemenu;
  @ViewChild(PerfectScrollbarDirective) directiveScroll: PerfectScrollbarDirective;

  public config: PerfectScrollbarConfigInterface = {};

  constructor(
    @Inject(APP_CONFIG) appConfig: MAppConfig,
    public menuItems: MenuItems,
    private router: Router,
    zone: NgZone,
    private translate: TranslateService,
    private userService: UserService,
    private authService: AuthService,
    private progressBarService: ProgressBarService,
  ) {
    this.appConfig = appConfig;
    this.translate = translate;
    translate.addLangs(['en', 'fr', 'de']);
    translate.setDefaultLang('en');
    const browserLang: string = translate.getBrowserLang();
    translate.use(browserLang.match(/en|fr|de/) ? browserLang : 'en');
    //this.mediaMatcher.addListener(mql => zone.run(() => {
      //this.mediaMatcher = mql;
    //}));
    //this.progressBarService.updateProgressBar$.subscribe((mode: string) => {
      //this.progressBarMode = mode;
    //});
  }

  ngOnInit(): void {
    this.url = this.router.url;
    this._router = this.router.events.filter(event => event instanceof NavigationEnd).subscribe((event: NavigationEnd) => {
      document.querySelector('.app-inner > .mat-drawer-content > div').scrollTop = 0;
      this.url = event.url;
      this.runOnRouteChange();
    });

  }

  ngOnDestroy(): void {
    this._router.unsubscribe();
  }

  runOnRouteChange(): void {
    //this.updatePS();
  }

  /*
  updatePS(): void {
    if (!this.mediaMatcher.matches) {
      setTimeout(() => {
        this.directiveScroll.update();
      }, 350);
    }
  }
  */

  logout() {
    this.authService.logout().subscribe(res => {
      this.router.navigate(['/']);
    });
  }

  hasSignedIn() {
    return !!this.userService.currentUser;
  }

  changeLanguage(language: string): void {
    this.translate.use(language);
  }

  sideClose($event) {
    this.sidemenu.close();
  }

}
