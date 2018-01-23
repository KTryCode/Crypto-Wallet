package pl.krystiano.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.service.CoinService;

@RestController
@RequestMapping("api/crypto")
public class CryptocurrencyController {

    @Autowired
    private CoinService cryptocurrencyService;

    @GetMapping(value = {"", "/"})
    public Iterable<Coin> listAll() {
        return this.cryptocurrencyService.listAll();
    }

    @PostMapping("/add")
    public Coin saveCryptocurrency(@RequestBody Coin cryptocurrency){
        return this.cryptocurrencyService.save(cryptocurrency);
    }

}
