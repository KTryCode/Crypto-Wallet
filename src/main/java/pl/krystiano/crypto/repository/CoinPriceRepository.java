package pl.krystiano.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.krystiano.crypto.domain.Coin;
import pl.krystiano.crypto.domain.CoinData;

import java.util.List;

@Repository
public interface CoinPriceRepository extends JpaRepository<CoinData, String> {
    List<CoinData> findBySymbol(String symbol);
}
