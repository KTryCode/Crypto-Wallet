package pl.krystiano.crypto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.krystiano.crypto.domain.Coin;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Coin, String> {
    List<Coin> findBySymbol(String symbol);

    @Modifying
    @Query("UPDATE Coin c SET c.amount = :amount where c.symbol= :symbol")
    int updateCoinAmount(@Param("symbol") String coinSymbol, @Param("amount") double amount);

}
