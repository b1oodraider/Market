package WebMarket.Market.controllers;

import WebMarket.Market.DTO.DBCartDTO;
import WebMarket.Market.models.GoodEntity;
import WebMarket.Market.models.LocalCart;
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
public class CartController {

    private final CartService cartService;
    private final LocalCart myLocalCart;
    private final GoodService goodService;

    public CartController(CartService cartService, LocalCart myLocalCart, GoodService goodService) {
        this.cartService = cartService;
        this.myLocalCart = myLocalCart;
        this.goodService = goodService;
    }


    @GetMapping("/cart")
    public String cart(@ModelAttribute("good") DBCartDTO good, Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            UsersDetails usersDetails = (UsersDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("myDBCart", cartService.getCart(usersDetails.getUser().getId()));
        } else {
            model.addAttribute("myLocalCart", myLocalCart.getCart().values().stream().toList());
        }
        return "user/userCart";
    }

    @PostMapping("/cart")
    public String updateCart(@ModelAttribute("good") DBCartDTO good, @RequestParam(name="user_id") int userId, @RequestParam("count") int quantity, @RequestParam("good_id") String goodId) {
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            cartService.changeCount(userId, Integer.parseInt(goodId), quantity);
        } else {
            myLocalCart.changeCount(Integer.parseInt(goodId), quantity);
        }
        return "redirect:cart";
    }
    @PostMapping("/{id}")
    public String addToCart(@ModelAttribute("good") GoodEntity good) {
        GoodEntity newGood = goodService.getById(good.getId());
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            UsersDetails usersDetails = (UsersDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            cartService.save(usersDetails.getUser().getId(), newGood.getId(), 1);
        }
        myLocalCart.add(newGood, 1);
        return "redirect:/stock";
    }
}
