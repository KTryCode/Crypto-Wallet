package pl.krystiano.crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.service.CoinService;

@SpringBootApplication
@EnableScheduling
public class CryptoApplication implements CommandLineRunner {

    @Autowired
    CoinService coinService;

    public static void main(String[] args) {
        SpringApplication.run(CryptoApplication.class, args);
    }


    @Override
    public void run(String... strings) {
        this.coinService.save(new Coin("BTC",  0.5, 10000));
        this.coinService.save(new Coin( "ETH",  1, 2000));
        this.coinService.save(new Coin("BCH",  6, 1200));
        this.coinService.save(new Coin("XRP",  450, 45 ));
        this.coinService.save(new Coin("LTC",  1100,  50));
    }
}