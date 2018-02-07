import {Component, OnInit, Input} from '@angular/core';
import {CryptoService} from "../crypto.service";
import {Coin} from "../coin.model";

@Component({
  selector: 'app-crypto-remove',
  templateUrl: './crypto-remove.component.html',
  styleUrls: ['./crypto-remove.component.css']
})
export class CryptoRemoveComponent implements OnInit {
  @Input() coin: Coin;

  constructor(private cryptoService: CryptoService) {
  }

  ngOnInit() {
  }

  //TODO implement
  onDelete() {
    console.log("Deleting {}...", this.coin.symbol);
    this.cryptoService.removeCoin(this.coin).subscribe((coin) => console.log(coin));
  }
}
