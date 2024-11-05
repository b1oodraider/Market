package WebMarket.Market.repositories;

import WebMarket.Market.models.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Good,Integer> {
}
