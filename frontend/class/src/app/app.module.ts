import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { jqxChartComponent } from 'jqwidgets-scripts/jqwidgets-ts/angular_jqxchart';
import {RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BuyComponent } from './buy/buy.component';
import { SellComponent } from './sell/sell.component';
import { HomeComponent } from './home/home.component';
import{TradeService} from './trade.service';
import { LedgerComponent } from './ledger/ledger.component';
import { AboutusComponent } from './aboutus/aboutus.component';
import { ContactusComponent } from './contactus/contactus.component';
import { CurrencyComponent } from './currency/currency.component';
import { MfstComponent } from './mfst/mfst.component';
import { AaplComponent } from './aapl/aapl.component'
const appRoutes: Routes = [
  {
path: '',
component: HomeComponent
  },
  {
path: 'login',
component: LoginComponent
  },
  {
path: 'ledger',
component: LedgerComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'buy',
    component: BuyComponent
  },
   {
    path: 'sell',
    component: SellComponent
  },
  {
    path: 'aboutus',
    component: AboutusComponent
  },
  {
    path: 'contactus',
    component: ContactusComponent
  },
  {
    path: 'currency',
    component: CurrencyComponent
  },
  {
    path: 'mfst',
    component: MfstComponent
  },
  {
    path: 'aapl',
    component: AaplComponent
  }
];
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    jqxChartComponent,
    BuyComponent,
    SellComponent,
    HomeComponent,
    LedgerComponent,
    AboutusComponent,
    ContactusComponent,
    CurrencyComponent,
    MfstComponent,
    AaplComponent
  ], 
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [TradeService,LoginComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
