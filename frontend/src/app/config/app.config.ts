import { InjectionToken } from '@angular/core';

import { MAppConfig } from './mapp.config';

export let APP_CONFIG = new InjectionToken('app.config');

export const AppConfig: MAppConfig = {
  routes: {
    home: 'home',
    products: 'products'
  },
  authImages: [
    '/assets/images/logo/logo.png',
    '/assets/images/img/shopping.jpg',
    '/assets/images/img/market.jpg'
  ],
  swipeOption : {
    pagination: '.swiper-pagination',
    paginationClickable: true,

    nextButton: '.swiper-button-next',
    prevButton: '.swiper-button-prev',

    // AutoPlay
    autoplay: 10000,
    speed: 1500,
    watchSlidesProgress: true,

    // Loop
    loop: true,

    // Position
    slidesPerView: 'auto', //If "auto" or slidesPerView > 1, enable watchSlidesVisibility for lazy load
    spaceBetween: 20,

    // Keyboard and Mousewheel
    keyboardControl: true,
    mousewheelControl: true,
    mousewheelForceToAxis: true, // just use the horizontal axis to 

    // Lazy Loading 
    watchSlidesVisibility: true,
    preloadImages: false,
    lazyLoading: true,

  },
  snackBarDuration: 3000,
};