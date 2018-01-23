package pl.krystiano.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.krystiano.crypto.domain.CoinData;
import pl.krystiano.crypto.service.CoinPriceService;


@RestController
@RequestMapping("api/coinbase")
public class PriceController {

    @Autowired
    private CoinPriceService coinPriceService;

    @GetMapping(value = {"/list"})
    public Iterable<CoinData> listAll() {
        return this.coinPriceService.listAll();
    }

    @RequestMapping("/update")
    public void updatePrices(){
        this.coinPriceService.updatePrices();
    }

}