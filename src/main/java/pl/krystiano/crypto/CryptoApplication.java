package pl.krystiano.crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.krystiano.crypto.domain.Cryptocurrency;
import pl.krystiano.crypto.service.CryptocurrencyService;

import java.time.LocalDate;

@SpringBootApplication
public class CryptoApplication implements CommandLineRunner {

    @Autowired
    CryptocurrencyService cryptocurrencyService;

    public static void main(String[] args) {
        SpringApplication.run(CryptoApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {
        this.cryptocurrencyService.save(new Cryptocurrency("aa", "A", (double) 1.1, LocalDate.now(), (double) 1000.2));


    }
}