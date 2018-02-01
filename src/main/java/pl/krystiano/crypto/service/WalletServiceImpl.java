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

import java.util.List;
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
    @Autowired
    private CoinPriceService coinPriceService;

    @Override
    public Iterable<Coin> listAll() {
        return this.walletRepository.findAll();
    }


    @Override
    public Coin save(Coin coin) {
        Coin coinToSave = coin;
        if (ifCoinExists(coinToSave)) {
            if (isCoinAlreadyOwned(coin)) {

                coinToSave = this.walletRepository.findBySymbol(coin.getSymbol()).get(0);
                double totalAmountOfCoin = coinToSave.getAmount() + coin.getAmount();
                coinToSave.setAmount(totalAmountOfCoin);
                return this.updateCoinAmount(coinToSave);
            } else {
                logger.info("Coin saved -> {}", coinToSave);
                return this.walletRepository.save(coinToSave);
            }
        } else {
            logger.info("WalletService.save(): Coin '{}' does not exist in DB. Saving not completed", coin.getSymbol());
            return coin;
        }
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
            List<CoinData> coinData = coinPriceRepository.findBySymbol(coinSymbol);
            if (!coinData.isEmpty()) {
                logger.info("znaleziono kryptowalute w bazie -> {}", coinSymbol);
                double coinPrice = coinData.get(0).getPrice_usd();
                coinToUpdate.setPrice_usd(coinPrice);
            } else {
                logger.info("No Coin '{}' in database", coinSymbol);
            }
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
        boolean isCoinOwned = symbolsOfOwnedCoins.anyMatch(searchedCoinSymbol::equals);
        if(isCoinOwned) logger.info("Coin is already owned.");
        return isCoinOwned;
    }

    @Override
    public Coin updateCoinAmount(Coin coin) {
        String coinSymbol = coin.getSymbol();
        double coinAmount = coin.getAmount();
        this.walletRepository.updateCoinAmount(coinSymbol, coinAmount);
        return coin;
    }

    private boolean ifCoinExists(Coin searchedCoin) {
        String searchedCoinSymbol = searchedCoin.getSymbol();
        Iterable<CoinData> coinsInDatabase = this.coinPriceService.listAll();
        Stream<CoinData> streamOfCoinsInDatabase = StreamSupport.stream(coinsInDatabase.spliterator(), false);
        Stream<String> symbolsOfCoinsInDatabase = streamOfCoinsInDatabase.map(CoinData::getSymbol);
        boolean isCoinExist = symbolsOfCoinsInDatabase.anyMatch(searchedCoinSymbol::equals);
        if(isCoinExist) logger.info("WalletService.ifCoinExists. Coin {} exist.");
        return isCoinExist;
    }
}