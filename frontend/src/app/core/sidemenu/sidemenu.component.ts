import { Component, OnInit, Output, EventEmitter, Input} from '@angular/core';
import { Router } from '@angular/router';
import {
  AuthService
} from '../../services';


@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.scss']
})
export class SidemenuComponent implements OnInit {

  @Input() menuitem: any;
  @Output() showEvent = new EventEmitter<boolean>();
  @Output() langChange = new EventEmitter<string>();

  constructor(
    private router: Router,
    private authService: AuthService,
  ) {

  }

  ngOnInit() {
  }

  logout() {
    this.authService.logout().subscribe(res => {
      this.router.navigate(['/']);
    });
  }

  sidemenuClose(){
    this.showEvent.emit();
  }

  changeLanguage(language: string){
    this.langChange.emit(language);
  }

}
