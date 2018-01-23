package pl.krystiano.crypto.service;

import pl.krystiano.crypto.domain.Coin;

public interface CoinService {

    Iterable<Coin> listAll();

    Coin save(Coin cryptocurrency);

}
