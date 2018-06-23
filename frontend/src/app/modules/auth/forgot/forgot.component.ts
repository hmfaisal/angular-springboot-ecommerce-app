import { Inject } from '@angular/core';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DisplayMessage } from '../../../shared/display-message/display-message';
import { Subscription } from 'rxjs/Subscription';
import { CustomValidators } from 'ng2-validation';
import { MatSnackBar } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import {
  UserService,
  AuthService
} from '../../../services';

import { Observable, Subject } from 'rxjs';
//import 'rxjs/add/observable/interval';

const username = new FormControl('', Validators.compose([Validators.required]));
const email = username;

@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrls: ['./forgot.component.scss']
})
export class ForgotComponent implements OnInit {

  form: FormGroup;
  submitted = false;
  notification: DisplayMessage;
  error: string;
  success:string;
  private subscription = new Subscription();
  private ngUnsubscribe: Subject<void> = new Subject<void>();

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
    this.route.params
      .takeUntil(this.ngUnsubscribe)
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    this.form = this.formBuilder.group({
      username: username,
      email: email
    });
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onSubmit() {
    this.notification = undefined;
    this.submitted = true;
    this.authService.register(this.form.value)
      // show me the animation
      //.delay(1000)
      .subscribe(data => {
        this.router.navigate(['/auth/login']);
        this.snackBar.open(this.success, 'X', {
          duration: 4000,
        });
      },
      error => {
        this.submitted = false;
        this.notification = { msgType: 'error', msgBody: 'Something went wrong.' };
      });
  }

}
