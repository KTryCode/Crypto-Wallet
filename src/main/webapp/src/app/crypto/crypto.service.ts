import {EventEmitter, Injectable} from '@angular/core';
import {Http} from "@angular/http";
import "rxjs/add/operator/map";
import 'rxjs/Rx';
import {Coin} from "./coin.model";
import {Subject} from "rxjs/Subject";

@Injectable()
export class CryptoService {

  public updatePricesSubject = new Subject<any>();
  onCoinAdded = new EventEmitter<Coin>();
  onUpdate = new EventEmitter();

  constructor(private http: Http) {
  }

  getCryptos() {
    return this.http.get('/api/wallet').map(response => response.json());
  }

  addCoin(coin: Coin) {
    return this.http.post('/api/wallet/add', coin).map(response => response.json());
  }

  updatePrices() {
    this.updatePricesSubject.next();
    this.http.request("/api/wallet/update_values");
    console.log("cryptoService.updatePrices() called");

    console.log("this.http.request('/api/courses/update');");
    this.http.request('/api/courses/update');

    console.log("    this.http.get('/api/courses/update').map(response => response.json());");
    return this.http.get('/api/courses/update').map(response => response.json());




  }
}
