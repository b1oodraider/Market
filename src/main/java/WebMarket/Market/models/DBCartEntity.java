package WebMarket.Market.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.boot.spi.AccessType;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="carts")
@IdClass(MyKey.class)
public class DBCartEntity {

    @Id
    @Column(name = "user_id")
    private long userId;
    @Id
    @Column(name="product_id")
    private long productId;
    @Column(name="product_count")
    private int productCount;
}

class MyKey implements Serializable {
    private long userId;
    private long productId;
}
