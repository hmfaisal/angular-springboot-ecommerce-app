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

  @Input() secondmenuitem: any;
  @Input() isSmenuShow: boolean;
  private subscription = new Subscription();  
  selectedIndex: number;
  menus: any[] = [];
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
    this.getMenuItems();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getMenuItems(){
    for(let menuitem of this.menuItems.getAll()){
      if(menuitem.type === 'basket'){
        for(let childitem of menuitem.children){
          this.menus.push(childitem);
        }
      }
    }
  }

  triggerMenu(childitem, index: number) {
    this.selectedMenu = childitem.name;
    this.sendView(this.selectedMenu);
    this.selectedIndex = index;
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }
  

}
