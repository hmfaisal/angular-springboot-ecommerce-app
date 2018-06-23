import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatDialogConfig } from '@angular/material';
import { Observable } from 'rxjs/Rx';

import { AddNewProductToListComponent } from '../modules/shopforme/lists/components/add-new-product-to-list/add-new-product-to-list.component';
import { AddNewProductToRequestComponent } from '../modules/shopforme/lists/components/add-new-product-to-request/add-new-product-to-request.component';
import { PermissionDialogComponent } from '../modules/common-compo/permissionDialog/permissionDialog.component';
import { AddressDialogComponent } from '../modules/shopforme/lists/date-address/addressDialog/addressDialog.component';
import { DelAddressDialogComponent } from '../modules/basket/shared-compo/date-address/delAddressDialog/delAddressDialog.component';

@Injectable()
export class DialogService {

    constructor(
        private dialog: MatDialog
    ) { }

    public addProductToList(listProduct:any,product:any): Observable<boolean> {
        let dialogRef = this.dialog.open(AddNewProductToListComponent, {
            data: {
                listProduct:listProduct,
                product: product
            },
          });
        return dialogRef.afterClosed();
    }

    public addProductToRequest(shoppingProduct:any,product:any): Observable<boolean> {
        let dialogRef = this.dialog.open(AddNewProductToRequestComponent, {
            data: {
                shoppingProduct:shoppingProduct,
                product: product
            },
          });
        return dialogRef.afterClosed();
    }

    public givePermission(name:string): Observable<boolean> {
        let dialogRef = this.dialog.open(PermissionDialogComponent, {
            data: name,
          });
        return dialogRef.afterClosed();
    }

    public saveAddress(delAddress:any): Observable<boolean> {
        let dialogRef = this.dialog.open(AddressDialogComponent, {
            data: delAddress,
          });
        return dialogRef.afterClosed();
    }

    public saveDelAddress(delAddress:any): Observable<boolean> {
        let dialogRef = this.dialog.open(DelAddressDialogComponent, {
            data: delAddress,
          });
        return dialogRef.afterClosed();
    }

}