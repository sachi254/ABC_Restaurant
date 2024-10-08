import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth-services/storage-service/storage.service';


const BASIC_URL =[ "http://localhost:8080/"]


@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }


  postCategory(categoryDto: any):Observable<any> {
    console.log(this.creatAuthorizationHeader())
    return this.http.post<[]>(BASIC_URL + "api/admin/category", categoryDto,
  {
    headers:this.creatAuthorizationHeader()
  })

  }


  getAllCategories():Observable<any> {
    return this.http.get<[]>(BASIC_URL + "api/admin/categories",
  {
    headers:this.creatAuthorizationHeader()
  })
  }


  getAllCategoriesByTitle(title: String): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/admin/categories/${title}`, {       
        headers: this.creatAuthorizationHeader()
    });
}

//Here "${BASIC_URL}" able to recognizes the use of the title variable to TypeScript.


//Start the prduct operations

  postProduct(categoryId: number, productDto: any):Observable<any> {
    return this.http.post<[]>(`${BASIC_URL}api/admin/${categoryId}/product`, productDto,
  {
    headers:this.creatAuthorizationHeader()
  })

  }



  getProductsByCategory(categoryId: number): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/admin/${categoryId}/products`, {       
        headers: this.creatAuthorizationHeader()
    });
}



getProductsByCategoryAndTitle(categoryId: any, title: String): Observable<any> {
  return this.http.get<[]>(`${BASIC_URL}api/admin/${categoryId}/product/${title}`, {       
      headers: this.creatAuthorizationHeader()
  });
}


deleteProduct(productId: number): Observable<any> {
  return this.http.delete<[]>(`${BASIC_URL}api/admin/product/${productId}`, 
  {       
      headers: this.creatAuthorizationHeader()
  });
}


getProductsById(productId: number): Observable<any> {
  return this.http.get<[]>(`${BASIC_URL}api/admin/product/${productId}`, {       
      headers: this.creatAuthorizationHeader()
  });
}



updateProduct(productId: number, productDto: any): Observable<any> {
  const formData = new FormData();
  formData.append('name', productDto.name);
  formData.append('price', productDto.price);
  formData.append('description', productDto.description);
  if (productDto.img) {
      formData.append('img', productDto.img);
  }

  return this.http.put(`${BASIC_URL}api/admin/product/${productId}`, formData, {
      headers: this.creatAuthorizationHeader()
  });
}



// get 
//all reservations 

getReservations(): Observable<any> {
  return this.http.get<[]>(`${BASIC_URL}api/admin/reservations`, {       
      headers: this.creatAuthorizationHeader()
  });
}


changeReservationStatus(reservationId: number, status: string): Observable<any> {
  return this.http.get<[]>(`${BASIC_URL}api/admin/reservation/${reservationId}/${status}`, {       
      headers: this.creatAuthorizationHeader()
  });
}




  creatAuthorizationHeader():HttpHeaders{
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      "Authorization", "Bearer " + StorageService.getToken()
    );
  }


  

}
