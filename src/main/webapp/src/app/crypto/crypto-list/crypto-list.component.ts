import {Component, OnInit} from '@angular/core';
import {Coin} from "../coin.model";
import {CryptoService} from "../crypto.service";


@Component({
  selector: 'app-crypto-list',
  templateUrl: './crypto-list.component.html',
  styleUrls: ['./crypto-list.component.css']
})

export class CryptoListComponent implements OnInit {

  private coins: Coin[] = [];

  constructor(private cryptoService: CryptoService) {
  }

  ngOnInit() {
    this.cryptoService.getCryptos().subscribe(
      (coins: any[]) => this.coins = coins,
      (error) => console.log(error));
  }

}
