package WebMarket.Market.services;

import WebMarket.Market.DTO.DBCartDTO;
import WebMarket.Market.models.CartEntity;
import WebMarket.Market.models.GoodEntity;
import WebMarket.Market.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService( CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartEntity> getCart(long user_id) {
        List<DBCartDTO> list = cartRepository.findByUserId(user_id);
        List<CartEntity> carts = new ArrayList<>();
        for (DBCartDTO dto : list) {
            carts.add(new CartEntity(dto.getUserId(), new GoodEntity(dto.getId(),dto.getGoods_name(), dto.getGoods_type(), dto.getGoods_in_stock(), dto.getGoods_description()), dto.getGoodCount()));
        }
        return carts;
    }

    public void save(int user_id, GoodEntity good, int good_count) {
        DBCartDTO cart = new DBCartDTO(user_id, good, good_count);
        if(cartRepository.existsByUserIdAndGoodId(user_id, good.getId())) {
            cart.setGoodCount(cart.getGoodCount() + cartRepository.findByUserIdAndGoodId(user_id, good.getId()).getGoodCount());
        }
        cartRepository.save(cart);
    }
}
