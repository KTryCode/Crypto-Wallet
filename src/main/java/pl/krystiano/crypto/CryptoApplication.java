package pl.krystiano.crypto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.service.CoinPriceService;
import pl.krystiano.crypto.service.WalletService;

@SpringBootApplication
@EnableScheduling
public class CryptoApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WalletService walletService;
    @Autowired
    CoinPriceService coinPriceService;


    public static void main(String[] args) {
        SpringApplication.run(CryptoApplication.class, args);
    }


    @Override
    public void run(String... strings) {
        myFinances();

        this.coinPriceService.getCoinPricesAndParseToDatabase();
        this.walletService.getPricesFromDatabase();
        this.walletService.valueCalculator();
    }

    private void myFinances() {
        this.walletService.save(new Coin("BTC", 0.0098));
        this.walletService.save(new Coin("ETH", 0.4344));
        this.walletService.save(new Coin("BCH", 0.1322));
        this.walletService.save(new Coin("DASH", 0.203));
        this.walletService.save(new Coin("LSK", 7.45));
        this.walletService.save(new Coin("MIOTA", 17.9820));
        this.walletService.save(new Coin("XMR", 0.4196));
        this.walletService.save(new Coin("QTUM", 3.8262));
        this.walletService.save(new Coin("XRP", 99.9));
        this.walletService.save(new Coin("XLM", 199.9));
    }
}