package pl.krystiano.crypto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@RequiredArgsConstructor
public class Cryptocurrency {


    public Cryptocurrency(String name, String shortname, double amount, LocalDate dateOfPurchase, double courseOnPurchaseDate) {
        this.name = name;
        this.shortname = shortname;
        this.amount = amount;
        this.dateOfPurchase = dateOfPurchase;
        this.courseOnPurchaseDate = courseOnPurchaseDate;
    }

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String shortname;
    private double amount;
    private double courseOnPurchaseDate;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOfPurchase;

}

