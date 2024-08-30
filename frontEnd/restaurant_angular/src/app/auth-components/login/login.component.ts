import { Component } from '@angular/core';
import { AuthService } from '../../auth-services/auth-service/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

loginForm: FormGroup;
isSpinning: boolean;

  constructor(private service: AuthService,
    private fb: FormBuilder){ }

  ngOnInit(){
    this.loginForm = this.fb.group({
        email:[null, Validators.required],
        password:[null, Validators.required],
    })
  }

  submitForm(){
    this.service.login(this.loginForm.value).subscribe((res)=>{
      console.log(res);
    })
    
  }

}
