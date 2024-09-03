import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { DashboardComponent } from './customer-components/dashboard/dashboard.component';
import { DemoNgZorroAntdModule } from '../../DemoNgZorroAntdModule';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { ViewProductsByCategoryComponent } from './customer-components/view-products-by-category/view-products-by-category.component';


@NgModule({
  declarations: [
    DashboardComponent,
    ViewProductsByCategoryComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule, 
    HttpClientModule
  ]
})
export class CustomerModule { }
