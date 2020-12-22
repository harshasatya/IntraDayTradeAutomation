import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from "@angular/router";
import { ClassService } from "../class.service";
import { TradeService } from "../trade.service";
import { ClassModel } from "../class-model";
import { TradeModel } from "../trademodel";
import { LoginComponent} from "../login/login.component";
import {ShareService} from "../share.service";
@Component({
  selector: 'app-currency',
  templateUrl: './currency.component.html',
  styleUrls: ['./currency.component.css']
})
export class CurrencyComponent implements OnInit {

  months: string[] = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

  source: any =
  {
    datatype: 'csv',
    datafields: [
      { name: 'timestamp' },
      { name: 'open' },
      { name: 'high' },
      { name: 'low' },
      { name: 'close' },
    ],
    url: 'https://www.alphavantage.co/query?function=FX_INTRADAY&from_symbol=EUR&to_symbol=USD&interval=5min&apikey=85D3HVNRSASR3WTJ&datatype=csv'
  };

  getWidth(): any {
    if (document.body.offsetWidth < 850) {
      return '90%';
    }

    return 600;
  }

  dataAdapter = new jqx.dataAdapter(this.source, { async: true, autoBind: true,autoRefresh:true, loadError: (xhr: any, status: any, error: any) => { alert('Error loading "' + this.source.url + '" : ' + error); } });
  //  value: new Date().setHours(-5.5),
  //source.refresh();


  toolTipCustomFormatFn = (value: any, itemIndex: any, serie: any, group: any, categoryValue: any, categoryAxis: any) => {
    let dataItem = this.dataAdapter.records[itemIndex];

    return '<DIV style="text-align:left"><b>Time: ' + categoryValue.getHours() + ":" +
      categoryValue.getMinutes() + '<br><b>Date:<br>' + categoryValue.getDate() + "-" + this.months[categoryValue.getMonth()] + '-' + categoryValue.getFullYear() +
      '</b><br />Open price: $' + value.open +
      '</b><br />Close price: $' + value.close +
      '</b><br />Low price: $' + value.low +
      '</b><br />High price: $' + value.high +

      '</DIV>';
  };

  padding: any = { left: 5, top: 5, right: 5, bottom: 5 };

  xAxis: any =
  {

    // type: 'dateTime'
    dataField: 'timestamp',
    type: 'date',
    unitInterval: 15,
    // baseUnit: 'second'
    baseUnit: 'minute',
    labels: { angle: -45, offset: { x: -17, y: 0 } },
    formatFunction: (value) => {
      // return (value.getHours());
      return jqx.dataFormat.formatDate(value, 'hh:mm', 'en-us');
    },
    gridLines: { step: 2 },
    valuesOnTicks: true,
    //  labels: { angle: -45, offset: { x: -17, y: 0 } }
  };

  seriesGroups: any[] =
  [
    {
      type: 'candlestick',
      columnsMaxWidth: 15,
      columnsMinWidth: 5,
      toolTipFormatFunction: this.toolTipCustomFormatFn,

      series: [
        {
          dataFieldClose: 'close',
          displayTextClose: ' Close price',
          dataFieldOpen: 'open',
          displayTextOpen: 'Open price',
          dataFieldHigh: 'high',
          displayTextHigh: 'High price',
          dataFieldLow: 'low',
          displayTextLow: 'Low price',
          displayText: 'USD / EURO',
          lineWidth: 1
        }
      ]
    },

  ];

  data: TradeModel;
  down:string;
  trade = {

    currency: "",
    quantity: "",
    profit: "",
    loss: "",
    time: ""
  }
  market ={
    market: "",
    class:"",
    company:""
  }
  h2;
  f;
  flag1 = 0;
  flag2 = 0;
  flag3 = 0;
  interval;
  timer;
  btn;
  constructor(private route: ActivatedRoute, private service: TradeService, private router: Router) {
   }
  mail:string;
  ngOnInit() {


  }
  gofun() {
    var market=this.market.market;
    var class1 =this.market.class;
    var company=this.market.company;
    var noo="";
    if((market.localeCompare(noo)==0)||(class1.localeCompare(noo)==0)||(company.localeCompare(noo)==0))
    {
      alert("please enter all the required fields");
    }
    else if((company.localeCompare('AAPL'))==0&&(class1.localeCompare('commodity'))==0)
    {
      this.router.navigate(["aapl"]);
    }
    else if((company.localeCompare('mfst'))==0&&(class1.localeCompare('commodity'))==0)
    {
      this.router.navigate(["mfst"]);
    }
    else
    {
       this.router.navigate(["currency"]);
    }

    
  }
  atfun() {
     if(this.flag3==0){

  this.f=true;
  this.flag3=1;
   this.timer= (<HTMLInputElement>document.getElementById("timervalue")).value;
   this.timer=(this.timer)*60;
   this.interval = setInterval(() => {
      
      if(this.timer > 0) {          
        this.timer--;
        
        (<HTMLInputElement>document.getElementById("timervalue")).value=this.timer;
        if(this.timer!=0)
        (<HTMLInputElement>document.getElementById("clk")).value=this.timer;
        else
        (<HTMLInputElement>document.getElementById("clk")).value="";
      } else {
    this.f=false;
    this.flag3=0;  
    this.btn = document.getElementById("btn");
    this.btn.checked = false;
    
    clearInterval(this.interval);
  
    }
  },1000)
  console.log(this.trade);
      this.service.trade(this.trade.quantity, this.trade.profit, this.trade.loss, this.trade.time)
      .subscribe((response:TradeModel) => {
        this.data = response;
        console.log(response)
    })
  
}
  else{
  this.f=false;
  this.flag3=0;
  this.timer=0;
          (<HTMLInputElement>document.getElementById("timervalue")).value=this.timer;
          (<HTMLInputElement>document.getElementById("clk")).value=this.timer;
              clearInterval(this.interval);
  this.service.off()
      .subscribe((response) => {
        this.data = response;
        console.log(response)
    })

}

  }


}
