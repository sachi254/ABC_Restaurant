import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { NzButtonSize } from 'ng-zorro-antd/button';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CustomerService } from '../../../customer/customer-service/customer.service';
import { AdminService } from '../../admin-services/admin.service';

@Component({
  selector: 'app-get-reservations',
  templateUrl: './get-reservations.component.html',
  styleUrl: './get-reservations.component.scss'
})
export class GetReservationsComponent {


  reservations: any;
  isSpinning: boolean = false;
  size: NzButtonSize = 'large';

  constructor(
    private adminService: AdminService) { }
    
  
  
  
    ngOnInit() {
     this.getReservationsByUser();
      
     
      }


      
      getReservationsByUser(){
        this.adminService.getReservations().subscribe((res) => {
          console.log(res);
          this.reservations = res;
        })
      }
    
      changeReservationStatus(reservationId: number, status: string){
        console.log(reservationId);
        console.log(status);
        this.adminService.changeReservationStatus(reservationId, status).subscribe((res) => {
          console.log(res);
          this.reservations = res;
        })
      
      }


}
