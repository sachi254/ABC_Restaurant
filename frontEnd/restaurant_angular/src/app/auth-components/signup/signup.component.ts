import { Component } from '@angular/core';
import { AuthService } from '../../auth-services/auth-service/auth.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {

  isSpinning:boolean;
  validateForm:FormGroup;

  confirmationValidator = (control:FormControl): { [s: string]: boolean } =>{
    if(!control.value){
        return {required:true};
    } else if (control.value !== this.validateForm.controls['password'].value){
        return{confirm:true,error:true}
    }
    return{};
  }

  constructor(private service: AuthService,
    private fb: FormBuilder,
  private notification: NzNotificationService){}






  ngOnInit(){
    this.validateForm = this.fb.group({
      email: ["", [Validators.required, Validators.email]], 
     
        password: ["", [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/)]], // Password pattern validation
      checkPassword:["",[Validators.required,this.confirmationValidator]],
      name:["",Validators.required],
    });
  }


  register() {
    if (this.validateForm.valid) { // Only submit if the form is valid
      this.isSpinning = true;
      console.log(this.validateForm.value);

      this.service.signup(this.validateForm.value).subscribe((res) => {
        console.log(res);

        if (res.id != null) {
          this.notification.success("SUCCESS", "You have registered successfully", { nzDuration: 5000 });
        } else {
          this.notification.error("ERROR", "Something went wrong, please try again", { nzDuration: 5000 });
        }

        this.isSpinning = false; // Stop spinner
      }, error => {
        this.isSpinning = false;
        this.notification.error("ERROR", "Registration failed, please check the details and try again", { nzDuration: 5000 });
        console.log('Error:', error);
      });
    } else {
      this.notification.error("ERROR", "Registration failed, please check the details and try again", { nzDuration: 5000 });
      this.validateForm.markAllAsTouched(); // Mark all controls as touched to show validation errors
    }
  }

}