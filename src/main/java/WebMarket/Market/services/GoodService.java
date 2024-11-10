package WebMarket.Market.services;

import WebMarket.Market.models.GoodEntity;
import WebMarket.Market.repositories.GoodsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {
    private final GoodsRepository goodsRepository;

    public GoodService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public void save(GoodEntity good) {

        goodsRepository.save(good);
    }

    public List<GoodEntity> getAll() {
        return goodsRepository.findAll();
    }

    public GoodEntity getById(int id) {

        return goodsRepository.findById(id).orElse(null);
    }
}
