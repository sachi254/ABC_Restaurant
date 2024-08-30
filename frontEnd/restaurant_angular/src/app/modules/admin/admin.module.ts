import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './admin-components/dashboard/dashboard.component';
import { AddCategoryComponent } from './admin-components/add-category/add-category.component';
import { DemoNgZorroAntdModule } from '../../DemoNgZorroAntdModule';



@NgModule({
  declarations: [
    DashboardComponent,
    AddCategoryComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    DemoNgZorroAntdModule
  ]
})
export class AdminModule { }
