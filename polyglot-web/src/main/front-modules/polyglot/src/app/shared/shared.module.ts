import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TableNavigationComponent} from './table-navigation/table-navigation.component';
import {InputDebounceDirective} from "./input-debounce.directive";

@NgModule({
  declarations: [TableNavigationComponent, InputDebounceDirective],
  exports: [TableNavigationComponent, InputDebounceDirective],
  imports: [
    CommonModule
  ]
})
export class SharedModule {
}
