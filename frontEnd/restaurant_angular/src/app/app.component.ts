import { Component } from '@angular/core';
import { StorageService } from './auth-services/storage-service/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'restaurant_angular';
  

isAdminLoggedIn: boolean = StorageService.isAdminLoggedIn();
isCustomerLoggedIn: boolean = StorageService.isCustomerLoggedIn();
isStaffLoggedIn: boolean = StorageService.isStaffLoggedIn();

  constructor(private router: Router){}

ngOnInit(){
  this.router.events.subscribe (event => {
      if(event.constructor.name === "NavigationEnd"){
        this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
        this.isCustomerLoggedIn = StorageService.isCustomerLoggedIn();
        this.isStaffLoggedIn = StorageService.isStaffLoggedIn();
      }
  })
}


logout(){
  StorageService.signout();
  this.router.navigateByUrl("/login");
}


}
