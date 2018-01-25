package pl.krystiano.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.service.WalletService;

@RestController
@RequestMapping("api/crypto")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping(value = {"", "/"})
    public Iterable<Coin> listAll() {
        return this.walletService.listAll();
    }

    @GetMapping("/update/coin_values")
    public Iterable<Coin> updateCoinValues() {
        return this.walletService.valueCalculator();
    }

    @PostMapping("/add")
    public Coin saveCryptocurrency(@RequestBody Coin cryptocurrency) {
        return this.walletService.save(cryptocurrency);
    }


}
