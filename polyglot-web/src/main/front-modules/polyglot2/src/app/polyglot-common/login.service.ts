import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private val: string;

  constructor() { }

   setValue(v:string){
    this.val = v;
   }

   getValue(): string{
     return this.val;
   }
}
