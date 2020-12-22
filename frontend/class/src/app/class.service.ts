import { Injectable } from '@angular/core'
import {ClassModel} from "./class-model";
import { HttpClient,HttpHeaders } from "@angular/common/http"
@Injectable({
  providedIn: 'root'
})
export class ClassService {
  classUrl: string = "http://localhost:4444/Example/rest/register/users";

  constructor(private http: HttpClient) { }
  create (obj){
      let body= JSON.stringify(obj);
    let httpOptions = {
      headers: new HttpHeaders({
          "Content-Type" : "application/json"
      })
    };
    return this.http.post(this.classUrl, body, httpOptions);
  }
}
