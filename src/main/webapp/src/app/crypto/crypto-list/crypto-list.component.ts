import {Component, OnInit} from '@angular/core';
import {Coin} from "../coin.model";
import {CryptoService} from "../crypto.service";


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

  colorGreenOrRedIfIsGreaterThanZero(value : number){
    return value>0 ? 'text-success' : 'text-danger';
  }


}
