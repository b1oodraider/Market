package WebMarket.Market.controllers;

import WebMarket.Market.models.LocalCart;
import WebMarket.Market.models.GoodEntity;
import WebMarket.Market.security.UsersDetails;
import WebMarket.Market.services.CartService;
import WebMarket.Market.services.GoodService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stock")
public class  GoodsController {
    private final LocalCart myLocalCart;
    private final GoodService goodService;
    private final CartService cartService;

    public GoodsController(LocalCart myLocalCart, GoodService goodService, CartService cartService) {
        this.myLocalCart = myLocalCart;
        this.goodService = goodService;
        this.cartService = cartService;
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
    public String addNewP(@ModelAttribute("newGood") GoodEntity good) {
        return "goods/addNew";
    }

    @PostMapping("/addNew")
    public String addNew(@ModelAttribute("newGood") GoodEntity good) {
        goodService.save(good);
        return "redirect:/stock";
    }

    @PostMapping("/{id}")
    public String addToCart(@ModelAttribute("good") GoodEntity good) {
        GoodEntity newGood = goodService.getById(good.getId());
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            UsersDetails usersDetails = (UsersDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            cartService.save(usersDetails.getUser().getId(), newGood, 1);
        }
        myLocalCart.add(newGood, 1);
        return "redirect:/stock";
    }

    @GetMapping("/cart")
    public String cart( Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            UsersDetails usersDetails = (UsersDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("myDBCart", cartService.getCart(usersDetails.getUser().getId()));
        } else {
            model.addAttribute("myLocalCart", myLocalCart.getCart().values().stream().toList());
        }
        return "user/userCart";
    }
}
