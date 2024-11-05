package WebMarket.Market.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="goods")
@Getter
@Setter
public class Good {
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

    public Good(String goods_name, String goods_type, Integer goods_in_stock, String goods_description) {
        this.goods_name = goods_name;
        this.goods_type = goods_type;
        this.goods_in_stock = goods_in_stock;
        this.goods_description = goods_description;
    }


    public Good() {}
}
