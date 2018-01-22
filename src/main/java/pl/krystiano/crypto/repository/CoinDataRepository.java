package pl.krystiano.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.krystiano.crypto.domain.CoinData;

@Repository
public interface CoinDataRepository extends JpaRepository<CoinData, String> {
}
