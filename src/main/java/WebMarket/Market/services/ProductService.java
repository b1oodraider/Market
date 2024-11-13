package WebMarket.Market.services;

import WebMarket.Market.models.ProductEntity;
import WebMarket.Market.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(ProductEntity good) {

        productRepository.save(good);
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public ProductEntity getById(int id) {

        return productRepository.findById(id).orElse(null);
    }
}
