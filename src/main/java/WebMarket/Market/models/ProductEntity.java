package WebMarket.Market.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int id;
    @Column(name="products_name")
    private String products_name;
    @Column(name="products_type")
    private String products_type;
    @Column(name="products_in_stock")
    private Integer products_in_stock;
    @Column(name="products_description")
    private String products_description;
}
