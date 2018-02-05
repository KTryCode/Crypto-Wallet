import {Component, OnInit} from '@angular/core';
import {Coin} from "../coin.model";
import {CryptoService} from "../crypto.service";
import {Http} from "@angular/http";


@Component({
  selector: 'app-crypto-list',
  templateUrl: './crypto-list.component.html',
  styleUrls: ['./crypto-list.component.css']
})

export class CryptoListComponent implements OnInit {

  private coins: Coin[] = [];

  constructor(private cryptoService: CryptoService, private http: Http) {
  }

  ngOnInit() {
    this.getCryptos();

    this.cryptoService.onCoinAdded
      .subscribe(
        () => {
          this.getCryptos();
        }
      );

    this.cryptoService.updatePricesSubject
      .subscribe(
      () => {
        this.http.request("/api/wallet/");
        this.http.request("/api/wallet/update_values");
        this.http.request("/api/courses/list");
        this.http.request("/api/courses/update");
        console.log("Crypto-list-component update");
        this.getCryptos();
      }
    );

    this.cryptoService.onUpdate
      .subscribe(
        () => {
          this.http.request("/api/wallet/");
          this.http.request("/api/wallet/update_values");
          this.http.request("/api/courses/list");
          this.http.request("/api/courses/update");
          console.log("Crypto-list-component update");
          this.getCryptos();
        }
      )
  }

  private getCryptos() {
    this.cryptoService.getCryptos()
      .subscribe(
        (coins: any[]) => this.coins = coins,
        (error) => console.log(error));
    console.log("getCryptos called");
  }

}
