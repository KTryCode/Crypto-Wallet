import {Component, OnInit} from '@angular/core';
import {Coin} from "../coin.model";
import {CryptoService} from "../crypto.service";
import {stringify} from "@angular/compiler/src/util";


@Component({
  selector: 'app-crypto-list',
  templateUrl: './crypto-list.component.html',
  styleUrls: ['./crypto-list.component.css']
})

export class CryptoListComponent implements OnInit {

  master = 'Crypto-list';
  private coins: Coin[] = [];

  constructor(private cryptoService: CryptoService) {
  }

  ngOnInit() {
    this.cryptoService.getCryptos()
      .subscribe(
        (coins: any[]) => this.coins = coins,
        (error) => console.log(error));


    //TODO To update, not create new one
    this.cryptoService.onCoinAdded
      .subscribe(
        () => {
          this.cryptoService.getCryptos()
            .subscribe(
              (coins: any[]) => this.coins = coins,
              (error) => console.log(error));
        }
      );

    this.cryptoService.onCoinRemoved
      .subscribe(
        () => {
          this.cryptoService.getCryptos()
            .subscribe(
              (coins: any[]) => this.coins = coins,
              (error) => console.log(error));
        }
      );
  }

  colorGreenOrRedIfIsGreaterThanZero(value: number): string {
    return (value > 0 ? 'text-success' : 'text-danger');
  }

  getTotalValueUSD(): number {
    return this.cryptoService.getTotalValue(this.coins);
  }

  getTotalChange24h(): number {
    let totalChangePercent: number = 0;
    let totalValueUSD = this.getTotalValueUSD();
    let totalValueUSDBeforeChange = 0;
    for (let coin of this.coins) {
      totalValueUSDBeforeChange += coin.value / (1 + coin.coinData.percent_change_24h/100);
    }
    totalChangePercent = totalValueUSD / totalValueUSDBeforeChange;
    return totalChangePercent;
  }

  getTotalChange7d(): number {
    if (this.coins.length > 0) {
      let totalChangePercent: number;
      let totalValueUSD = this.getTotalValueUSD();
      let totalValueUSDBeforeChange = 0;

      for (let coin of this.coins) {
        let coinValueBeforeChange = coin.value / (1 + (coin.coinData.percent_change_7d / 100));
        totalValueUSDBeforeChange += coinValueBeforeChange;
      }
      totalChangePercent = totalValueUSD / totalValueUSDBeforeChange;
      return totalChangePercent;
    } else {
      return null;
    }
  }


}
