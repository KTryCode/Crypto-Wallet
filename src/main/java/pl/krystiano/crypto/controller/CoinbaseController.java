package pl.krystiano.crypto.controller;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.krystiano.crypto.domain.CoinData;
import pl.krystiano.crypto.repository.CoinDataRepository;
import pl.krystiano.crypto.service.CoinDataService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/coinbase")
public class CoinbaseController {

    @Autowired
    private CoinDataService coinDataService;

    @GetMapping(value = {"", "/"})
    public Iterable<CoinData> listAll() {
        return this.coinDataService.listAll();
    }

}
