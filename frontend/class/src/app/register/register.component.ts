import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params ,Router} from "@angular/router";
import {ClassService} from "../class.service";
import {ClassModel} from "../class-model";
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private route: ActivatedRoute,private service: ClassService, private router: Router) { 
  }
  data;
  trader = {
    name:"",
    email:"",
    password:"",
    repassword:"",
    phone:""
      }
  getFriend(){
    console.log(this.trader)
    var name=this.trader.name;
    var email=this.trader.email;
    var pass=this.trader.password;
    var repass=this.trader.repassword;
    var phone=this.trader.phone;
    var noo="";
    if((name.localeCompare(noo)==0)||(email.localeCompare(noo)==0)||(pass.localeCompare(noo)==0)||(repass.localeCompare(noo)==0)||(phone.localeCompare(noo)!=0))
    {
      alert("Fill all the required fields");
      this.router.navigate(['register']);
    }
    else if(pass.localeCompare(repass)!=0)
    {     
          console.log("password and repeat password not matching");
          alert("password and repeat password does not match");
          this.router.navigate(['register']);  
    }
    else
    {
        console.log("submit clicked");
        this.service.create(this.trader).subscribe({
         complete: () => {
         console.log("create completed");
         alert("successfully registered");
         this.router.navigate(['']);
       }
    });
   }
  }
  

  ngOnInit() {
    
  }

 

}