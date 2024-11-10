package WebMarket.Market.repositories;

import WebMarket.Market.DTO.DBCartDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<DBCartDTO, Integer> {
    @Query(value="select * from carts join goods on carts.good_id = goods.id where carts.user_id =?1",
            nativeQuery = true)
    List<DBCartDTO> findByUserId(long id);
    boolean existsByUserIdAndGoodId(long id, long goodId);
    DBCartDTO findByUserIdAndGoodId(long id, long goodId);
}
