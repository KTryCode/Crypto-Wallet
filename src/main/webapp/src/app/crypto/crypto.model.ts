export class Cryptocurrency{
  public id: number;
  public name: string;
  public shortname: string;
  public amount: number;
  public dateOfPurchase: string;
  public courseOnPurchaseDate: number;


  constructor(name: string, shortname: string, amount: number, dateOfPurchase: string, courseOnPurchaseDate: number) {
    this.name = name;
    this.shortname = shortname;
    this.amount = amount;
    this.dateOfPurchase = dateOfPurchase;
    this.courseOnPurchaseDate = courseOnPurchaseDate;
  }
}
