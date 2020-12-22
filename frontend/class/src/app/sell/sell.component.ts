import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params ,Router} from "@angular/router";
import {TradeService} from "../trade.service";
import {TradeModel} from "../trademodel";
@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.css']
})
export class SellComponent implements OnInit {

   constructor(private route: ActivatedRoute,private service: TradeService, private router: Router) { }
   trade = {
    quantity:""
   }
   data: TradeModel;
   getfriend()
   {
     console.log(this.trade.quantity);
     this.service.sell(this.trade.quantity).subscribe((response) =>{
       this.data = response;
        });
   }
  ngOnInit() {
  }

}
