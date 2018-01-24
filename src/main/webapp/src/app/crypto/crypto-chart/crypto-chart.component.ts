import {Component, OnInit} from '@angular/core';
import {Chart} from 'chart.js'
import {CryptoService} from "../crypto.service";
import 'rxjs/add/operator/map';
import {forEach} from "@angular/router/src/utils/collection";


@Component({
  selector: 'app-crypto-chart',
  templateUrl: './crypto-chart.component.html',
  styleUrls: ['./crypto-chart.component.css']
})
export class CryptoChartComponent implements OnInit {

  chart = [];
  labels = [];
  coins = [];


  constructor(private cryptoService: CryptoService) {


  }
  ngOnInit() {

    let test = [];
    this.cryptoService.getCryptos().subscribe(
      (coins: any[]) => {
        this.coins = coins;

        this.coins.forEach(function (coin) {
          console.log(coin.symbol);
          test.push(coin.symbol)
        })

      },
      (error) => console.log(error));

    this.labels = test;

/*    let symbols = this.cryptoService.getCryptos().subscribe();
    console.log(symbols);
    symbols.then(){l

    }*/

    this.chart = new Chart('canvas', {
      type: 'doughnut',
      data: {
        labels: this.labels,
        //"a", "b", "c", "d"
        datasets: [
          {
            data: [2, 3, 5],
            borderColor: "#000000",
            backgroundColor: ["#ba1723", "#15ba13", "#1d1fba"],
            fill: true
          },
        ]
      },
      options: {
        legend: {
          display: false
        },

      }
    });
  }

}
