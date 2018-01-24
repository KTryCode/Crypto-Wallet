export class Coin{
  public symbol: string;
  public amount: number;
  public priceOnPurchaseDate: number;
  public price_usd: number;


  constructor(symbol: string, amount: number, priceOnPurchaseDate: number, price_usd: number) {
    this.symbol = symbol;
    this.amount = amount;
    this.priceOnPurchaseDate = priceOnPurchaseDate;
    this.price_usd=price_usd;
  }
}
