import {Component, OnInit} from '@angular/core';
import {CryptoService} from "../crypto.service";
import {Coin} from "../coin.model";
import {Http} from "@angular/http";

@Component({
  selector: 'app-crypto-update',
  templateUrl: './crypto-update.component.html',
  styleUrls: ['./crypto-update.component.css']
})
export class CryptoUpdateComponent implements OnInit {

  constructor(private cryptoService: CryptoService, private http: Http) {
  }

  ngOnInit() {
  }

  updatePrices() {
    this.cryptoService.onUpdate.emit(null);
    this.cryptoService.updatePrices().subscribe();
  }

  onPricesUpdate() {
    this.http.request("/api/wallet/update_values");
    console.log("crypto-update.onUpdate() called");
    return this.cryptoService.updatePrices()
      .subscribe(
        () => {
          this.cryptoService.onUpdate.emit();
        });

  }

}
