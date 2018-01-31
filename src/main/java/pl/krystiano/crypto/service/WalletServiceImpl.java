package pl.krystiano.crypto.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.domain.CoinData;
import pl.krystiano.crypto.repository.CoinPriceRepository;
import pl.krystiano.crypto.repository.WalletRepository;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
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
        Coin coinToUpdate = coin;
        if (isCoinAlreadyOwned(coin)) {
            coinToUpdate = this.walletRepository.findBySymbol(coin.getSymbol()).get(0);
            double totalAmountOfCoin = coinToUpdate.getAmount() + coin.getAmount();
            coinToUpdate.setAmount(totalAmountOfCoin);
            return this.updateCoinAmount(coinToUpdate);
        }
        logger.info("Coin saved -> {}", coinToUpdate);
        return this.walletRepository.save(coinToUpdate);
    }

    //TODO Fix to method save only when coin is not exist
    @Override
    public Iterable<Coin> save(Iterable<Coin> coins) {
        return this.walletRepository.save(coins);
    }

    //TODO implement method
    @Override
    public void remove(Coin coin) {
        logger.info("Coin deleted from wallet! -> {}", coin);
        this.walletRepository.delete(coin);
    }

    @Override
    public Iterable<Coin> valueCalculator() {
        logger.info("WalletService: valueCalculator()");
        Iterable<Coin> ownedCoins = this.listAll();
        ownedCoins.forEach(coin -> {
            double valueUsd = coin.getAmount() * coin.getPrice_usd();
            coin.setValue(valueUsd);
        });
        return this.walletRepository.save(ownedCoins);
    }

    //TODO Fix exception, when coin is not found
    @Override
    @Transactional
    public Iterable<Coin> getPricesFromDatabase() {
        Iterable<Coin> ownedCoins = this.listAll();

        for (Coin coinToUpdate : ownedCoins) {
            String coinSymbol = coinToUpdate.getSymbol();
            CoinData coinData = coinPriceRepository.findBySymbol(coinSymbol).get(0);
            double coinPrice = coinData.getPrice_usd();
            coinToUpdate.setPrice_usd(coinPrice);
        }

        valueCalculator();
        logger.info("getPricesFromDatabase(): Prices of coins downloaded from CoinBase and parsed to your wallet");
        return this.save(ownedCoins);
    }

    //TODO awful... get rid of it
    private boolean isCoinAlreadyOwned(Coin searchedCoin) {
        String searchedCoinSymbol = searchedCoin.getSymbol();
        Iterable<Coin> ownedCoins = this.listAll();
        Stream<Coin> streamOfOwnedCoins = StreamSupport.stream(ownedCoins.spliterator(), false);
        Stream<String> symbolsOfOwnedCoins = streamOfOwnedCoins.map(Coin::getSymbol);

        return symbolsOfOwnedCoins.anyMatch(searchedCoinSymbol::equals);
    }

    @Override
    public Coin updateCoinAmount(Coin coin) {
        String coinSymbol=coin.getSymbol();
        double coinAmount = coin.getAmount();
        this.walletRepository.updateCoinAmount(coinSymbol, coinAmount);
        return coin;
    }
}