package hr.valecic.musicstorewebapp.controller.admin;

import hr.valecic.musicstorewebapp.dal.service.GenreService;
import hr.valecic.musicstorewebapp.dal.service.ItemService;
import hr.valecic.musicstorewebapp.model.Genre;
import hr.valecic.musicstorewebapp.model.Item;
import hr.valecic.musicstorewebapp.model.enums.MediaType;
import hr.valecic.musicstorewebapp.viewmodel.CategoryViewModel;
import hr.valecic.musicstorewebapp.viewmodel.InsertItemViewModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes({"newGenreCategory", "genresList"})
public class CategoryController {

    private final GenreService genreService;

    @GetMapping("/categories")
    public String getAllItems(Model model) {
        model.addAttribute("genresList",  genreService.getAllGenres());
        model.addAttribute("newGenreCategory", new CategoryViewModel());

        return "categories";
    }
    @PostMapping("/insertGenreCetgory")
    public String insertGenreCetgory(@ModelAttribute("newGenreCategory") CategoryViewModel categoryViewModel,
                                     Model model) {
        genreService.saveGenre(categoryViewModel.getNewGenreCategory().toUpperCase());
        model.addAttribute("newGenreCategory", new CategoryViewModel());
        return "redirect:/categories";
    }
    @DeleteMapping("/deleteGenreFromDb/{id}")
    public String insertGenreCetgory(@PathVariable("id") Long id, Model model) {
        genreService.removeGenreById(id);
        return "redirect:/categories";
    }

}
