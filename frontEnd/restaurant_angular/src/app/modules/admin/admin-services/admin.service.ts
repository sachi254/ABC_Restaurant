import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth-services/storage-service/storage.service';


const BASIC_URL = ["http://localhost:8080/"]


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

  /*

  getAllCategoriesByTitle(title: String): Observable<any> {
    return this.http.get<[]>(BASIC_URL + 'api/admin/categories/${title}', {
        headers: this.creatAuthorizationHeader()
    });
}

  */

  getAllCategoriesByTitle(title: String): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/admin/categories/${title}`, {       
        headers: this.creatAuthorizationHeader()
    });
}

//Here "${BASIC_URL}" able to recognizes the use of the title variable to TypeScript.


  creatAuthorizationHeader():HttpHeaders{
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      "Authorization", "Bearer " + StorageService.getToken()
    );
  }


}
