import { NgModule } from '@angular/core';

import { MenuItems } from './menu-items/menu-items';
import { AccordionAnchorDirective, AccordionLinkDirective, AccordionDirective } from './accordion';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MaterialModule } from './material/material.module';
import { SwiperModule } from 'angular2-useful-swiper';
import { TranslateModule } from '@ngx-translate/core';
import { ClickOutsideModule } from 'ng-click-outside';
import { NouisliderModule } from 'ng2-nouislider';
import { NgxPaginationModule } from 'ngx-pagination';
import { Ng4GeoautocompleteModule } from './ng4-geoautocomplete';
import { AgmCoreOverrideModule } from './agmcoreoverride.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';


@NgModule({
  imports: [
    MaterialModule,
    FlexLayoutModule,
    TranslateModule,
    SwiperModule,
    ClickOutsideModule,
    NouisliderModule,
    NgxPaginationModule,
    Ng4GeoautocompleteModule,
    OwlDateTimeModule, 
    OwlNativeDateTimeModule,
    /*
    AgmCoreOverrideModule.forRoot({
      apiKey: '<key>'  // Not really needed.
    }),
    */
  ],
  declarations: [
    AccordionAnchorDirective,
    AccordionLinkDirective,
    AccordionDirective,
  ],
  exports: [
    AccordionAnchorDirective,
    AccordionLinkDirective,
    AccordionDirective,
    MaterialModule,
    FlexLayoutModule,
    TranslateModule,
    SwiperModule,
    ClickOutsideModule,
    NouisliderModule,
    NgxPaginationModule,
    Ng4GeoautocompleteModule,
    OwlDateTimeModule, 
    OwlNativeDateTimeModule,
   ],
  providers: [ MenuItems ]
})
export class SharedModule { }
