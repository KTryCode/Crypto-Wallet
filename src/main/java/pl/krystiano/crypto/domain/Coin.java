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

    public Coin(String symbol, double amount) {
        this.symbol = symbol;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private long id;
    private String symbol;
    private double amount;
    private double price_usd;
    private double value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

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

    public double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(double price_usd) {
        this.price_usd = price_usd;
    }
}

