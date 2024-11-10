package WebMarket.Market.repositories;

import WebMarket.Market.models.GoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<GoodEntity,Integer> {
}
