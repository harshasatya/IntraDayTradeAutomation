import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params,Router } from "@angular/router";
import {ClassModel} from "../class-model";
import {TradeService} from "../trade.service";
import {ShareService} from "../share.service";
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 data :number;
  sub: Subscription;
 member: string;
  trader = {
    email:"",
    password:"",
    cardnumber: "",
    expiry_date :""
  }
  constructor(private router: Router,private route: ActivatedRoute,private service: TradeService,private shareService: ShareService) { }
  getfriend()
  {
   
   
  console.log(this.trader)
    this.service.getDetails(this.trader.email,this.trader.password).subscribe((response) =>{
       this.data = response;
       if(this.data == 1)
      {   
         this.shareService.sendinfo(this.trader.email);
         this.router.navigate(['dashboard']);
      }
      else
      { 
        alert("username and password does not match....try again!!!")
        this.router.navigate(['login']);
      }
    });
    
     
  } 

  ngOnInit() {
   
     
  }
}