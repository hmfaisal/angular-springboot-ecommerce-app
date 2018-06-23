import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { COUNTRIES } from './../../model/countryList';

import {
  MenudataService,
  ShopformeDataService,
  ShareDataService,
  GeocodingApiService
} from '../../services';

@Component({
  selector: 'app-shopforme',
  templateUrl: './shopforme.component.html',
  styleUrls: ['./shopforme.component.scss']
})
export class ShopForMeComponent implements OnInit, OnDestroy {

  @Input() currentview: string;
  @Input() currentData: any;
  private subscription = new Subscription();
  country: any;
  city: any;
  countries=COUNTRIES;

  constructor(
    private menuDataService: MenudataService,
    private shopformeDataService: ShopformeDataService,
    private shareDataService: ShareDataService,
    private geocodingApiService: GeocodingApiService,
    private http: HttpClient
  ) {

  }

  ngOnInit() {
    this.getCurrentIpLocation();
    setTimeout(() => {
      this.currentview = "PRODUCTS";
      this.sendUserPosition();
    }, 1000);
    this.subscription.add(this.menuDataService.currentView.subscribe(value => {
      this.shopformeDataService.clear();
      this.currentview = value;
    }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.clear();
  }

  getCurrentIpLocation() {
    this.http.get('http://ipinfo.io').subscribe(data => {
      let geolocationPosition: any = data;
      this.city = geolocationPosition.city;
      //let checkCountry = this.httpGet('assets/data/country.json');
      for (let country of this.countries) {
        if (geolocationPosition.country == country.code) {
          this.country= country.name;
        }
      }
    });
  }

  httpGet(theUrl): any {
    const xmlHttp = new XMLHttpRequest();
    xmlHttp.open('GET', theUrl, false); // false for synchronous request
    xmlHttp.send(null);
    return JSON.parse(xmlHttp.responseText);
  }

  sendUserPosition() {
    this.shareDataService.changeCountry(this.country);
    this.shareDataService.changeCity(this.city);
  }

  onClickedOutside() {
    this.menuDataService.changeMenuTrigger(false);
    this.menuDataService.changeFMenuTrigger(false);
    this.menuDataService.changeMoreMenuTrigger(false);
  }

  clear() {
    this.menuDataService.clear();
    this.shopformeDataService.clear();
    this.shareDataService.clear();
  }


}
