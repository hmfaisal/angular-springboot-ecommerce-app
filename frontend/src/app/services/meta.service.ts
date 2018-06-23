import { Injectable } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { NavigationEnd, Router } from '@angular/router';

@Injectable()
export class MetaService {

    constructor(
        private title: Title,
        private meta: Meta,
        private router: Router
    ) { }

    setMeta(){
        this.title.setTitle('BasketBird');
        this.router.events.subscribe((event: any) => {
          if (event instanceof NavigationEnd) {
            switch (event.urlAfterRedirects) {
              case '/':
                this.meta.updateTag({
                  name: 'Basketbird',
                  content: 'A crowdshopping experience'
                });
                break;
              case '/' + 'products':
                this.title.setTitle('Products');
                this.meta.updateTag({
                  name: 'Product',
                  content: 'List of Products'
                });
                break;
            }
          }
        });
    }

}