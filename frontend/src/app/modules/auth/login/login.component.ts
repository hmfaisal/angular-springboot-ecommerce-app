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

const username = new FormControl('', Validators.compose([Validators.required]));
const email = username;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  submitted = false;
  notification: DisplayMessage;
  returnUrl: string;
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
    public snackBar: MatSnackBar,
  ) {
    this.subscription.add(translate.get('WRONG_ERROR').subscribe((res: string) => {
      this.error= res;
    }));
    this.subscription.add(translate.get('LOGIN_SUCCESSFUL').subscribe((result: string) => {
      this.success = result;
    }));

  }

  ngOnInit() {
    /*
    this.route.params
      .takeUntil(this.ngUnsubscribe)
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    */
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.form = this.formBuilder.group({
      username: username,
      email: email,
      password: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])]
    });
  }

  ngOnDestroy() {
    //this.ngUnsubscribe.next();
    //this.ngUnsubscribe.complete();
    this.subscription.unsubscribe();
  }

  onResetCredentials() {
    this.subscription.add(this.userService.resetCredentials()
      //.takeUntil(this.ngUnsubscribe)
      .subscribe(res => {
        if (res.result === 'success') {
          alert('Password has been reset to 123 for all accounts');
        } else {
          alert('Server error');
        }
      }));
  }

  onSubmit() {
    this.notification = undefined;
    this.submitted = true;

    this.subscription.add(this.authService.login(this.form.value)
      //.delay(1000)
      .subscribe(data => {
        //this.userService.getMyInfo().subscribe();
        this.snackBar.open(this.success, 'X', {
          duration: 2000,
        });
      this.router.navigate([this.returnUrl]);
      },
      error => {
        this.submitted = false;
        this.notification = { msgType: 'error', msgBody: this.error };
      }));

  }

  onfbLoginSubmit() {
    window.location.href='http://52.34.87.42:8080/auth/facebook';
    /*
    this.authService.fbLogin()
      .subscribe(data => {
        //this.userService.getMyInfo().subscribe();
        console.log(data);
        this.router.navigate(['/']);
      },
      error => {
        this.submitted = false;
        this.notification = { msgType: 'error', msgBody: 'Incorrect email or password.' };
      });
      */
  }

}
