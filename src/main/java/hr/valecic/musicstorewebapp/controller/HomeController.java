package hr.valecic.musicstorewebapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.valecic.musicstorewebapp.dal.service.GenreService;
import hr.valecic.musicstorewebapp.dal.service.ItemService;
import hr.valecic.musicstorewebapp.model.Genre;
import hr.valecic.musicstorewebapp.model.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes({"itemsList"})
public class HomeController {
    private final ItemService itemService;
    private final GenreService genreService;
    private List<Item> itemsList;
    private List<Genre> genreList;

    @GetMapping("/home")
    public String getAllItems(Model model) {
        //add loading of list only if the list is empty
        itemsList = itemService.getAllItems();
        genreList = genreService.getAllGenres();
        model.addAttribute("itemsList", itemsList);
        model.addAttribute("genreList", genreList);

        return "home";
    }

    @GetMapping("/filterHome")
    public String filterItems(@RequestParam("mediaType") String mediaTypeJSON,
                              @RequestParam("genreType") String genreTypeJSON, Model model) {
        ObjectMapper mapper = new ObjectMapper();
        List<String> mediaFilters;
        List<String> genreFilters;
        try {
            mediaFilters = mapper.readValue(mediaTypeJSON, new TypeReference<List<String>>() {
            });
            genreFilters = mapper.readValue(genreTypeJSON, new TypeReference<List<String>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<Item> itemsListForFiltering = new ArrayList<>();

        filterByMedia(mediaFilters, itemsListForFiltering);
        itemsListForFiltering = filterByGenre(genreFilters, itemsListForFiltering);

        model.addAttribute("itemsList", itemsListForFiltering);

        return "home";
    }

    private List<Item> filterByGenre(List<String> genreFilters, List<Item> itemsListMediaFiltered) {
        if (genreFilters.isEmpty()) {
            return itemsListMediaFiltered;
        }
        List<Item> filteredList = new ArrayList<>();
        for (String genreFilter : genreFilters) {
            filteredList.addAll(itemsListMediaFiltered.stream().filter(item -> item.getAlbum().getGenretype().equals(genreFilter)).toList());
        }
        return filteredList;
    }

    private void filterByMedia(List<String> mediaFilters, List<Item> itemsListForFiltering) {
        if (!mediaFilters.isEmpty()) {
            for (String mediaFilter : mediaFilters) {
                itemsListForFiltering.addAll(itemService.getAllItemsFilteredByMedia(mediaFilter));
            }
        } else {
            itemsListForFiltering.addAll(itemsList);
        }
    }
}
