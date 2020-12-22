import { Injectable } from '@angular/core'
import {ClassModel} from "./class-model";
import { HttpClient,HttpHeaders } from "@angular/common/http"
import {TradeModel} from "./trademodel";
import { Subscription} from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class TradeService {
  public mail: string;
   trader = {
    email:"",
    password:"",
    cardnumber: "",
    expiry_date :""
  }

  constructor(private http: HttpClient) { }
  
  public getDetails(email: string,password: string){
    this.mail=email;
    console.log(this.mail);
    let url="http://localhost:4444/Example/rest/trades/details"+"/"+ email + "/" + password;
     return this.http.get<number>(url);
  }
 
  trade(quantity: string,profit: string,loss: string,time: string){
   
    
    let url="http://localhost:4444/Example/rest/trades"+"/rsi/"+ quantity + "/" + profit + "/"+ loss + "/" +time;
     return this.http.get<TradeModel>(url);
    //return this.productList.find(c => c.productId == id);
  }
  getAll(mail: string) {
    console.log(mail);
    let url="http://localhost:4444/Example/rest/trades/ledger";
    return this.http.get<TradeModel[]>(url);
    //return this.productList;
  }
  buy(quantity: string)
  {
    let url="http://localhost:4444/Example/rest/trades"+"/buy/"+quantity;
    return this.http.get<TradeModel>(url);
  }
  sell(quantity: string)
  {
    let url="http://localhost:4444/Example/rest/trades"+"/sell/"+quantity;
    return this.http.get<TradeModel>(url);
  }
  off()
  {
    let url="http://localhost:4444/Example/rest/trades/atoff";
    return this.http.get<TradeModel>(url);
  }

}