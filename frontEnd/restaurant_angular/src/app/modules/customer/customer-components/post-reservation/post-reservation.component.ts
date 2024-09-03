import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CustomerService } from '../../customer-service/customer.service';

@Component({
  selector: 'app-post-reservation',
  templateUrl: './post-reservation.component.html',
  styleUrl: './post-reservation.component.scss'
})
export class PostReservationComponent {

 
  validateForm!: FormGroup;
  isSpinning: boolean = false;
  categoryId!: number; 


TableType: string[] = [
"Sigle-Table",
"Cuple-Style-Table",
"Family-Style-Table",
"Middle-Table",
"Coner-Table",
"Outdoor-Table",
"Window-Side-Table"
];


constructor(private fb: FormBuilder,
  private customerService:CustomerService,
  private message: NzMessageService,
  private router: Router) { }
  



  ngOnInit() {
    this.validateForm = this.fb.group({
    tableType: [null, [Validators.required]],
    dateTime: [null, [Validators.required]],
    description: [null, [Validators.required]],
    });
    }


    postReservation() {
      console.log(this.validateForm.value);
    
      this.customerService.postReservation(this.validateForm.value).subscribe((res) => {
        console.log(res);

      if (res.id != null) {
      this.message.success('Request Submitted Successfully.',{nzDuration: 5000 });
      this.router.navigateByUrl('/customer/reservation');
      } else {
      this.message.error('Something went wrong', {nzDuration: 5000 } );
    }
      });

   
    }

    
  }

