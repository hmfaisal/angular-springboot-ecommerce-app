import { Injectable } from '@angular/core';

export interface BadgeItem {
  type: string;
  value: string;
}

export interface ChildrenItems {
  link: string;
  name: string;
  icon: string;
  type?: string;
}

export interface Menu {
  link: string;
  name: string;
  type: string;
  icon: string;
  badge?: BadgeItem[];
  children?: ChildrenItems[];
}

const MENUITEMS = [
  {
    link: '/' + 'auth' + '/' + 'login',
    name: 'LOGIN',
    type: 'guest',
    icon: 'lnr-enter'
  },
  {
    link: '/' + 'shopforme',
    name: 'SHOP_4_ME',
    type: 'link',
    icon: 'lnr-store'
  },
  {
    link: '/' + 'goingshopping',
    name: 'GOING_SHOPPING',
    type: 'link',
    icon: 'lnr-map'
  },
  {
    link: '/' + 'basket',
    name: 'BASKETS',
    type: 'link',
    icon: 'lnr-cart'
  },
  {
    link: 'sub',
    name: 'MORE',
    type: 'sub',
    icon: 'lnr-menu',
    children: [
      //{link: '/' + 'user'+ '/' + 'history', name: 'HISTORY',icon: 'lnr-database'},
      //{link: '/' + 'wallet',name: 'WALLET',icon: 'lnr-briefcase'},
      //{link: '/' + 'user'+ '/' + 'profile', name: 'PROFILE',icon: 'lnr-user'},
      //{link: '/' + 'user'+ '/' + 'settings', name: 'SETTINGS',icon: 'lnr-cog'},
    ]
  },
  {
    link: '/' + 'auth' + '/' + 'logout',
    name: 'LOGOUT',
    type: 'logout',
    icon: 'lnr-exit'
  },
  {
    link: 'shopforme',
    name: 'SHOP_4_ME',
    type: 'shopforme',
    icon: 'lnr-store',
    children: [
      {link: '/add' , name: 'ADD_ITEM', type: 'main', icon: 'lnr-file-add'},
      {link: '/' + 'shopforme', name: 'PRODUCTS', type: 'main', icon:'lnr-store'},
      {link: '/' + 'lists' + '/' + 'mylist', name: 'LISTS', type: 'main', icon:'lnr-calendar-full'},
      {link: 'search',name: 'SEARCH', type: 'main', icon:'lnr-magnifier'},
      
      {link: 'filter', name: 'FILTER', type: 'more', icon: 'lnr-list'},
      {link: 'category', name: 'CATEGORY', type: 'filter', icon: 'lnr-layers'},
      //{link: 'brand', name: 'BRAND', type: 'filter', icon: 'lnr-tag'},
      {link: 'price',name: 'PRICE', type: 'filter', icon: 'lnr-eye'},
      {
        link: 'sort',
        name: 'SORT', 
        type: 'filter', 
        icon:'lnr-sort-amount-asc',
        children: [
          {link: 'high', name: 'HIGH', type: 'main', icon: 'euro_symbol'},
          {link: 'low',name: 'LOW', type: 'main', icon: 'euro_symbol'},
        ]
      },
    ]
  },
  {
    link: 'goingshopping',
    name: 'GOING_SHOPPING',
    type: 'goingshopping',
    icon: 'lnr-map',
    children: [
      {link: 'goingshopping',name: 'REQUESTS',type: 'main',icon: 'lnr-map'},
      {link: 'distance',name: 'DISTANCE',type: 'main',icon: 'lnr-map-marker'},
      {link: 'time',name: 'TIME',type: 'main',icon: 'lnr-clock'},
      {link: 'price',name: 'PRICE', type: 'main', icon: 'lnr-eye'},
      //{link: 'checkin', name: 'CHECKIN',type: 'main',icon: 'lnr-map-marker'},
    ]
  },
  {
    link: 'basket',
    name: 'BASKETS',
    type: 'basket',
    icon: 'lnr-cart',
    children: [
      {link: 'request',name: 'REQUEST',type: 'main',icon: 'lnr-store'},
      {link: 'accept',name: 'ACCEPT',type: 'main',icon: 'lnr-map'},
    ]
  },
  {
    link: 'lang',
    name: 'LANGUAGE',
    type: 'lang',
    icon: 'lnr-earth',
    children: [
      {link: 'en',name: 'ENGLISH',icon:'lnr-flag'},
      {link: 'de',name: 'DEUTSCH',icon: 'lnr-flag'},
      //{link: 'fr',name: 'FRENCH',icon: 'lnr-flag'},
    ]
  }
];

@Injectable()
export class MenuItems {
  getAll(): Menu[] {
    return MENUITEMS;
  }
}
