import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StaffRoutingModule } from './staff-routing.module';
import { DashboardComponent } from './staff-components/dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { DemoNgZorroAntdModule } from '../../DemoNgZorroAntdModule';
import { StaffGetReservationsComponent } from './staff-components/staff-get-reservations/staff-get-reservations.component';



@NgModule({
  declarations: [
    DashboardComponent,
    StaffGetReservationsComponent
  ],
  imports: [
    CommonModule,
    StaffRoutingModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule, 
    HttpClientModule
  ]
})
export class StaffModule { }
