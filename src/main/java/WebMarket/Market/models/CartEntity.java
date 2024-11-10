package WebMarket.Market.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {
    private long user_id;
    private GoodEntity good;
    private int good_count;
}
