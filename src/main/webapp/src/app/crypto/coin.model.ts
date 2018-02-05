export class Coin{
  public symbol: string;
  public amount: number;
  public price_usd: number;
  public value: number;


  constructor(symbol: string, amount: number, price_usd: number, value: number) {
    this.symbol = symbol;
    this.amount = amount;
    this.price_usd=price_usd;
    this.value=value;
  }
}
