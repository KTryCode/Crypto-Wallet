import {EventEmitter, Injectable} from '@angular/core';
import {Http} from "@angular/http";
import "rxjs/add/operator/map";
import 'rxjs/Rx';
import {Coin} from "./coin.model";

@Injectable()
export class CryptoService {

  onCoinAdded = new EventEmitter<Coin>();

  constructor(private http: Http) {
  }

  getCryptos() {
    return this.http.get('/api/wallet').map(response => response.json());
  }

  addCoin(coin: Coin) {
    console.log(this.ifAlreadyExist(coin));
    return this.http.post('/api/wallet/add', coin).map(response => response.json());
  }

  ifAlreadyExist(coin: Coin) {
    let costam = this.getCryptos()
      .filter((data) => data.symbol == coin.symbol)
      .subscribe((result) => {
          console.log("ifAlreadyExist -> " + result);
      });
    console.log(costam!=null);
    return costam != null;
  }
}
