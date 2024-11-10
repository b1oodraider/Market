package WebMarket.Market.models;

import WebMarket.Market.repositories.GoodsRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@SessionScope
@Component
public class LocalCart {
    private Map<String, CartNode> cart = new HashMap<>();

    private final GoodsRepository goodsRepository;

    public LocalCart(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public void add(GoodEntity good, int count) {
        int temp = count;
        if(cart.containsKey(good.getGoods_name())) {
            temp += cart.get(good.getGoods_name()).getCount();
        }
        cart.put(good.getGoods_name(), new CartNode(good, temp));
    }

    public void changeCount(int id, int countDelta) {
        GoodEntity good = goodsRepository.findById(id).get();
        cart.put(good.getGoods_name(), new CartNode(good, countDelta));
    }

    public void removeOne(int id) {
        cart.remove(goodsRepository.findById(id).get().getGoods_name());
    }

    public void removeAll() {
        cart.clear();
    }
}

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class CartNode {
    private GoodEntity good;
    private int count;
}
