package pl.krystiano.crypto.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.repository.CoinPriceRepository;
import pl.krystiano.crypto.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private CoinPriceRepository coinDataRepository;

    @Override
    public Iterable<Coin> listAll() {
        return this.walletRepository.findAll();
    }

    @Override
    public Coin save(Coin coin) {
        logger.info("Coin saved -> {}", coin);
        return this.walletRepository.save(coin);
    }

    @Override
    public Iterable<Coin> save(Iterable<Coin> coins) {
        logger.info("Some coins saved -> {}", coins);
        return this.walletRepository.save(coins);
    }

    @Override
    public void remove(Coin coin){
        logger.info("Coin deleted from wallet! -> {}", coin);
        this.walletRepository.delete(coin);
    }

    @Override
    public Iterable<Coin> valueCalculator(){
        Iterable<Coin> coinsInWallet = this.listAll();
        double valueOfCoin;
        for(Coin coin: coinsInWallet){
            valueOfCoin = coin.getAmount() * coin.getPrice_usd();
            coin.setValue(valueOfCoin);
        }
        return coinsInWallet;
    }
}
