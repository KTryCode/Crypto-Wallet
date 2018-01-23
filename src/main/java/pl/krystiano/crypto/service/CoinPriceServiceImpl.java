package pl.krystiano.crypto.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.domain.CoinData;
import pl.krystiano.crypto.repository.CoinPriceRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class CoinPriceServiceImpl implements CoinPriceService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CoinPriceRepository coinDataRepository;
    @Autowired
    private WalletService walletService;

    @Override
    public Iterable<CoinData> listAll() {
        return this.coinDataRepository.findAll();
    }

    @Override
    public void updatePrices(){
        getCoinPricesAndParseToDatabase();
        Iterable<Coin> coins = this.walletService.listAll();
        CoinData coinData;
        double coinPrice;

        for(Coin coinToUpdate: coins){
            String coinSymbol = coinToUpdate.getSymbol();
            coinData = coinDataRepository.findBySymbol(coinSymbol).get(0);
            coinPrice=coinData.getPrice_usd();
            coinToUpdate.setPrice_usd(coinPrice);
        }
        this.walletService.save(coins);
        logger.info("Prices of coins parsed to your wallet");
    }

    @Override
    public void getCoinPricesAndParseToDatabase() {

        ObjectMapper mapper = new ObjectMapper();
        List<CoinData> coinDataList;

        try {
            coinDataList = mapper.readValue(new URL("https://api.coinmarketcap.com/v1/ticker/"), new TypeReference<List<CoinData>>() {});
            this.coinDataRepository.save(coinDataList);
            logger.info("Prices of cryptocurrencies updated -> {}", LocalTime.now());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
