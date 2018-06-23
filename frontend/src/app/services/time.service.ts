import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class TimeService {

    constructor() {
       
    }

    getTimeZone(){
        let d = new Date();
        let n = d.getTimezoneOffset();
        let timezone = n / -60;
        return timezone;
    }

    convertToLocal(){

    }
}