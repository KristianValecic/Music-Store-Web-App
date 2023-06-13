package hr.valecic.musicstorewebapp.controller;

import hr.valecic.musicstorewebapp.dal.service.PersonService;
import hr.valecic.musicstorewebapp.dal.service.PurchaseService;
import hr.valecic.musicstorewebapp.model.CustomPersonDetails;
import hr.valecic.musicstorewebapp.model.Item;
import hr.valecic.musicstorewebapp.model.Person;
import hr.valecic.musicstorewebapp.model.Purchase;
import hr.valecic.musicstorewebapp.model.shopping.ShoppingCartList;
import hr.valecic.musicstorewebapp.viewmodel.InsertItemViewModel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"authRole"})
@AllArgsConstructor
public class BuyController {
    private PersonService personService;
    private PurchaseService purchaseService;

    @GetMapping("/buyItems")
    public String getAllItems(Model model) {
        //TODO
        //inserts purchase into db:
        //Što je kupljeno (sadržaj košarice, sa količinama)
        //o Kada je kupljeno
        //o Kako je kupljeno (gotovina – pouzeće ili PayPal

        if (model.getAttribute("auhtRole") != ""){
            CustomPersonDetails principal = (CustomPersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Person person = personService.getPersonByEmail(principal.getUsername()).get();


        }
        if (model.getAttribute("auhtRole") != ""){
            CustomPersonDetails principal = (CustomPersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Person person = personService.getPersonByEmail(principal.getUsername()).get();


        }

        //ocisti cartlist
        //makne kolicinu porizvoda iz baze
        //ispise thank you for your purchase
        ShoppingCartList.getInstance().clear();
//        model.addAttribute("cartList", ShoppingCartList.getInstance());

        return "buy";
    }

}
