import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AutoCompleteComponent } from './auto-complete.component';
import { AutoCompleteSearchService } from './auto-complete.service';
import { LocalStorageService } from './storage.service';
import { GlobalRef, BrowserGlobalRef } from './windowRef.service';
@NgModule({
  declarations: [
    AutoCompleteComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    AutoCompleteComponent
  ],
  providers : [{ provide: GlobalRef, useClass: BrowserGlobalRef }, AutoCompleteSearchService, LocalStorageService]
})
export class Ng4GeoautocompleteModule {

  static forRoot(): ModuleWithProviders {
    return {
      ngModule: Ng4GeoautocompleteModule
    };
  }

}
