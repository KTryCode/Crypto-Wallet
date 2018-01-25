package pl.krystiano.crypto.service;

import pl.krystiano.crypto.domain.Coin;

public interface WalletService {

    Iterable<Coin> listAll();

    Coin save(Coin coin);
    void remove(Coin coin);

    Iterable<Coin> save(Iterable<Coin> coins);

    Iterable<Coin> valueCalculator();

    Iterable<Coin> getPricesFromDatabase();
}
