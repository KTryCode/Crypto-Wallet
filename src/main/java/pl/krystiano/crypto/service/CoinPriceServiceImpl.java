package pl.krystiano.crypto.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
public class CoinPriceServiceImpl implements CoinPriceService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CoinPriceRepository coinPriceRepository;

    @Override
    @Transactional
    public Iterable<CoinData> listAll() {
        return this.coinPriceRepository.findAll();
    }

    @Override
    public Iterable<CoinData> getCoinPricesAndParseToDatabase() {

        ObjectMapper mapper = new ObjectMapper();
        List<CoinData> coinPriceData;

        try {
            coinPriceData = mapper.readValue(new URL("https://api.coinmarketcap.com/v1/ticker/"), new TypeReference<List<CoinData>>() {
            });
            return this.coinPriceRepository.save(coinPriceData);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
