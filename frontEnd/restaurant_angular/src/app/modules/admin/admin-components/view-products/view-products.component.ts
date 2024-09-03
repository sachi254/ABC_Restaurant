import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from '../../admin-services/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzButtonSize } from 'ng-zorro-antd/button';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal'; // Addedto get message

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
    private message: NzMessageService,
    private modal: NzModalService, // Inject NzModalService
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



deleteProduct(productId: any) {
  this.modal.confirm({
    nzTitle: 'Are you sure you want to delete this product?',
    nzContent: 'This action cannot be undone.',
    nzOkText: 'Yes',
    nzOkType: 'primary',
    nzOnOk: () => this.confirmDeleteProduct(productId),
    nzCancelText: 'No',
    nzOnCancel: () => console.log('Deletion canceled')
  });
}

confirmDeleteProduct(productId: any) {
  this.adminService.deleteProduct(productId).subscribe(
    () => {
      this.getProductsByCategory();
      this.message.success('Product deleted successfully.', { nzDuration: 5000 });
    },
    () => {
      this.message.error('Something went wrong.', { nzDuration: 5000 });
    }
  );
}





}

