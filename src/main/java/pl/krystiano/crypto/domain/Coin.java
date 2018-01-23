package pl.krystiano.crypto.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "my_wallet")
public class Coin {

    public Coin() {
    }

    public Coin(String symbol, double amount, double courseOnPurchaseDate) {
        this.symbol = symbol;
        this.amount = amount;
        this.priceOnPurchaseDate = courseOnPurchaseDate;
    }

    @Id
    private String symbol;
    private double amount;
    private double priceOnPurchaseDate;
    private double price_usd;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPriceOnPurchaseDate() {
        return priceOnPurchaseDate;
    }

    public void setPriceOnPurchaseDate(double priceOnPurchaseDate) {
        this.priceOnPurchaseDate = priceOnPurchaseDate;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(double price_usd) {
        this.price_usd = price_usd;
    }
}

