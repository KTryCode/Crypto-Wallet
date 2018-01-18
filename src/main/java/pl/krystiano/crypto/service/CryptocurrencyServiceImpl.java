package pl.krystiano.crypto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krystiano.crypto.domain.Cryptocurrency;
import pl.krystiano.crypto.repository.CryptocurrencyRepository;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    @Autowired
    private CryptocurrencyRepository cryptocurrencyRepository;

    @Override
    public Iterable<Cryptocurrency> listAll() {
        return this.cryptocurrencyRepository.findAll();
    }

    @Override
    public Cryptocurrency save(Cryptocurrency cryptocurrency) {
        return this.cryptocurrencyRepository.save(cryptocurrency);
    }
}
