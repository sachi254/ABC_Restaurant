import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './admin-components/dashboard/dashboard.component';
import { AddCategoryComponent } from './admin-components/add-category/add-category.component';
import { DemoNgZorroAntdModule } from '../../DemoNgZorroAntdModule';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PostProductComponent } from './admin-components/post-product/post-product.component';
import { ViewProductsComponent } from './admin-components/view-products/view-products.component';
import { UpdateProductComponent } from './admin-components/update-product/update-product.component';



@NgModule({
  declarations: [
    DashboardComponent,
    AddCategoryComponent,
    PostProductComponent,
    ViewProductsComponent,
    UpdateProductComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    DemoNgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule, 
    HttpClientModule
  ]
})
export class AdminModule { }
