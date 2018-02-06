package pl.krystiano.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.service.CoinPriceService;
import pl.krystiano.crypto.service.WalletService;

@RestController
@RequestMapping("api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;
    @Autowired
    private CoinPriceService coinPriceService;

    @GetMapping(value = {"", "/"})
    public Iterable<Coin> listAll() {
        return this.walletService.listAll();
    }

    @GetMapping("/update_values")
    public Iterable<Coin> updateCoinValuesUSD() {
        getPriceUsdFromDatabase();
        return this.walletService.valueCalculator();
    }


    @PostMapping("/add")
    public Coin saveCoin(@RequestBody Coin coinToAdd) {
        return this.walletService.save(coinToAdd);
    }

    @GetMapping("/update_prices")
    public Iterable<Coin> getPriceUsdFromDatabase() {
        return this.walletService.getPricesFromDatabase();
    }
}

