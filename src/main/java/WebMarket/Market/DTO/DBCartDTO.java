package WebMarket.Market.DTO;

import WebMarket.Market.models.GoodEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="carts")
public class DBCartDTO {

    public DBCartDTO(int userId, GoodEntity good, int goodCount) {
        this.userId = userId;
        this.goodCount = goodCount;

        this.id = good.getId();
        this.goods_name=good.getGoods_name();
        this.goods_description=good.getGoods_description();
        this.goods_type=good.getGoods_type();
        this.goods_in_stock=good.getGoods_in_stock();
    }

    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name="good_id")
    private int goodId;
    @Column(name="good_count")
    private int goodCount;

    private int id;
    private String goods_name;
    private String goods_type;
    private int goods_in_stock;
    private String goods_description;
}
