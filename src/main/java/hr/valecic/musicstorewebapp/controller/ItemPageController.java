package hr.valecic.musicstorewebapp.controller;

import hr.valecic.musicstorewebapp.dal.service.ItemService;
import hr.valecic.musicstorewebapp.model.Item;
import hr.valecic.musicstorewebapp.viewmodel.ItemPageViewModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@SessionAttributes({"itemPageViewModel", "cartList", "authRole"})
public class ItemPageController {
    private final ItemService itemService;

    @GetMapping("/itemPage/{id}")
    public String getItemById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("errorMsgForItemPage", "");

        ItemPageViewModel itemPageViewModel = new ItemPageViewModel();
        itemPageViewModel.setItem(itemService.getAllItemById(id));
        itemPageViewModel.setItemAmount(1);

        model.addAttribute("itemPageViewModel", itemPageViewModel);
        System.out.println(itemPageViewModel.getItem().getAmountinstock() > 0);
        model.addAttribute("inStock", itemPageViewModel.getItem().getAmountinstock() > 0);
        model.addAttribute("amountInStock", itemPageViewModel.getItem().getAmountinstock());

        return "itemPage";
    }


}
