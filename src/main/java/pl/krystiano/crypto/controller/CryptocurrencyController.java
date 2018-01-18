package pl.krystiano.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.krystiano.crypto.domain.Cryptocurrency;
import pl.krystiano.crypto.service.CryptocurrencyService;

@RestController
@RequestMapping("api/crypto")
public class CryptocurrencyController {

    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @GetMapping(value = {"", "/"})
    public Iterable<Cryptocurrency> listAll() {
        return this.cryptocurrencyService.listAll();
    }

    @PostMapping("/add")
    public Cryptocurrency saveCryptocurrency(@RequestBody Cryptocurrency cryptocurrency){
        return this.cryptocurrencyService.save(cryptocurrency);
    }

}
