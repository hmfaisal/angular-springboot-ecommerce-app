import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

import {
  UserService,
  MenudataService,
} from '../../services';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  //@Output() sidemenuToggle = new EventEmitter();
  @Input() menuitem: any;
  selectedMenu: string;

  constructor(
    private translate: TranslateService,
    private userService: UserService,
    private menuDataService: MenudataService,
  ) { }

  ngOnInit() {
  }

  changeLanguage(language: string): void {
    this.translate.use(language);
  }

  hasSignedIn() {
    return !!this.userService.currentUser;
  }

  selectMenu(menu) {
    this.selectedMenu = menu.name;
    this.sendView(this.selectedMenu);
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }

}
