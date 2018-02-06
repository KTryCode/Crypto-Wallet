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
        updateCoinValuesUSD();
        return this.walletService.listAll();
    }

    @GetMapping("/update_values")
    public Iterable<Coin> updateCoinValuesUSD() {
        return this.walletService.valueCalculator();
    }


    @PostMapping("/add")
    public Coin saveCoin(@RequestBody Coin coinToAdd) {
        this.walletService.save(coinToAdd);
        updateCoinValuesUSD();
        return coinToAdd;
    }

    //TODO Implement
    @RequestMapping("/remove")
    public Coin removeCoin(@RequestBody Coin coinToDelete){
        return coinToDelete;
    }



}

