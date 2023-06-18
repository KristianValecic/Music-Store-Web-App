package hr.valecic.musicstorewebapp.controller.admin;

import hr.valecic.musicstorewebapp.dal.service.LoginhistoryService;
import hr.valecic.musicstorewebapp.dal.service.PurchaseService;
import hr.valecic.musicstorewebapp.model.Loginhistory;
import hr.valecic.musicstorewebapp.model.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes({"authRole"})
    public class UsersController {
    private LoginhistoryService loginhistoryService;
    private PurchaseService purchaseService;
    @GetMapping("/users")
    public String getAllItems(Model model) {
        List<Loginhistory> loggedUsersList = loginhistoryService.getAll();
        List<Purchase> purchaseHistoryList = purchaseService.getAll();

        model.addAttribute("loggedUsersList", loggedUsersList);
        model.addAttribute("purchaseHistoryList", purchaseHistoryList);
        return "users";
    }

    @GetMapping("/filterPurchaseHistory")
    public String filterPurchaseHistory(@RequestParam("fromDate")String fromDate, @RequestParam("toDate")String toDate, Model model) {
        List<Loginhistory> loggedUsersList = loginhistoryService.getAll();
        List<Purchase> purchaseHistoryList = purchaseService.getFilteredByDate(fromDate, toDate);

        System.out.println("filterPurchaseHistory");

        model.addAttribute("loggedUsersList", loggedUsersList);
        model.addAttribute("purchaseHistoryList", purchaseHistoryList);
        return "users";
    }
}
