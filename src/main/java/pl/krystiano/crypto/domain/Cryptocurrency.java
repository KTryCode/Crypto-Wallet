package pl.krystiano.crypto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
public class Cryptocurrency {

    public Cryptocurrency() {
    }

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
    @NotNull
    private String name;
    @NotNull
    private String shortname;
    @NotNull
    private double amount;
    @NotNull
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOfPurchase;
    @NotNull
    private double courseOnPurchaseDate;

}

