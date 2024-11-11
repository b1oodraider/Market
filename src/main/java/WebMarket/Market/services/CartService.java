package WebMarket.Market.services;

import WebMarket.Market.DTO.DBCartDTO;
import WebMarket.Market.models.CartEntity;
import WebMarket.Market.models.GoodEntity;
import WebMarket.Market.repositories.CartRepository;
import WebMarket.Market.repositories.GoodsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final GoodsRepository goodsRepository;

    public CartService(CartRepository cartRepository, GoodsRepository goodsRepository) {
        this.cartRepository = cartRepository;
        this.goodsRepository = goodsRepository;
    }

    @Transactional
    public List<CartEntity> getCart(long user_id) {
        List<DBCartDTO> list = cartRepository.findByUserId(user_id);
        List<CartEntity> carts = new ArrayList<>();
        for (DBCartDTO dto : list) {
            carts.add(new CartEntity(
                    dto.getUserId(),
                    goodsRepository.findById(dto.getGoodId()).get(),
                    dto.getGoodCount())
            );

        }
        return carts;
    }

    @Transactional
    public void save(int user_id, int good_id, int good_count) {
        DBCartDTO cart = new DBCartDTO(user_id, good_id, good_count);
        if(cartRepository.existsByUserIdAndGoodId(user_id, good_id)) {
            cart.setGoodCount(
                    cart.getGoodCount() + cartRepository.findByUserIdAndGoodId(user_id, good_id).getGoodCount());
        }
        cartRepository.save(cart);
    }

    @Transactional
    public void changeCount(int user_id, int good_id, int newCount) {
        cartRepository.save(new DBCartDTO(user_id, good_id, newCount));
    }

}
