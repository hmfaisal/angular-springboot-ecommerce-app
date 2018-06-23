import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import {
  UserService,
  AuthService
} from '../../../../services';

@Component({
  selector: 'app-dual',
  templateUrl: './dual.component.html',
  styleUrls: ['./dual.component.scss']
})
export class DualComponent implements OnInit {

  constructor(
    private router:Router,
    private userService: UserService,
    private authService: AuthService,
  ) { }

  ngOnInit() {
  }

  route(id:any){
    
    if(id=="shopper" && this.hasSignedIn()){
      this.router.navigate(['./goingshopping']);
    }else if(id=="requester" && this.hasSignedIn()){
      this.router.navigate(['./shopforme']);
    }else{
      this.router.navigate(['./auth/login']);
    }
  }

  hasSignedIn() {
    return !!this.userService.currentUser;
  }

}
