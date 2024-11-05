package WebMarket.Market.controllers;

import WebMarket.Market.models.Good;
import WebMarket.Market.services.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stock")
public class GoodsController {
    private final GoodService goodService;

    public GoodsController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping("")
    public String stock(Model model) {
        model.addAttribute("goods", goodService.getAll());
        return "goods/stock";
    }

    @GetMapping("/{id}")
    public String stock(@PathVariable int id, Model model) {
        model.addAttribute("good", goodService.getById(id));
        return "goods/goodPage";
    }

    @GetMapping("/addNew")
    public String addNewP(@ModelAttribute("newGood") Good good) {
        return "goods/addNew";
    }

    @PostMapping("/addNew")
    public String addNew(@ModelAttribute("newGood") Good good) {
        goodService.save(good);
        return "redirect:/stock";
    }
}
