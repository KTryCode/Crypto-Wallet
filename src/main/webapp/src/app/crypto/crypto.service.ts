import {EventEmitter, Injectable} from '@angular/core';
import {Http} from "@angular/http";
import "rxjs/add/operator/map";
import 'rxjs/Rx';
import {Coin} from "./coin.model";

@Injectable()
export class CryptoService {

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

  updatePrices(){
    console.log("cryptoService.updatePrices() called");
    return this.http.get('/api/courses/update').map(response => response.json());
  }
}
