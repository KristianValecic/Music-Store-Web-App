package hr.valecic.musicstorewebapp.controller;

import hr.valecic.musicstorewebapp.model.Item;
import hr.valecic.musicstorewebapp.model.shopping.ShoppingCartList;
import hr.valecic.musicstorewebapp.viewmodel.InsertItemViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BuyController {


    @GetMapping("/buyItems")
    public String getAllItems(Model model) {
        //TODO
        //inserts purchase into db:
        //Što je kupljeno (sadržaj košarice, sa količinama)
        //o Kada je kupljeno
        //o Kako je kupljeno (gotovina – pouzeće ili PayPal

        //ocisti cartlist
        //ispise thank you for your purchase
        ShoppingCartList.getInstance().clear();
//        model.addAttribute("cartList", ShoppingCartList.getInstance());

        return "buy";
    }

}
