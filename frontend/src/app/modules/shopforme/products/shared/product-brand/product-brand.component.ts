import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { MenudataService,
        ProductService 
      } from '../../../../../services';

@Component({
  selector: 'app-product-brand',
  templateUrl: './product-brand.component.html',
  styleUrls: ['./product-brand.component.scss']
})
export class ProductBrandComponent implements OnInit, OnDestroy  {

  @Input() menuitem: any;
  private subscription = new Subscription();
  brands: any[] = [];
  selectedIndex: number;
  selectedMenu: string;
  selectedBrand: string;

  constructor(
    private menuDataService: MenudataService,
    private productService: ProductService,
  ) { }

  ngOnInit() {
    this.getBrand();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getBrand(): void {
    this.subscription.add(this.productService.getBrand()
        .subscribe(
        brands => this.brands = brands,
    ));
  }

  selectMenu(brand, index: number) {
    this.selectedMenu = "BRAND";
    this.selectedBrand = brand;
    this.selectedIndex = index;
    this.sendView(this.selectedMenu);
    setTimeout(() => {
      this.sendData(this.selectedBrand);
    }, 1000); 
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }

  sendData(selectedData:string){
    this.menuDataService.changeData(selectedData);
  }

}
