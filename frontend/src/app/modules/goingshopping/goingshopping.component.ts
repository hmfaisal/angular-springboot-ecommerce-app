import { Component, OnInit, OnDestroy, Input } from '@angular/core';;
import { Subscription } from 'rxjs/Subscription';
import { HttpClient } from '@angular/common/http';
import { COUNTRIES } from './../../model/countryList';

import { 
  MenudataService,
  BasketDataService,
  ShareDataService
} from '../../services';

@Component({
  selector: 'app-goingshopping',
  templateUrl: './goingshopping.component.html',
  styleUrls: ['./goingshopping.component.scss']
})
export class GoingShoppingComponent implements OnInit, OnDestroy {

  @Input() currentview: string;
  private subscription = new Subscription();
  country:any;
  city:any;
  countries=COUNTRIES;

  constructor(
    private menuDataService: MenudataService,
    private shareDataService: ShareDataService,
    private basketDataService: BasketDataService,
    private http: HttpClient
  ) {
    
  }

  ngOnInit() {
    this.getCurrentIpLocation();
    setTimeout(() => {
      this.currentview = "REQUESTS";
      this.sendUserPosition();
    }, 1000);
    this.subscription.add(this.menuDataService.currentView.subscribe(value => {
      this.basketDataService.clear();
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

  sendUserPosition(){
    this.shareDataService.changeCountry(this.country);
    this.shareDataService.changeCity(this.city);
  }

  onClickedOutside(){
    this.menuDataService.changeMenuTrigger(false);
  }

  clear(){
    this.menuDataService.clear();
    this.basketDataService.clear();
    this.shareDataService.clear();
  }

}
