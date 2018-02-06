import { Component, OnInit } from '@angular/core';
import {CryptoService} from "../crypto.service";
import {Coin} from "../coin.model";

@Component({
  selector: 'app-crypto-remove',
  templateUrl: './crypto-remove.component.html',
  styleUrls: ['./crypto-remove.component.css']
})
export class CryptoRemoveComponent implements OnInit {

  constructor(private cryptoService: CryptoService) { }

  ngOnInit() {
  }

  //TODO implement
  onDelete(coin: Coin){
    this.cryptoService.removeCoin(coin)
  }
}
