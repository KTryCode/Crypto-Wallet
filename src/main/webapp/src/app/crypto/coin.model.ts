import {CoinData} from "./coin_data.model";

export class Coin {
  public symbol: string;
  public amount: number;
  public value?: number;
  public coinData?: CoinData;


  constructor(symbol: string, amount: number, value: number, coin_data: CoinData) {
    this.symbol = symbol;
    this.amount = amount;
    this.value = value;
    this.coinData = coin_data;
  }
}
