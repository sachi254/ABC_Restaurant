import { Component } from '@angular/core';
import { StaffService } from '../../staff-services/staff.service';
import { NzButtonSize } from 'ng-zorro-antd/button';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';

@Component({
  selector: 'app-staff-get-reservations',
  templateUrl: './staff-get-reservations.component.html',
  styleUrl: './staff-get-reservations.component.scss'
})
export class StaffGetReservationsComponent {




  reservations: any;
  isSpinning: boolean = false;
  size: NzButtonSize = 'large';

  constructor(
    private staffService: StaffService,
    private message: NzMessageService,
    private router: Router) { }
    
  
  
  
    ngOnInit() {
     this.getReservationsByUser();
      
     
      }


      
      getReservationsByUser(){
        this.staffService.getReservations().subscribe((res) => {
          console.log(res);
          this.reservations = res;
        })
      }
   
      changeReservationStatus(reservationId: number, status: string){
        console.log(reservationId);
        console.log(status);
        this.staffService.changeReservationStatus(reservationId, status).subscribe((res) => {
          console.log(res);
          this.reservations = res;

          if (res.id != null) {
            this.message.success('Request Submitted Successfully.',{nzDuration: 5000 });
            this.router.navigateByUrl('/staff/reservations');
            } else {
            this.message.error('Something went wrong', {nzDuration: 5000 } );
          }
        })
      
      }
        



}
