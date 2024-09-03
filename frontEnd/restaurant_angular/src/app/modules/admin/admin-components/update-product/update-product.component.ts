import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-services/admin.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrl: './update-product.component.scss'
})
export class UpdateProductComponent {


  productId!: any; // Use definite assignment assertion or initialize in ngOnInit
  validateForm!: FormGroup;
  selectedFile: any ; 
  imagePreview: string | ArrayBuffer | null = null ; 
  isSpinning = false;
  imgChanged = false;
  existingImage: string | null = null;


constructor(private fb: FormBuilder,
private message: NzMessageService,
private router: Router,
private adminService: AdminService,
private activatedroute: ActivatedRoute) { }


ngOnInit(): void {

this.productId = this.activatedroute.snapshot.params['productId']; 
this.validateForm = this.fb.group({
name: [null, [Validators.required]],
price: [null, [Validators.required]],
description: [null, [Validators.required]],
});
this.getProductById();
}
  
  getProductById() {
  this.adminService.getProductsById(this.productId).subscribe((res) => {
    console.log(res);
  const productDto = res;
  this.existingImage = 'data:image/jpeg;base64,' + res.returnedImg;
  this.validateForm.patchValue (productDto);
  })
  }


  updateProduct(): void {
    if (this.validateForm.valid) {
      this.isSpinning = true;
      const productData = {
          ...this.validateForm.value,
          img: this.imgChanged ? this.selectedFile : null
      };

      this.adminService.updateProduct(this.productId, productData).subscribe(
          (response) => {
              this.message.success('Product updated successfully!');
              this.isSpinning = false;
              this.router.navigate(['/admin/dashboard']); 
          },
          (error) => {
              this.message.error('Failed to update product. Please try again.');
              this.isSpinning = false;
          }
      );
  } else {
      this.message.error('Please fill out all required fields.');
  }
}


  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
    this.imgChanged = true;
    this.existingImage = null;
    }
   
    previewImage() {
      const reader = new FileReader();
      reader.onload = () => {
      this.imagePreview = reader.result;
      };
      reader.readAsDataURL(this.selectedFile);
      }
}