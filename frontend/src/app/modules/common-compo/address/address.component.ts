import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { Address } from '../../../model/address';

import {
  ShareDataService,
} from '../../../services';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  address: Address;

  constructor(
    private shareDataService: ShareDataService,
  ) {
  }

  ngOnInit() {
    this.shareDataService.clearAdrAdd();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  autoCompleteCallback(data: any): any {
    let addressData: any ;
    let street_number: string;
    let route: string;
    let postal_code: string;
    let locality: any;
    let administrative_area_level: any;
    let country: string;
    let lat: any;
    let lng: any;

    addressData = JSON.parse(JSON.stringify(data));

    if (addressData.response==true && addressData.data) {
      lat = addressData.data.geometry.location.lat;
      lng = addressData.data.geometry.location.lng;
      for (let adr of addressData.data.address_components) {
        for (let data of adr.types) {
          if (data == "street_number") {
            street_number = adr.long_name;
          }
          if (data == "route") {
            route = adr.long_name;
          }
          if (data == "postal_code") {
            postal_code = adr.long_name;
          }
          if (data == "locality") {
            locality = adr.long_name;
            let n = locality.indexOf(' ','?','.', '\\-', '_','(',')');
            locality = locality.substring(0, n != -1 ? n : locality.length);
          }
          if (data == "administrative_area_level_1") {
            administrative_area_level = adr.long_name;
          }
          if (data == "country") {
            country = adr.long_name;
          }
        }
      }
      let url: any;
      url = this.constructUrl(route, street_number, postal_code, locality, country);
      this.createAddress(route, street_number, postal_code, locality, country, administrative_area_level, lat, lng, url);
    }
  }


  constructUrl(route: string, street_number: string, postal_code: string, locality: any, country: string) {
    let url: any;
    url = "https://maps.google.com/?q=" + route + "+" + street_number + ",+" + postal_code + "+" + locality + ",+" + country;
    return url;
  }

  createAddress(route: string, street_number: string, postal_code: string, locality: any, country: string, administrative_area_level: any, lat: any, lng: any, url: any) {
    this.address = {
      streetNumber: street_number,
      streetName: route,
      postcode: postal_code,
      city: locality,
      state: administrative_area_level,
      country: country,
      latitude: lat,
      longitude: lng,
      url: url
    };
    this.sendData(this.address);
  }

  sendData(data: any) {
    this.shareDataService.changeAdrAdd(data);
  }

}
