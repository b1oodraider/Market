package WebMarket.Market.services;

import WebMarket.Market.DTO.DBCartDTO;
import WebMarket.Market.models.DBCartEntity;
import WebMarket.Market.repositories.CartRepository;
import WebMarket.Market.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public List<DBCartDTO> getCart(int user_id) {
        return cartRepository.findByUserId(user_id);
    }

    @Transactional
    public void save(long user_id, long product_id) {
        DBCartEntity cart = new DBCartEntity(user_id, product_id,1);
        cartRepository.save(cart);
    }

    @Transactional
    public void changeCount(int user_id, int product_id, int newCount) {
        cartRepository.save(new DBCartEntity(user_id, product_id, newCount));
    }

    @Transactional
    public void clearCart(int user_id) {
        cartRepository.deleteAllByUserId(user_id);
    }

    @Transactional
    public void deleteOne(int user_id, int product_id) {
        cartRepository.deleteAllByUserIdAndProductId(user_id, product_id);
    }

}
