import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import 'rxjs/add/observable/interval';

import { 
  MenudataService,
  ProductService 
} from '../../../../../services';

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.scss']
})

export class ProductSearchComponent implements OnInit {

  @Input() menuitem: any;
  selectedMenu: string;
  form: FormGroup;
  submitted = false;

  constructor(
    private menuDataService: MenudataService,
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      name: ['', Validators.compose([Validators.required])]
    });
  }

  onSubmit() {
    this.submitted = true;
    this.selectedMenu = "SEARCH";
    this.sendView(this.selectedMenu);
    setTimeout(() => {
      this.sendData(this.form.controls.name.value);
    }, 1000);   
  }

  sendView(selectedView:string){
    this.menuDataService.changeView(selectedView);
  }

  sendData(selectedData:any){
    this.menuDataService.changeData(selectedData);
  }


}
