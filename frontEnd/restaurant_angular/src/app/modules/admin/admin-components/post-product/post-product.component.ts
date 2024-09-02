import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-services/admin.service';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-post-product',
  templateUrl: './post-product.component.html',
  styleUrl: './post-product.component.scss'
})
export class PostProductComponent {

  categoryId!: number; // Use definite assignment assertion or initialize in ngOnInit
  validateForm!: FormGroup;
  selectedFile: File | null ; 
  imagePreview: string | ArrayBuffer | null ; 
  isSpinning = false;


constructor(private fb: FormBuilder,
private message: NzMessageService,
private router: Router,
private adminService: AdminService,
private activatedroute: ActivatedRoute) { }


ngOnInit(): void {

this.categoryId = this.activatedroute.snapshot.params['categoryId']; // Convert to number using '+'

  
this.validateForm = this.fb.group({
name: [null, [Validators.required]],
price: [null, [Validators.required]],
description: [null, [Validators.required]],
});
}


submitForm(): void {

  if (!this.validateForm.valid) {
    this.message.error('Please fill in all required fields.', { nzDuration: 5000 });
    return;
  }

  if (!this.selectedFile) {
    this.message.error('Please select an image file.', { nzDuration: 5000 });
    return;
  }
    

  this.isSpinning = true;
  const formData: FormData = new FormData();
  formData.append('img', this.selectedFile);
  formData.append('name', this.validateForm.get('name').value);
  formData.append('price', this.validateForm.get('price').value);
  formData.append('description', this.validateForm.get('description').value);
  this.adminService.postProduct(this.categoryId, formData).subscribe((res) => {
  this.isSpinning = false;
  if (res.id != null) {
  this.message.success('Product Posted Successfully.',{nzDuration: 5000 });
  this.router.navigateByUrl('/admin/dashboard');
  } else {
  this.message.error('Something went wrong', {nzDuration: 5000 } );
}
  });
}
  





onFileSelected(event: any) {
  this.selectedFile = event.target.files[0];
  this.previewImage();
  }

  
  previewImage() {
  const reader = new FileReader();
  reader.onload = () => {
  this.imagePreview = reader.result;
  };
  reader.readAsDataURL(this.selectedFile);
  }



 

  }





