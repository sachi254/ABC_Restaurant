import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './staff-components/dashboard/dashboard.component';
import { StaffGetReservationsComponent } from './staff-components/staff-get-reservations/staff-get-reservations.component';

const routes: Routes = [

  {path:"dashboard",component:DashboardComponent},
  {path:"reservations",component:StaffGetReservationsComponent},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StaffRoutingModule { }
