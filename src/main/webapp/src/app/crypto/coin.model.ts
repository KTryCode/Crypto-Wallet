export class Coin{
  public symbol: string;
  public amount: number;
  public priceOnPurchaseDate: number;
  public price_usd: number;
  public value: number;


  constructor(symbol: string, amount: number, priceOnPurchaseDate: number, price_usd: number, value: number) {
    this.symbol = symbol;
    this.amount = amount;
    this.priceOnPurchaseDate = priceOnPurchaseDate;
    this.price_usd=price_usd;
    this.value=value;
  }
}
