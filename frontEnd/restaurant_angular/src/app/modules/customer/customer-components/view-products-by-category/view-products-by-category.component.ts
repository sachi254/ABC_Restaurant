import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NzButtonSize } from 'ng-zorro-antd/button';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { AdminService } from '../../../admin/admin-services/admin.service';
import { CustomerService } from '../../customer-service/customer.service';

@Component({
  selector: 'app-view-products-by-category',
  templateUrl: './view-products-by-category.component.html',
  styleUrl: './view-products-by-category.component.scss'
})
export class ViewProductsByCategoryComponent {



  categoryId!: number; 
  Products: any = [];
  isSpinning: boolean = false;
  validateForm!: FormGroup;
  size: NzButtonSize = 'large';
  


  constructor (private customerService: CustomerService, 
    private activatedroute: ActivatedRoute,
    private message: NzMessageService,
    private modal: NzModalService, // Inject NzModalService
    private fb: FormBuilder
  ) {}


  ngOnInit(): void {
  this.categoryId = this.activatedroute.snapshot.params['categoryId'];
   this.validateForm = this.fb.group({
   title: [null, [Validators.required]],
   });
  this.getProductsByCategory();
}



submitForm() {
  this.isSpinning = true;
  this. Products = [];
  this.customerService.getProductsByCategoryAndTitle(this.categoryId, this.validateForm.get(['title'])!.value).subscribe((res) => {
   console.log(res);
  res.forEach(element => {
    element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
  this.Products.push(element);
  this.isSpinning = false;
  });
  });
  }

  
  

  
  
  getProductsByCategory() {
    this.Products = [];
    this.customerService.getProductsByCategory(this.categoryId).subscribe((res) => {
      console.log(res);
    res.forEach(element => {
      element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
      this.Products.push(element);
      });
    });
   }




}
