package pl.krystiano.crypto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.krystiano.crypto.domain.Coin;

@Repository
public interface CoinRepository extends JpaRepository<Coin, String> {
}
