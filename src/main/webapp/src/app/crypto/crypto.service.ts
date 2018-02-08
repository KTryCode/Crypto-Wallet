import {EventEmitter, Injectable} from '@angular/core';
import {Http} from "@angular/http";
import "rxjs/add/operator/map";
import 'rxjs/Rx';
import {Coin} from "./coin.model";
import {map} from "rxjs/operator/map";

@Injectable()
export class CryptoService {

  onCoinAdded = new EventEmitter<Coin>();
  onCoinRemoved = new EventEmitter<Coin>();

  constructor(private http: Http) {
  }

  getCryptos() {
    return this.http.get('/api/wallet').map(response => response.json());
  }

  addCoin(coin: Coin) {
    return this.http.post('/api/wallet/add', coin).map(response => response.json());
  }

  removeCoin(coin: Coin) {
    console.log("Crypto_service.removeCoin() " + coin.symbol);
    return this.http.delete('/api/wallet/remove/' + coin.symbol).map(response =>
      response.json());
  }

  ifAlreadyExist(coin: Coin) {
    let searchedCoin = this.getCryptos()
      .filter((data) => data.symbol == coin.symbol)
      .subscribe((result) => {
        console.log("ifAlreadyOwned -> " + result);
      });
    console.log(searchedCoin != null);
    return searchedCoin != null;
  }
}
