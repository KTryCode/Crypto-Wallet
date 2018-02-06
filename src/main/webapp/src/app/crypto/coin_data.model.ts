export class CoinData{
  public id:string;
  public name: string;
  public symbol: string;
  public rank: number;
  public price_usd: number;
  public price_btc: number;
  public percent_change_1h: number;
  public percent_change_24h: number;
  public percent_change_7d: number;


  constructor(id: string, name: string, symbol: string, rank: number, price_usd: number, price_btc: number, percent_change_1h: number, percent_change_24h: number, percent_change_7d: number) {
    this.id = id;
    this.name = name;
    this.symbol = symbol;
    this.rank = rank;
    this.price_usd = price_usd;
    this.price_btc = price_btc;
    this.percent_change_1h = percent_change_1h;
    this.percent_change_24h = percent_change_24h;
    this.percent_change_7d = percent_change_7d;
  }
}
