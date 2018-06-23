import { Component, OnInit, Input, Output, EventEmitter, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MenuItems } from '../../../shared/menu-items/menu-items';
import { MenudataService } from '../../../services/menudata.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-secondary-menu',
  templateUrl: './secondary-menu.component.html',
  styleUrls: ['./secondary-menu.component.scss']
})
export class SecondaryMenuComponent implements OnInit, OnDestroy {

  //@Input() secondmenuitem: any;
  @Input() isSmenuShow: boolean;
  @Input() isFmenuShow: boolean;
  @Input() isMoreMenuShow: boolean;
  private subscription = new Subscription();  
  selectedIndex: number;
  fselectedIndex: number;
  menus: any[] = [];
  mainMenu: any[] = [];
  filterMenu:any[] = [];
  selectedMenu: string;
  
  constructor(
    public menuItems: MenuItems,
    private router: Router,
    private menuDataService: MenudataService,
  ) {
    
   }

  ngOnInit() {
    this.subscription.add(this.menuDataService.currentMenuTrigger.subscribe(value => {
      this.isSmenuShow = value;
    }));
    this.subscription.add(this.menuDataService.currentMoreMenuTrigger.subscribe(value => {
      this.isMoreMenuShow = value;
    }));
    this.subscription.add(this.menuDataService.currentFMenuTrigger.subscribe(value => {
      this.isFmenuShow = value;
    }));
    this.getMenuItems();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getMenuItems(){
    for(let menuitem of this.menuItems.getAll()){
      if(menuitem.type === 'shopforme'){
        for(let childitem of menuitem.children){
          if(childitem.type != "more"){
            this.menus.push(childitem);
          }if(childitem.type != "filter"){
            this.mainMenu.push(childitem);
          }else{
            this.filterMenu.push(childitem);
          }
        }
      }
    }
  }

  triggerMenu(childitem, index: number) {
    this.selectedMenu = childitem.name;
    this.isFmenuShow= false;
    this.isMoreMenuShow= false;
    this.fselectedIndex = undefined;
    if(this.selectedIndex == index && this.selectedMenu!="LISTS"){
      this.isSmenuShow= !this.isSmenuShow;
    }else if(this.selectedMenu=="LISTS"){
      this.sendView(this.selectedMenu);
    }else if(this.selectedMenu=="PRODUCTS"){
      this.sendView(this.selectedMenu);
    }else{
      this.isSmenuShow= true;
    }
    this.selectedIndex = index;
  }

  triggerMoreMenu(childitem, index: number) {
    this.isFmenuShow= false;
    this.isSmenuShow= false;
    this.fselectedIndex = undefined;
    if(this.selectedIndex == index){
      this.isMoreMenuShow= !this.isMoreMenuShow;
    }else{
      this.isMoreMenuShow= true;
    }
    this.selectedIndex = index;
  }

  ftriggerMenu(childitem, index: number) {
    this.isSmenuShow= false;
    this.selectedIndex = undefined;
    if(this.fselectedIndex == index){
      this.isFmenuShow= !this.isFmenuShow;
    }else{
      this.isFmenuShow= true;
    }
    this.fselectedIndex = index;
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }
  

}
