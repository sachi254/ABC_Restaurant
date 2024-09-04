import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth-services/storage-service/storage.service';

const BASIC_URL =[ "http://localhost:8080/"]


@Injectable({
  providedIn: 'root'
})
export class StaffService {

  
  constructor(private http: HttpClient) { }


  getAllCategories():Observable<any> {
    return this.http.get<[]>(BASIC_URL + "api/staff/categories",
  {
    headers:this.creatAuthorizationHeader()
  })

  }


  getAllCategoriesByName(title: string):Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/staff/categories/${title}`,
  {
    headers:this.creatAuthorizationHeader()
  })

  }


// view product

getProductsByCategory(categoryId: number): Observable<any> {
  return this.http.get<[]>(`${BASIC_URL}api/staff/${categoryId}/products`, {       
      headers: this.creatAuthorizationHeader()
  });
}


// search products by category id and the product name
getProductsByCategoryAndTitle(categoryId: any, title: String): Observable<any> {
return this.http.get<[]>(`${BASIC_URL}api/staff/${categoryId}/product/${title}`, {       
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
