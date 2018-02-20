package pl.krystiano.crypto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = "coin")
@Table(name = "coinData")
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinData {

    @Id
    private String id;
    private String name;
    private String symbol;
    private int rank;
    private double price_usd;
    private double price_btc;
    private double percent_change_1h;
    private double percent_change_24h;
    private double percent_change_7d;

    @JsonIgnore
    @OneToOne(mappedBy = "coinData")
    private Coin coin;

}
