package WebMarket.Market.repositories;

import WebMarket.Market.DTO.DBCartDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<DBCartDTO, Integer> {
    List<DBCartDTO> findByUserId(long id);
    boolean existsByUserIdAndGoodId(long id, long goodId);
    DBCartDTO findByUserIdAndGoodId(long id, long goodId);
}
