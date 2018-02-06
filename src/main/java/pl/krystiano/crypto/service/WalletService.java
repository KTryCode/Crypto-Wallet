package pl.krystiano.crypto.service;

import pl.krystiano.crypto.domain.Coin;

public interface WalletService {

    Iterable<Coin> listAll();

    Coin save(Coin coin);
    void remove(Coin coin);

    Iterable<Coin> save(Iterable<Coin> coins);

    Coin updateCoinAmount(Coin coin);

    Iterable<Coin> valueCalculator();

    Coin valueCalculator(Coin coin);

    void assignDataToMyWallet();

    Coin assignDataToCoin(Coin coin);


}
