package pl.krystiano.crypto.service;

import pl.krystiano.crypto.domain.Coin;

public interface WalletService {

    Iterable<Coin> listAll();

    Coin save(Coin coin);

    Iterable<Coin> save(Iterable<Coin> coins);

    void remove(String symbol);

    Coin updateCoinAmount(Coin coin);

    Iterable<Coin> valueCalculator();

    Coin valueCalculator(Coin coin);

    void assignDataToMyWallet();

    Coin assignDataToCoin(Coin coin);

    Coin findCoinBySymbol(String coinSymbol);


}
