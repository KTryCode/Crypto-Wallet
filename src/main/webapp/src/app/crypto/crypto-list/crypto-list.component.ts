import {Component, OnInit} from '@angular/core';
import {Cryptocurrency} from "../crypto.model";
import {CryptoService} from "../crypto.service";

@Component({
  selector: 'app-crypto-list',
  templateUrl: './crypto-list.component.html',
  styleUrls: ['./crypto-list.component.css']
})

export class CryptoListComponent implements OnInit {

  cryptos: Cryptocurrency[] = [];

  constructor(private cryptoService: CryptoService) {
  }

  ngOnInit() {
    this.cryptoService.getCryptos().subscribe(
      (cryptos: any[]) => this.cryptos = cryptos,
      (error) => console.log(error));
  }

}
