package WebMarket.Market.controllers;

import WebMarket.Market.models.ProductEntity;
import WebMarket.Market.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stock")
public class  GoodsController {
    private final ProductService productService;

    public GoodsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String stock(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products/stock";
    }

    @GetMapping("/{id}")
    public String stock(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "products/productPage";
    }

    @GetMapping("/addNew")
    public String addNewP(@ModelAttribute("newProduct") ProductEntity product) {
        return "products/addNew";
    }

    @PostMapping("/addNew")
    public String addNew(@ModelAttribute("newProduct") ProductEntity product) {
        productService.save(product);
        return "redirect:/stock/addNew";
    }
}
