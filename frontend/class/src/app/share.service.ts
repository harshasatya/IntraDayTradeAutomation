import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ShareService {
  mad = new Subject<string>();
  cast=this.mad.asObservable();
  constructor() { }
  sendinfo(email){
    console.log(email);
    this.mad.next(email);
    console.log(this.mad);
  }
}
