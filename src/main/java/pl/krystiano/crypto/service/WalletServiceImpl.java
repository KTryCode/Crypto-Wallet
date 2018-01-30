package pl.krystiano.crypto.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.domain.CoinData;
import pl.krystiano.crypto.repository.CoinPriceRepository;
import pl.krystiano.crypto.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private CoinPriceRepository coinPriceRepository;

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
    public void remove(Coin coin) {
        logger.info("Coin deleted from wallet! -> {}", coin);
        this.walletRepository.delete(coin);
    }

    @Override
    public Iterable<Coin> valueCalculator() {
        Iterable<Coin> coinsInWallet = this.listAll();
        for (Coin coin : coinsInWallet) {
            double valueOfCoin = coin.getAmount() * coin.getPrice_usd();
            coin.setValue(valueOfCoin);
            logger.info("value of {} is {}", coin.getSymbol(), coin.getValue());
        }
        return this.walletRepository.save(coinsInWallet);
    }

    @Override
    @Transactional
    public Iterable<Coin> getPricesFromDatabase() {
        Iterable<Coin> coinsInMyWallet = this.listAll();

        for (Coin coinToUpdate : coinsInMyWallet) {
            String coinSymbol = coinToUpdate.getSymbol();
            CoinData coinData = coinPriceRepository.findBySymbol(coinSymbol).get(0);
            double coinPrice = coinData.getPrice_usd();
            coinToUpdate.setPrice_usd(coinPrice);
        }

        valueCalculator();
        logger.info("getPricesFromDatabase(): Prices of coins parsed to your wallet");
        return this.save(coinsInMyWallet);
    }
}