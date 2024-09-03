import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../../../auth-services/storage-service/storage.service';
import { Observable } from 'rxjs';

const BASIC_URL =[ "http://localhost:8080/"]

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }





  
  getAllCategories():Observable<any> {
    return this.http.get<[]>(BASIC_URL + "api/customer/categories",
  {
    headers:this.creatAuthorizationHeader()
  })

  }


  
  getAllCategoriesByName(title: string):Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/customer/categories/${title}`,
  {
    headers:this.creatAuthorizationHeader()
  })

  }


// view product

getProductsByCategory(categoryId: number): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/customer/${categoryId}/products`, {       
        headers: this.creatAuthorizationHeader()
    });
}


// search products by category id and the product name
getProductsByCategoryAndTitle(categoryId: any, title: String): Observable<any> {
  return this.http.get<[]>(`${BASIC_URL}api/customer/${categoryId}/product/${title}`, {       
      headers: this.creatAuthorizationHeader()
  });
}




//Reservation functions start

postReservation(reservationDto: any): Observable<any> {
  reservationDto.customerId = StorageService.getUserId();
  return this.http.post<[]>(BASIC_URL + `api/customer/reservation`,reservationDto, {       
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
