package WebMarket.Market.repositories;

import WebMarket.Market.DTO.DBCartDTO;
import WebMarket.Market.models.DBCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<DBCartEntity, Integer> {
    @Query(
            value="select carts.*, products.products_name from carts join products on carts.product_id=products.product_id",
            nativeQuery=true
    )
    List<DBCartDTO> findByUserId(int id);

    void deleteAllByUserIdAndProductId(long id, long productId);

    void deleteAllByUserId(long id);
}
