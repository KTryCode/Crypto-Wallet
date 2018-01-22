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
import pl.krystiano.crypto.domain.CoinData;
import pl.krystiano.crypto.repository.CoinDataRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CoinDataServiceImpl implements CoinDataService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CoinDataRepository coinDataRepository;

    @Override
    public Iterable<CoinData> listAll() {
        return this.coinDataRepository.findAll();
    }


    @Override
    @Scheduled(fixedRate = 5000)
    public void parseCoinDataToDatabase() {
        ObjectMapper mapper = new ObjectMapper();
        List<CoinData> coinDataList;
        try {
            coinDataList = mapper.readValue(new URL("https://api.coinmarketcap.com/v1/ticker/"), new TypeReference<List<CoinData>>() {});
            this.coinDataRepository.save(coinDataList);
            logger.info("Crypto data updated -> {}", LocalTime.now());

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
