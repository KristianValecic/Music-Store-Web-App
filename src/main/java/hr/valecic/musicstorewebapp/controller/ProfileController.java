package hr.valecic.musicstorewebapp.controller;

import hr.valecic.musicstorewebapp.dal.service.PersonService;
import hr.valecic.musicstorewebapp.model.CustomPersonDetails;
import hr.valecic.musicstorewebapp.model.Person;
import hr.valecic.musicstorewebapp.model.shopping.ShoppingCartList;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@SessionAttributes({"authRole"})
public class ProfileController {
    private PersonService personService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String openProfilePage(Model model) {
        CustomPersonDetails principal = (CustomPersonDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("person", personService.getPersonByEmail(principal.getUsername()).get());

        return "profile";
    }

    @GetMapping("/editProfile")
    public String openEditProfilePage(Model model) {
        CustomPersonDetails principal = (CustomPersonDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("person", personService.getPersonByEmail(principal.getUsername()).get());

        return "editProfile";
    }

    @PostMapping("/savePersonChanges")
    public String savePersonChanges(@ModelAttribute Person person, @RequestParam("password") String password, Model model) {
//        insert update logic
        if (!password.isBlank()){
            person.setPassword(passwordEncoder.encode(password));
        }
        personService.savePerson(person);
        return "redirect:/profile";
    }
}
