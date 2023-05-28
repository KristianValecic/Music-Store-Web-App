package hr.valecic.musicstorewebapp.dal.service;

import hr.valecic.musicstorewebapp.dal.repository.ItemRepository;
import hr.valecic.musicstorewebapp.model.Item;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final AlbumService albumService;
    private final ItemRepository itemRepository;

    public Long saveItem(Item item) {
        Long albumID = albumService.saveAlbum(item.getAlbum());
        item.getAlbum().setIdalbum(albumID);
        itemRepository.save(item);
        return item.getIditem();
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getAllItemById(Long id) {
        return itemRepository.findItemByIditem(id);
    }

    public boolean itemExists(Item item) {
        String artistName = item.getAlbum().getArtist().getArtistname();
        String albumName = item.getAlbum().getAlbumname();
        String genre = item.getAlbum().getGenretype();

        boolean album = itemRepository.existsItemByAlbum_AlbumnameAndAlbum_Genretype(albumName, genre);
        boolean artist = itemRepository.existsItemByAlbum_Artist_Artistname(artistName);
        boolean media = itemRepository.existsItemByAlbumAlbumnameAndMediatype(albumName, item.getMediatype());

        return album
                && artist
                && media;
    }

    @Transactional
    public void deleteItemById(Long id) {
        itemRepository.deleteItemByIditem(id);
    }

    public List<Item> getAllItemsFiltered(String mediaType, String genreType) {
        return itemRepository.findAllByMediatypeOrAlbum_Genretype(mediaType, genreType);
    }

    public List<Item> getAllItemsFilteredByMedia(String mediaType) {
        return itemRepository.findAllByMediatype(mediaType);
    }

    public void updateItem(Item updatedItem) {
        itemRepository.save(updatedItem);
    }

    public Item getOne(Long id) {
        return itemRepository.findById(id).get();
    }
}
