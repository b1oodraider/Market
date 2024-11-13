package WebMarket.Market.DTO;

import WebMarket.Market.models.DBCartEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DBCartDTO extends DBCartEntity {

    public DBCartDTO(long userId, long productId, int productCount, String productsName) {
        super(userId, productId, productCount);
        this.productsName = productsName;
    }

    @Column(insertable=false, updatable=false)
    private String productsName;
}