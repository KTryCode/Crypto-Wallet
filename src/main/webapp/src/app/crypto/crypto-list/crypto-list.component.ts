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
    this.getCryptos();


    //TODO To update, not create new one
    this.cryptoService.onCoinAdded
      .subscribe(
        () => {
          this.getCryptos();
        }
      );

    this.cryptoService.onUpdate
      .subscribe(
        () => {
          this.getCryptos();
        }
      )
  }

  private getCryptos() {
    this.cryptoService.getCryptos()
      .subscribe(
        (coins: any[]) => this.coins = coins,
        (error) => console.log(error));
  }

}
