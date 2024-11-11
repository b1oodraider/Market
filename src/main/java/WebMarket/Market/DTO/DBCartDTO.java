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

    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name="good_id")
    private int goodId;
    @Column(name="good_count")
    private int goodCount;
}
