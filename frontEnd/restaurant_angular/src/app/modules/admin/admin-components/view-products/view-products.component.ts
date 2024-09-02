import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from '../../admin-services/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzButtonSize } from 'ng-zorro-antd/button';

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrl: './view-products.component.scss'
})
export class ViewProductsComponent {


  categoryId!: number; 
  Products: any = [];
  isSpinning: boolean;
  validateForm!: FormGroup;
  size: NzButtonSize = 'large';
  


  constructor (private adminService: AdminService, 
    private activatedroute: ActivatedRoute,
    private fb: FormBuilder) {}


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
  this.adminService.getProductsByCategoryAndTitle(this.categoryId, this.validateForm.get(['title'])!.value).subscribe((res) => {
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
  this.adminService.getProductsByCategory(this.categoryId).subscribe((res) => {
  res.forEach(element => {
    element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
    this.Products.push(element);
    });
  });
 }


}

