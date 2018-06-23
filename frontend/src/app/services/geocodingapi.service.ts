import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class GeocodingApiService {
    API_KEY: string;
    API_URL: string;

    constructor(private http: HttpClient) {
        this.API_KEY = 'AIzaSyAznme6ZNoGVfMRlVMBRUuOQpSXSO8gUUQ'
        this.API_URL = `https://maps.googleapis.com/maps/api/geocode/json?key=${this.API_KEY}&address=`;
    }

    findFromAddress(address: string, postalCode?: string, place?: string, province?: string, region?: string, country?: string): Observable<any> {
        let compositeAddress = [address];

        if (postalCode) compositeAddress.push(postalCode);
        if (place) compositeAddress.push(place);
        if (province) compositeAddress.push(province);
        if (region) compositeAddress.push(region);
        if (country) compositeAddress.push(country);

        let url = `${this.API_URL}${compositeAddress.join(',')}`;

        return this.http.get(url);
    }

}