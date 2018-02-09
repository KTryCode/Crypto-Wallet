import {Component, OnInit} from '@angular/core';
import {FormControl, ReactiveFormsModule, FormGroup, Validators} from "@angular/forms";
import {Coin} from "../coin.model";
import {CryptoService} from "../crypto.service";

@Component({
  selector: 'app-crypto-add',
  templateUrl: './crypto-add.component.html',
  styleUrls: ['./crypto-add.component.css']
})
export class CryptoAddComponent implements OnInit {

  symbols = ['BTC', 'ETH', 'XRP', "XLM", 'QTUM', 'MIOTA', 'SUB', 'BCH'];

  model = new Coin(this.symbols[0], 1.2, null, null);

  constructor(private cryptoService: CryptoService) {
  }

  ngOnInit() {
  }

  onCoinAdd(event) {
    let coin: Coin = new Coin(null, null, null, null);

    this.cryptoService.addCoin(coin)
      .subscribe(
        (newCoin: Coin) => {
          // this.added_coin_amount = null;
          // this.added_coin_symbol = null;
          this.cryptoService.onCoinAdded.emit(newCoin);
        })

  }
  addCoin(){
    this.model=new Coin('XRP', 100, null, null);
  }
  onSubmit(){

  }

  // TODO: Remove this when we're done
  get diagnostic() {
    return JSON.stringify(this.model)
  }


}
