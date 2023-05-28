package hr.valecic.musicstorewebapp.controller.admin;

import hr.valecic.musicstorewebapp.Utilities.ItemUtils;
import hr.valecic.musicstorewebapp.dal.service.AlbumService;
import hr.valecic.musicstorewebapp.dal.service.ArtistService;
import hr.valecic.musicstorewebapp.dal.service.GenreService;
import hr.valecic.musicstorewebapp.dal.service.ItemService;
import hr.valecic.musicstorewebapp.model.Album;
import hr.valecic.musicstorewebapp.model.Artist;
import hr.valecic.musicstorewebapp.model.Item;
import hr.valecic.musicstorewebapp.viewmodel.InsertItemViewModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes({"itemsList", "newItem", "image", "errorMessage"})
public class InsertItemController {
    private final ItemService itemService;
    private final ArtistService artistService;
    private final AlbumService albumService;
    private final GenreService genreService;

    @GetMapping("/insertItems")
    public String getAllItems(Model model) {
        List<Item> itemsList = itemService.getAllItems();
        model.addAttribute("itemsList", itemsList);
        model.addAttribute("genreList", genreService.getAllGenres());
        model.addAttribute("newItem", new InsertItemViewModel());
//        if (!model.containsAttribute("image")){
        model.addAttribute("image", null);
//        }
        return "insert";
    }

    @PostMapping("/insertInputedItem")
    public String insertItem(@ModelAttribute("newItem") InsertItemViewModel insertedItem, Model model) throws IOException {
        Item item = Item.getItemFromViewModelToItem(insertedItem);

        byte[] image = insertedItem.getImage().getBytes();
        if (image.length == new byte[0].length) {
            model.addAttribute("errorMessage", "Image must be uploaded.");
            return "redirect:/insertItems";
        }

        if (itemService.itemExists(item)) {
            model.addAttribute("errorMessage", "Item already exists.");
            return "redirect:/insertItems";
        }
        itemService.saveItem(item);
        model.addAttribute("newItem", new InsertItemViewModel());
        return "redirect:/insertItems";
    }

    @PostMapping("/DeleteItemFromDb/{id}")
    public String deleteItemFromDb(@PathVariable("id") Long id, Model model) {
        itemService.deleteItemById(id);
        return "redirect:/insertItems";
    }
    @PostMapping("/updateItem/{id}")
    public String updateItem(@PathVariable("id") Long id, Model model,
                             @RequestParam("artistNameUpdateInput")String artistName,
                             @RequestParam("ablumNameUpdateInput")String albumName,
                             @RequestParam("genreTypeUpdateSelect")String albumGenre,
                             @RequestParam("updatePrice")String itemPrice,
                             @RequestParam("updateAmountInStock")String itemAmountInStock,
                             @RequestParam("imageUpdateInput")MultipartFile imageB64,
                             @RequestParam("mediaTypeUpdateSelect")String itemMedia) {

        Item updatedItem = itemService.getOne(id);
        Album updatedAlbum = albumService.getOne(updatedItem.getAlbum().getIdalbum());
        Artist updatedArtist = artistService.getOne(updatedAlbum.getArtist().getIdartist());

        updatedArtist.setArtistname(artistName);
        artistService.saveArtist(updatedArtist);

        if (!imageB64.isEmpty()){
            try {
                updatedAlbum.setImage(imageB64.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        updatedAlbum.setAlbumname(albumName);
        updatedAlbum.setGenretype(albumGenre);
        albumService.saveAlbum(updatedAlbum);

        updatedItem.setAmountinstock(Long.parseLong(itemAmountInStock));
        updatedItem.setPrice(new BigDecimal(itemPrice));
        updatedItem.setMediatype(itemMedia);

        itemService.updateItem(updatedItem);
        return "redirect:/insertItems";
    }

    @PostMapping("/uploadAlbumImage")
    public String uploadAlbumImage(@RequestParam("image") MultipartFile file, Model model) throws IOException {

        byte[] image = file.getBytes();
        model.addAttribute("image", Base64.getEncoder().encodeToString(image));


        return "redirect:/insertItems";
    }
}
