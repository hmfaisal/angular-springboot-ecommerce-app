import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { MenudataService,
        ProductService 
      } from '../../../../../services';

@Component({
  selector: 'app-product-category',
  templateUrl: './product-category.component.html',
  styleUrls: ['./product-category.component.scss']
})

export class ProductCategoryComponent implements OnInit, OnDestroy {

  @Input() menuitem: any;
  private subscription = new Subscription();
  categories: any[] = [];
  selectedIndex: number;
  selectedMenu: string;
  selectedCategory: string;

  constructor(
    private menuDataService: MenudataService,
    private productService: ProductService,
  ) { }

  ngOnInit() {
    this.getCategory();
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getCategory(): void {
    this.subscription.add(this.productService.getCategory()
        .subscribe(
          category => this.categories = category,
    ));
  }

  selectMenu(category, index: number) {
    this.selectedMenu = "CATEGORY";
    this.selectedCategory = category;
    this.selectedIndex = index;
    this.sendView(this.selectedMenu);
    setTimeout(() => {
      this.sendData(this.selectedCategory);
    }, 1000); 
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }

  sendData(selectedData:any){
    this.menuDataService.changeData(selectedData);
  }

}
