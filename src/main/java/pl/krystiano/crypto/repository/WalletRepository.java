package pl.krystiano.crypto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.krystiano.crypto.domain.Coin;

@Repository
public interface WalletRepository extends JpaRepository<Coin, String> {
}
