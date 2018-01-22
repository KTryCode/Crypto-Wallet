package pl.krystiano.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.krystiano.crypto.domain.CoinData;
import pl.krystiano.crypto.service.CoinDataService;


@RestController
@RequestMapping("api/coinbase")
public class CoinbaseController {

    @Autowired
    private CoinDataService coinDataService;

    @GetMapping(value = {"", "/"})
    public Iterable<CoinData> listAll() {
        return this.coinDataService.listAll();
    }

}
