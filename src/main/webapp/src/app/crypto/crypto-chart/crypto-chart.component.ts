import {Component, OnInit} from '@angular/core';
import {Chart} from 'chart.js'
import {CryptoService} from "../crypto.service";
import 'rxjs/add/operator/map';


@Component({
  selector: 'app-crypto-chart',
  templateUrl: './crypto-chart.component.html',
  styleUrls: ['./crypto-chart.component.css']
})
export class CryptoChartComponent implements OnInit {

  chart = [];
  colorsDefault = [
    'rgba(255, 99, 132, 0.7)',
    'rgba(54, 162, 235, 0.7)',
    'rgba(255, 206, 86, 0.7)',
    'rgba(75, 192, 192, 0.7)',
    'rgba(153, 102, 255, 0.7)',
    'rgba(255, 159, 64, 0.7)',
    'rgba(235, 0, 40, 0.7)',
    'rgba(0, 200, 86, 0.7)',];
  colorsHovered = [
    'rgba(255, 99, 132, 0.85)',
    'rgba(54, 162, 235, 0.85)',
    'rgba(255, 206, 86, 0.85)',
    'rgba(75, 192, 192, 0.85)',
    'rgba(153, 102, 255, 0.85)',
    'rgba(255, 159, 64, 0.85)',
    'rgba(235, 0, 40, 0.85)',
    'rgba(0, 200, 86, 0.85)',];


  constructor(private cryptoService: CryptoService) {
  }

  ngOnInit() {
    let symbols = [];
    let values: number[] = [];

    this.cryptoService.getValues().subscribe(
      (coins: any[]) => {
        coins.forEach(function (coin) {
          symbols.push(coin.symbol);
          console.log(symbols);
          values.push(coin.value);
          console.log(values);
        });

        this.chart = new Chart('canvas', {
          type: 'doughnut',
          position: 'right',
          data: {
            labels: symbols,
            datasets: [
              {
                data: values,
                borderWidth : 3.5,
                borderColor: "#ffffff",
                backgroundColor: this.colorsDefault,
                hoverBackgrounColor : this.colorsHovered,
                fill: false,
              },
            ]
          },
          options: {
            responsive: true,
            cutoutPercentage: 35,
            animation : {
              animateRotate: true,
            },
            legend: {
              display: true,
              position: 'right',
              labels: {
                fontFamily: 'sans-serif',
                fontColor: 'rgb(30 , 100 , 200)',
                fontSize: 13,
                padding: 10,
              }
            },
            title: {
              text: 'Pennies in my pocket',
              position: 'bottom',
              fontSize: 15,
              display: true,
              padding: 30
            }
          }
        })
        ;
      },
      (error) => console.log(error));
  }

}
