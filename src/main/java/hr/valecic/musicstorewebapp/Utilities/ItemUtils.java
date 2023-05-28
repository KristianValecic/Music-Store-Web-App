package hr.valecic.musicstorewebapp.Utilities;

import hr.valecic.musicstorewebapp.model.Album;
import hr.valecic.musicstorewebapp.model.Artist;
import hr.valecic.musicstorewebapp.model.Item;
import hr.valecic.musicstorewebapp.viewmodel.InsertItemViewModel;

import java.io.IOException;

public class ItemUtils {
    private ItemUtils() {
    }

    public static Item convertItemViewModelToItem(InsertItemViewModel insertedItem) throws IOException {
        Artist artist = insertedItem.getAlbum().getArtist();

        Album album = insertedItem.getAlbum();
        album.setImage(insertedItem.getImage().getBytes());
        album.setGenretype(insertedItem.getGenretype());
        album.setArtist(artist);

        Item item = new Item();
        item.setMediatype(insertedItem.getMediatype());
        item.setAlbum(album);
        item.setAmountinstock(insertedItem.getAmountinstock());
        item.setPrice(insertedItem.getPrice());

        return item;
    }
}
