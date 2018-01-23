package pl.krystiano.crypto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.repository.CoinRepository;

@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    private CoinRepository coinRepository;

    @Override
    public Iterable<Coin> listAll() {
        return this.coinRepository.findAll();
    }

    @Override
    public Coin save(Coin cryptocurrency) {
        return this.coinRepository.save(cryptocurrency);
    }
}
