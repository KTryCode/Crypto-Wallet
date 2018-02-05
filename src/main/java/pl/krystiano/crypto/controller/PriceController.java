package pl.krystiano.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.krystiano.crypto.domain.CoinData;
import pl.krystiano.crypto.service.CoinPriceService;


@RestController
@RequestMapping("api/courses")
public class PriceController {

    @Autowired
    private CoinPriceService coinPriceService;

    @GetMapping(value = {"/list"})
    public Iterable<CoinData> listAll() {
        return this.coinPriceService.listAll();
    }

    @GetMapping(value ={"/update"})
    public Iterable<CoinData> updatePrices(){
        System.out.println("CoinPriceService.updatePrices() called");
        return this.coinPriceService.getCoinPricesAndParseToDatabase();
    }
}