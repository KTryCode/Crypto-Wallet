package pl.krystiano.crypto.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.web.bind.annotation.*;
import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.service.CoinPriceService;
import pl.krystiano.crypto.service.WalletService;

import java.util.logging.Logger;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
@RequestMapping("api/wallet")
public class WalletController {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WalletService walletService;
    @Autowired
    private CoinPriceService coinPriceService;

    @GetMapping(value = {"", "/"})
    public Iterable<Coin> listAll() {
        updateCoinValuesUSD();
        return this.walletService.listAll();
    }

    @GetMapping("/update_values")
    public Iterable<Coin> updateCoinValuesUSD() {
        return this.walletService.valueCalculator();
    }


    @PostMapping("/add")
    public Coin saveCoin(@RequestBody Coin coinToAdd) {
        System.out.println("Controller test");
        this.walletService.save(coinToAdd);
        updateCoinValuesUSD();
        return coinToAdd;
    }

    //TODO Implement
    @PostMapping("/remove")
    public Coin removeCoin(@RequestBody Coin coinToDelete) {
        logger.info("deleting");
        System.out.println("Deleting....");
        return coinToDelete;
    }

    @DeleteMapping("/remove/{symbol}")
    public void deleteCoin(@PathVariable String symbol) {
        System.out.println("Delete Coin . Controller" + symbol);
        logger.info("Delete Coin . Controller" + symbol);
        this.walletService.remove(symbol);
    }

}

