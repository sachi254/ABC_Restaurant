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





  creatAuthorizationHeader():HttpHeaders{
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      "Authorization", "Bearer " + StorageService.getToken()
    );
  }



}
