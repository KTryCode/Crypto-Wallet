import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import "rxjs/add/operator/map";
import 'rxjs/Rx';

@Injectable()
export class CryptoService {

  constructor(private http: Http) { }

  getCryptos(){
    return this.http.get('/api/crypto').map(response => response.json());
  }

  getValues(){
    return this.http.get('/api/crypto/update/coin_values').map(response => response.json());
  }

}
