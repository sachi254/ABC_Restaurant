import { Component } from '@angular/core';
import { AuthService } from '../../auth-services/auth-service/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from '../../auth-services/storage-service/storage.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

loginForm: FormGroup;
isSpinning: boolean;

  constructor(private service: AuthService,
    private fb: FormBuilder,
  private router:Router,
  private message: NzMessageService){ }

  ngOnInit(){
    this.loginForm = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, Validators.required],
    });
  }

  submitForm(){



    if (!this.loginForm.valid) {
      // Display specific validation messages
      if (this.loginForm.controls['email'].hasError('required')) {
        this.message.error('Please fill in all required fields', { nzDuration: 5000 });
      } else if (this.loginForm.controls['email'].hasError('email')) {
        this.message.error('Please enter a valid email', { nzDuration: 5000 });
      } else if (this.loginForm.controls['password'].hasError('required')) {
        this.message.error('Password is required', { nzDuration: 5000 });
      }
      return; // Stop submission if the form is invalid
    }

    this.isSpinning = true;










    this.service.login(this.loginForm.value).subscribe((res)=>{
      console.log(res);

      this.isSpinning = false;

if(res.userId != null){
const user = {
  id:res.userId,
  role:res.userRole
}
console.log(user);
StorageService.saveToken(res.jwt);
StorageService.saveUser(user);

if(StorageService.isAdminLoggedIn()){
  this.router.navigateByUrl("admin/dashboard");
}else if(StorageService.isCustomerLoggedIn()){
  this.router.navigateByUrl("customer/dashboard");
}else if(StorageService.isStaffLoggedIn()){
  this.router.navigateByUrl("staff/dashboard");
}

}else {
  console.log("wrong credentials")
  this.message.error('Wrong credentials, please try again', { nzDuration: 5000 });
}

}, error => {
  this.isSpinning = false; 
  this.message.error('Login failed. Please try again later.', { nzDuration: 5000 });
  console.log('Error:', error); // Log the error for debugging
});
}
}