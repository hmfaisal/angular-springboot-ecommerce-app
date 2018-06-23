import { Inject } from '@angular/core';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DisplayMessage } from '../../../shared/display-message/display-message';
import { Subscription } from 'rxjs/Subscription';
import { CustomValidators } from 'ng2-validation';
import { TranslateService } from '@ngx-translate/core';
import { MatSnackBar } from '@angular/material';
import {
  UserService,
  AuthService
} from '../../../services';

import { Observable, Subject } from 'rxjs';
//import 'rxjs/add/observable/interval';

const password = new FormControl('', Validators.compose([Validators.required, Validators.minLength(6), Validators.maxLength(32)]));
const confirmPassword = new FormControl('', CustomValidators.equalTo(password));
const username = new FormControl('', Validators.compose([Validators.required, CustomValidators.email]));
const email = username;

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  submitted = false;
  notification: DisplayMessage;
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  private subscription = new Subscription();
  error: string;
  success:string;

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private translate: TranslateService,
    public snackBar: MatSnackBar
  ) {
    this.subscription.add(translate.get('WRONG_ERROR').subscribe((res: string) => {
      this.error= res;
    }));
    this.subscription.add(translate.get('REGISTRATION_SUCCESSFUL').subscribe((result: string) => {
      this.success = result;
    }));
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: username,
      email: email,
      password: password,
      confirmPassword: confirmPassword
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  onSubmit() {
    this.notification = undefined;
    this.submitted = true;
    this.subscription.add(this.authService.register(this.form.value)
      .subscribe(data => {
        this.snackBar.open(this.success, 'X', {
          duration: 4000,
        });
        this.router.navigate(['/auth/login']);
      },
      error => {
        this.submitted = false;
        this.notification = { msgType: 'error', msgBody: this.error};
      }));
  }

}
