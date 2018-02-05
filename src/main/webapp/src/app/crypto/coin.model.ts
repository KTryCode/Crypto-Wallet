import {CoinData} from "./coin_data.model";

export class Coin{
  public symbol: string;
  public amount: number;
  public price_usd: number;
  public value: number;
  public coin_data: CoinData;


  constructor(symbol: string, amount: number, price_usd: number, value: number, coin_data: CoinData) {
    this.symbol = symbol;
    this.amount = amount;
    this.price_usd=price_usd;
    this.value=value;
    this.coin_data=coin_data;
  }

}
