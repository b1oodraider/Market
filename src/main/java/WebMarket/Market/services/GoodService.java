package WebMarket.Market.services;

import WebMarket.Market.models.Good;
import WebMarket.Market.repositories.GoodsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {
    private final GoodsRepository goodsRepository;

    public GoodService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public void save(Good good) {

        goodsRepository.save(good);
    }

    public List<Good> getAll() {
        return goodsRepository.findAll();
    }

    public Good getById(int id) {
        return goodsRepository.findById(id).orElse(null);
    }
}
