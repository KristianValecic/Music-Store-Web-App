package hr.valecic.musicstorewebapp.controller;

import hr.valecic.musicstorewebapp.dal.service.ItemService;
import hr.valecic.musicstorewebapp.dal.service.PersonService;
import hr.valecic.musicstorewebapp.dal.service.PurchaseService;
import hr.valecic.musicstorewebapp.dal.service.ShoppingcartService;
import hr.valecic.musicstorewebapp.model.*;
import hr.valecic.musicstorewebapp.model.shopping.ShoppingCartList;
import hr.valecic.musicstorewebapp.viewmodel.InsertItemViewModel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes({"authRole"})
@AllArgsConstructor
public class BuyController {
    private PersonService personService;
    private PurchaseService purchaseService;
    private ItemService itemService;
    private ShoppingcartService shoppingcartService;

    @GetMapping("/buyItems")
    public String getAllItems(@RequestParam("paymentMethod") String inputType, Model model) {
        //TODO
        //inserts purchase into db:
        //Što je kupljeno (sadržaj košarice, sa količinama) --
        //o Kada je kupljeno --
        //o Kako je kupljeno (gotovina – pouzeće ili PayPal --

        if (model.getAttribute("auhtRole") != ""){
            savePurchase(inputType);

            ShoppingCartList.getInstance().forEach(shoppingCartItem -> {
                itemService.subtractAmountOfBoughtItem(shoppingCartItem);
            });
        }

        //ocisti cartlist --
        //makne kolicinu porizvoda iz baze
        //ispise thank you for your purchase --
        ShoppingCartList.getInstance().clear();
//        model.addAttribute("cartList", ShoppingCartList.getInstance());

        return "buy";
    }

    private void savePurchase(String inputType) {
        CustomPersonDetails principal = (CustomPersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person = personService.getPersonByEmail(principal.getUsername()).get();

        Purchase purchase = new Purchase();
        purchase.setPaymentmethod(inputType);
        purchase.setTimeofpurchase(getCurrentTime());
        purchase.setPersonByPersonid(person);

        Shoppingcart lastCartItemsListForPerson = shoppingcartService.getLastCartItemsListForPerson(person);
        purchase.setShoppingcartByShoppingcartid(lastCartItemsListForPerson);
        purchaseService.savePurchase(purchase);
    }


    @GetMapping("/buyPage")
    public String showBuyPage(Model model) {

        return "buy";
    }
    private Timestamp getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        return new java.sql.Timestamp(now.getTime());
    }

}
