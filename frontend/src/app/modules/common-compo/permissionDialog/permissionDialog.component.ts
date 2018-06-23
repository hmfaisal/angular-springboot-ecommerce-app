import { Component, Inject, OnInit, OnDestroy} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Observable, Subject } from 'rxjs';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-permissionDialog',
  templateUrl: './permissionDialog.component.html',
  styleUrls: ['./permissionDialog.component.scss']
})
export class PermissionDialogComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();

  constructor(
    public dialogRef: MatDialogRef<PermissionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public name: string
  ) { }

  ngOnInit() {
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  confirmSelection(){
    this.dialogRef.close(true);
  }

  onDialogClose() {
    this.dialogRef.close();
  }

}
