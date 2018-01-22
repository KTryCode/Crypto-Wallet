package pl.krystiano.crypto.service;

import pl.krystiano.crypto.domain.CoinData;

public interface CoinDataService {

    Iterable<CoinData> listAll();

    void parseCoinDataToDatabase();
}
