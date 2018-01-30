import { Component, OnInit } from '@angular/core';
import {Coin} from "../coin.model";
import {CryptoService} from "../crypto.service";

@Component({
  selector: 'app-crypto-add',
  templateUrl: './crypto-add.component.html',
  styleUrls: ['./crypto-add.component.css']
})
export class CryptoAddComponent implements OnInit {

  added_coin_symbol: string =null;
  added_coin_amount:number = null;

  constructor(private cryptoService: CryptoService) { }

  ngOnInit() {
  }

  onCoinAdd(){
    let coin : Coin = new Coin(this.added_coin_symbol, this.added_coin_amount, null, null, null);
    console.log("added coin symbol: ", this.added_coin_symbol);
    console.log("added coin amount: ", this.added_coin_amount);

    this.cryptoService.addCoin(coin)
      .subscribe(
        (newCoin: Coin) => {
          this.added_coin_amount = null;
          this.added_coin_symbol = null;
          this.cryptoService.onCoinAdded.emit(newCoin);
        })

  }
}
