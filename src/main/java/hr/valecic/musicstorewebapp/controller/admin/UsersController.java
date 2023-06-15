package hr.valecic.musicstorewebapp.controller.admin;

import hr.valecic.musicstorewebapp.dal.service.LoginhistoryService;
import hr.valecic.musicstorewebapp.model.Loginhistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes({"authRole"})
public class UsersController {
    private LoginhistoryService loginhistoryService;
    @GetMapping("/users")
    public String getAllItems(Model model) {
        List<Loginhistory> loggedUsersList = loginhistoryService.getAll();
        model.addAttribute("loggedUsersList", loggedUsersList);
        return "users";
    }

}
