import { Component, OnInit } from '@angular/core';
import{TradeModel} from "../trademodel";
import { TradeService } from "../trade.service"
import { LoginComponent} from "../login/login.component"
import {ShareService} from "../share.service";
@Component({
  selector: 'app-ledger',
  templateUrl: './ledger.component.html',
  styleUrls: ['./ledger.component.css']
})
export class LedgerComponent implements OnInit {
  mail: string;
  mad: string;
  model:TradeModel;
  tradeList: TradeModel[];
  constructor(private service:TradeService,private trader:LoginComponent,private shareService:ShareService) {}
  ngOnInit() {
    this.loadTradeList();  }

   loadTradeList(){
    
      this.service.getAll(this.mad).subscribe((response) =>{
        this.tradeList=response;
        console.log(this.tradeList);
        
        
   
      })
   }

}
