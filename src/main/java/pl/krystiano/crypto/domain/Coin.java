package pl.krystiano.crypto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "my_wallet")
public class Coin {

    public Coin(String symbol, double amount, double courseOnPurchaseDate) {
        this.symbol = symbol;
        this.amount = amount;
        this.courseOnPurchaseDate = courseOnPurchaseDate;
    }

    @Id
    private String symbol;
    private double amount;
    private double courseOnPurchaseDate;
}

