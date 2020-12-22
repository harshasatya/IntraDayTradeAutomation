import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from "@angular/router";
import { ClassService } from "../class.service";
import { TradeService } from "../trade.service";
import { ClassModel } from "../class-model";
import { TradeModel } from "../trademodel";
import { LoginComponent} from "../login/login.component";
import {ShareService} from "../share.service";
@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.css']
})
export class BuyComponent implements OnInit {

   
  h2;
  f;
  flag1 = 0;
  flag2 = 0;
  flag3 = 0;
  interval;
  timer;
  btn;
  constructor(private route: ActivatedRoute, private service: TradeService, private router: Router,private shareService: ShareService) {
   }
  mail:string;

  ngOnInit() {


  }
  trade = {
    quantity:""
   }
   data: TradeModel;
   getfriend()
   {
     console.log(this.trade.quantity);
     this.service.buy(this.trade.quantity).subscribe((response) =>{
       this.data = response;
        });
   }
  
  


}
