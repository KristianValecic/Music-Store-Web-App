package hr.valecic.musicstorewebapp.controller;

import hr.valecic.musicstorewebapp.model.shopping.ShoppingCartItem;
import hr.valecic.musicstorewebapp.model.shopping.ShoppingCartList;
import hr.valecic.musicstorewebapp.viewmodel.ItemPageViewModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@SessionAttributes({"itemPageViewModel", "cartList"})
public class ShoppingCartController { //TODO: add cart to database for auth users
    @PostMapping("/addItemToCart")
    public String addItemToCart(@ModelAttribute ItemPageViewModel itemPageViewModel,
                                @RequestParam("itemAmount") Integer itemAmount, Model model) {
        itemPageViewModel.setItemAmount(itemAmount);
        ShoppingCartList.addItem(ShoppingCartItem.getItemFromItemPageVM(itemPageViewModel));
        model.addAttribute("cartList", ShoppingCartList.getInstance());

        return "redirect:/shoppingCart";
    }
    @PostMapping("/removeAmountFromCart/{itemIndex}/{amount}")
    public String removeFromCart(@PathVariable("itemIndex") Integer itemIndex,
                                 @PathVariable("amount") Integer amount, Model model) {
        ShoppingCartList.decreaseItemAmount(itemIndex, amount);

        model.addAttribute("cartList", ShoppingCartList.getInstance());

        return "redirect:/shoppingCart";
    }
    @PostMapping("/removeItemFromCart/{itemIndex}")
    public String removeFromCart(@PathVariable("itemIndex") Integer itemIndex, Model model) {
        ShoppingCartList.getInstance().remove(ShoppingCartList.getInstance().get(itemIndex));

        model.addAttribute("cartList", ShoppingCartList.getInstance());

        return "redirect:/shoppingCart";
    }
    @GetMapping("/shoppingCart")
    public String itemCart(Model model) {
        model.addAttribute("cartList", ShoppingCartList.getInstance());

        return "shoppingCart";
    }
    @GetMapping("/clearShoppingCart")
    public String clearShoppingCart(Model model) {
        ShoppingCartList.getInstance().clear();
        model.addAttribute("cartList", ShoppingCartList.getInstance());

        return "redirect:/shoppingCart";
    }
}
