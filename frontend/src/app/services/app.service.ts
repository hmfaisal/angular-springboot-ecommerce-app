import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBar } from '@angular/material';

declare const Modernizr;

@Injectable()
export class AppService {

    constructor(
        private translate: TranslateService,
        private snackBar: MatSnackBar,
    ) { }

    checkBrowserFeatures() {
        let supported = true;
        for (let feature in Modernizr) {
            if (Modernizr.hasOwnProperty(feature) &&
                typeof Modernizr[feature] === 'boolean' && Modernizr[feature] === false) {
                supported = false;
                break;
            }
        }

        if (!supported) {
            this.translate.get(['updateBrowser']).subscribe((texts) => {
                this.snackBar.open(texts['updateBrowser'], 'OK');
            });
        }

        return supported;
    }

}