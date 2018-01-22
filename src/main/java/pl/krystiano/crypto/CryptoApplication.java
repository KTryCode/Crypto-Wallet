package pl.krystiano.crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.krystiano.crypto.domain.Cryptocurrency;
import pl.krystiano.crypto.service.CoinDataService;
import pl.krystiano.crypto.service.CryptocurrencyService;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class CryptoApplication implements CommandLineRunner {

    @Autowired
    CryptocurrencyService cryptocurrencyService;

    public static void main(String[] args) {
        SpringApplication.run(CryptoApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {
        this.cryptocurrencyService.save(new Cryptocurrency("Bitcoin", "BTC",  0.5, LocalDate.now(), 10000));
        this.cryptocurrencyService.save(new Cryptocurrency("Ethereum", "ETH",  1, LocalDate.now(), 2000));
        this.cryptocurrencyService.save(new Cryptocurrency("Bitcoin Cash", "BCH",  6, LocalDate.now(), 1200));
        this.cryptocurrencyService.save(new Cryptocurrency("Ripple", "XRP",  450, LocalDate.now(), 45 ));
        this.cryptocurrencyService.save(new Cryptocurrency("Litecoin", "LTC",  1100, LocalDate.now(), 50));
    }
}