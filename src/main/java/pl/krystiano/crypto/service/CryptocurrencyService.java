package pl.krystiano.crypto.service;

import pl.krystiano.crypto.domain.Cryptocurrency;

public interface CryptocurrencyService {

    Iterable<Cryptocurrency> listAll();

    Cryptocurrency save(Cryptocurrency cryptocurrency);

}
