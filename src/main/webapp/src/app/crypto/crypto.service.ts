import { Injectable } from '@angular/core';
import {Http} from "@angular/http";

@Injectable()
export class CryptoServiceService {

  constructor(private http:Http) { }

  getCrypto(){
    return this.http.get('/api/crypto')
  }

}
