import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CustomerService } from '../../customer-service/customer.service';
import { NzButtonSize } from 'ng-zorro-antd/button';

@Component({
  selector: 'app-get-all-reservations',
  templateUrl: './get-all-reservations.component.html',
  styleUrl: './get-all-reservations.component.scss'
})
export class GetAllReservationsComponent {

  reservations: any;
  isSpinning: boolean = false;
  size: NzButtonSize = 'large';

  constructor(private fb: FormBuilder,
    private customerService: CustomerService,
    private message: NzMessageService,
    private router: Router) { }
    
  
  
  
    ngOnInit() {
     this.getReservationsByUser();
      
     
      }


      
      getReservationsByUser(){
        this.customerService.getReservationsByUser().subscribe((res) => {
          console.log(res);
          this.reservations = res;
        })
      }
    


}
