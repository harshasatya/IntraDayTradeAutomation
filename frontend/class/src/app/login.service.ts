import { Injectable } from '@angular/core'
import {ClassModel} from "./class-model";
import { HttpClient,HttpHeaders } from "@angular/common/http"
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  trader = {
    email:"",
    password:"",
    cardnumber: "",
    expiry_date :""
  }
  classUrl: string = "http://localhost:4444/Example/rest/trades/details";

  constructor(private http: HttpClient) { }
  
    getDetails(email: String,password: String){
    let url=this.classUrl+"/"+ email + "/" + password;
     return this.http.get<number>(url);
    //return this.productList.find(c => c.productId == id);
  }
}
