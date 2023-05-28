package hr.valecic.musicstorewebapp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {


    @GetMapping("/users")
    public String getAllItems(Model model) {
        return "users";
    }

}
