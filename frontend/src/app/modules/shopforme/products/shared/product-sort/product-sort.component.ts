import { Component, OnInit, Input } from '@angular/core';
import { MenudataService } from '../../../../../services/menudata.service';

@Component({
  selector: 'app-product-sort',
  templateUrl: './product-sort.component.html',
  styleUrls: ['./product-sort.component.scss']
})

export class ProductSortComponent implements OnInit {

  @Input() menuitem: any;
  selectedIndex: number;
  selectedMenu: string;

  constructor(
    private menuDataService: MenudataService,
  ) { }

  ngOnInit() {
  }

  selectMenu(childitem, index: number) {
    this.selectedMenu = childitem.name;
    this.selectedIndex = index;
    this.sendView(this.selectedMenu);
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }

}
