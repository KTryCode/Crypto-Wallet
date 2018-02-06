package pl.krystiano.crypto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@Table(name = "coins")
public class Coin {

    public Coin() {
    }

    public Coin(String symbol, double amount) {
        this.symbol = symbol;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String symbol;
    private double amount;
    private double value;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coin_data_id")
    private CoinData coinData;

    @JsonIgnore
    public double getPrice_usd(){
        return this.getCoinData().getPrice_usd();
    }

}

