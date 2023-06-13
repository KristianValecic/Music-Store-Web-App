package hr.valecic.musicstorewebapp.controller;

import hr.valecic.musicstorewebapp.dal.service.PersonService;
import hr.valecic.musicstorewebapp.dal.service.ShoppingcartService;
import hr.valecic.musicstorewebapp.model.CustomPersonDetails;
import hr.valecic.musicstorewebapp.model.Person;
import hr.valecic.musicstorewebapp.model.Shoppingcartitem;
import hr.valecic.musicstorewebapp.model.shopping.ShoppingCartItem;
import hr.valecic.musicstorewebapp.model.shopping.ShoppingCartList;
import hr.valecic.musicstorewebapp.viewmodel.ItemPageViewModel;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes({"itemPageViewModel", "cartList", "cartTotalPrice", "authRole"})
public class ShoppingCartController {

    private static BigDecimal totalCartPrice = new BigDecimal("0");
    private static boolean isAddingItemsToCartFLAG = false;
    private PersonService personService;
    private ShoppingcartService shoppingcartService;

    @GetMapping("/shoppingCart")
    public String itemCart(Model model) {
        totalCartPrice = new BigDecimal("0");

        if(userLoggedIn(model)){
           Person person = getPersonFromContext();


            if (!shoppingcartService.existsCartForPerson(person) && !ShoppingCartList.getInstance().isEmpty()) {
//                if cart doesnt exist in db and isn't empty
                //TODO: napravi adding method i onda to korsiti i ovdje
                List<ShoppingCartItem> instanceBefore = ShoppingCartList.getInstance();//For control

                Collection<Shoppingcartitem> shoppingcartitems = Shoppingcartitem.convertFromShoppingCartItem(ShoppingCartList.getInstance());
                shoppingcartService.saveCart(person, shoppingcartitems);
            } else if (shoppingcartService.existsCartForPerson(person)) {
//                got items list from db
                loadCartFromDb(person);
//                if (isAddingItemsToCartFLAG){
//                    saveChangedCartToDb(person);
//                }
            }
            isAddingItemsToCartFLAG = false;
        }
        model.addAttribute("cartList", ShoppingCartList.getInstance());
        ShoppingCartList.getInstance().forEach(shoppingCartItem -> totalCartPrice = totalCartPrice.add(shoppingCartItem.getTotalPrice()));
        model.addAttribute("cartTotalPrice", totalCartPrice);

        return "shoppingCart";
    }

    private Person getPersonFromContext() {
       CustomPersonDetails principal = (CustomPersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return personService.getPersonByEmail(principal.getUsername()).get();
    }

    private void loadCartFromDb(Person person) {
        Collection<Shoppingcartitem> cartItemsListForPerson = shoppingcartService.getCartItemsListForPerson(person);
        Collection<ShoppingCartItem> tempList = ShoppingCartList.convertFromShoppingcartitem(cartItemsListForPerson);

        List<ShoppingCartItem> instanceBefore = new ArrayList<>(ShoppingCartList.getInstance());//For control

        ShoppingCartList.getInstance().clear();
       // tempList.forEach(ShoppingCartList::addItem);//
        ShoppingCartList.getInstance().addAll(tempList);

        List<ShoppingCartItem> instanceAFTERR = new ArrayList<>(ShoppingCartList.getInstance());//For control

    }

    private void saveChangedCartToDb(Person person) {
//        Collection<Shoppingcartitem> cartItemsListForPerson = shoppingcartService.getCartItemsListForPerson(person);
//        Collection<ShoppingCartItem> tempList = ShoppingCartList.convertFromShoppingcartitem(cartItemsListForPerson);

//        List<ShoppingCartItem> instance = ShoppingCartList.getInstance();//For control

//        ShoppingCartList.getInstance().clear();
//        tempList.forEach(ShoppingCartList::addItem);
//        ShoppingCartList.getInstance().
//        shoppingcartService.saveCart(person, Shoppingcartitem.convertFromShoppingCartItem(ShoppingCartList.getInstance()));
    }

    @PostMapping("/addItemToCart")
    public String addItemToCart(@ModelAttribute ItemPageViewModel itemPageViewModel,
                                @RequestParam("itemAmount") Integer itemAmount, Model model) {
        itemPageViewModel.setItemAmount(itemAmount);
        ShoppingCartList.addItem(ShoppingCartItem.getItemFromItemPageVM(itemPageViewModel));

        saveCartForLoggedInUSer(model);
        model.addAttribute("cartList", ShoppingCartList.getInstance());
        isAddingItemsToCartFLAG = true;
        return "redirect:/shoppingCart";
    }

    private void saveCartForLoggedInUSer(Model model) {
        if(userLoggedIn(model)){
            Person person = getPersonFromContext();
            Collection<Shoppingcartitem> shoppingcartitems = Shoppingcartitem.convertFromShoppingCartItem(ShoppingCartList.getInstance());
            shoppingcartService.saveCart(person, shoppingcartitems);
        }
    }

    private boolean userLoggedIn(Model model) {
        return model.containsAttribute("authRole") && model.getAttribute("authRole") != "";
    }

    @PostMapping("/removeAmountFromCart/{itemIndex}/{amount}")
    public String removeFromCart(@PathVariable("itemIndex") Integer itemIndex,
                                 @PathVariable("amount") Integer amount, Model model) {
        ShoppingCartList.decreaseItemAmount(itemIndex, amount);
        saveCartForLoggedInUSer(model);

        model.addAttribute("cartList", ShoppingCartList.getInstance());
        deleteItemIfNoneLeft();
        deleteIfEmptyCart();
        return "redirect:/shoppingCart";
    }

    @PostMapping("/removeItemFromCart/{itemIndex}")
    public String removeFromCart(@PathVariable("itemIndex") Integer itemIndex, Model model) {
        ShoppingCartList.getInstance().remove(ShoppingCartList.getInstance().get(itemIndex));
        saveCartForLoggedInUSer(model);

        model.addAttribute("cartList", ShoppingCartList.getInstance());
        deleteItemIfNoneLeft();
        deleteIfEmptyCart();
        return "redirect:/shoppingCart";
    }

    @GetMapping("/clearShoppingCart")
    public String clearShoppingCart(Model model) {
        ShoppingCartList.getInstance().clear();
        saveCartForLoggedInUSer(model);
        deleteIfEmptyCart();

        model.addAttribute("cartList", ShoppingCartList.getInstance());
        return "redirect:/shoppingCart";
    }

    private void deleteItemIfNoneLeft() {
//TODO
        if (ShoppingCartList.getInstance().isEmpty()){
            CustomPersonDetails principal = (CustomPersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Person person = personService.getPersonByEmail(principal.getUsername()).get();

            //delete item
        }
    }

    private void deleteIfEmptyCart() {
        if (ShoppingCartList.getInstance().isEmpty()){
            CustomPersonDetails principal = (CustomPersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Person person = personService.getPersonByEmail(principal.getUsername()).get();

            shoppingcartService.deleteShoppingCartForPerson(person);
        }
    }
}
