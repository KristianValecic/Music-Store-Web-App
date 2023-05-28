package hr.valecic.musicstorewebapp.controller;

import hr.valecic.musicstorewebapp.model.Item;
import hr.valecic.musicstorewebapp.viewmodel.InsertItemViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BuyController {


    @GetMapping("/buyItems")
    public String getAllItems(Model model) {

        return "buy";
    }

}
