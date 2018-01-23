package pl.krystiano.crypto.service;

import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.domain.CoinData;

public interface CoinPriceService {

    Iterable<CoinData> listAll();

    void updatePrices();

    void getCoinPricesAndParseToDatabase();
}
