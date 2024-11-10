package WebMarket.Market.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="goods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="goods_name")
    private String goods_name;
    @Column(name="goods_type")
    private String goods_type;
    @Column(name="goods_in_stock")
    private Integer goods_in_stock;
    @Column(name="goods_description")
    private String goods_description;
}
