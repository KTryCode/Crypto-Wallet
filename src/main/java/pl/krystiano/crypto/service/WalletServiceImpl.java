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
    @Autowired
    private CoinPriceService coinPriceService;

    @Override
    public Iterable<Coin> listAll() {
        return this.walletRepository.findAll();
    }


    @Override
    public Coin save(Coin coinToSave) {
        if (ifCoinExists(coinToSave)) {
            if (isCoinAlreadyOwned(coinToSave)) {
                Coin coinInWallet = this.walletRepository.findBySymbol(coinToSave.getSymbol()).get(0);
                double totalAmountOfCoin = coinToSave.getAmount() + coinInWallet.getAmount();
                coinInWallet.setAmount(totalAmountOfCoin);
                return this.updateCoinAmount(coinInWallet);
            } else {
                coinToSave = this.assignDataToCoin(coinToSave);
                return this.walletRepository.save(coinToSave);
            }
        } else {
            logger.info("WalletService.save(): Coin '{}' does not exist in DB. Saving not completed", coinToSave.getSymbol());
            return coinToSave;
        }
    }

    @Override
    public Iterable<Coin> save(Iterable<Coin> coins) {
        return this.walletRepository.save(coins);
    }

    //TODO implement method
    @Override
    public void remove(String symbol) {
        logger.info("Coin deleted from wallet! -> {}", symbol);
        this.walletRepository.removeBySymbol(symbol);
    }

    @Override
    public Iterable<Coin> valueCalculator() {
        logger.info("WalletService: valueCalculator()");
        Iterable<Coin> ownedCoins = this.listAll();
        ownedCoins.forEach(this::valueCalculator);
        return this.walletRepository.save(ownedCoins);
    }

    @Override
    public Coin valueCalculator(Coin coin) {
        double valueUSD = coin.getAmount() * coin.getPrice_usd();
        coin.setValue(valueUSD);
        return coin;
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
        if (isCoinExist) logger.info("WalletService.ifCoinExists. Coin {} exist.", searchedCoin.getSymbol());
        return isCoinExist;
    }

    //TODO awful... get rid of it
    private boolean isCoinAlreadyOwned(Coin searchedCoin) {
        String searchedCoinSymbol = searchedCoin.getSymbol();
        Iterable<Coin> ownedCoins = this.listAll();
        Stream<Coin> streamOfOwnedCoins = StreamSupport.stream(ownedCoins.spliterator(), false);
        Stream<String> symbolsOfOwnedCoins = streamOfOwnedCoins.map(Coin::getSymbol);
        boolean isCoinOwned = symbolsOfOwnedCoins.anyMatch(searchedCoinSymbol::equals);
        if (isCoinOwned) logger.info("Coin is already owned.");
        return isCoinOwned;
    }

    @Override
    public void assignDataToMyWallet() {
        Iterable<Coin> myCoins = this.walletRepository.findAll();
        for (Coin coin : myCoins) {
            assignDataToCoin(coin);
        }
    }

    @Override
    public Coin assignDataToCoin(Coin coin) {
        String symbolOfCoin = coin.getSymbol();
        CoinData coinData = this.coinPriceRepository.findBySymbol(symbolOfCoin).get(0);
        coin.setCoinData(coinData);
        return coin;
    }
}