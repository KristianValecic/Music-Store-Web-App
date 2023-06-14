package hr.valecic.musicstorewebapp.dal.repository;

import hr.valecic.musicstorewebapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    boolean existsItemByAlbumAlbumnameAndMediatype(String albumName, String mediaType);
    boolean existsItemByAlbum_AlbumnameAndAlbum_Genretype(String albumName, String albumGenreType);
    boolean existsItemByAlbum_Artist_Artistname(String artistName);
    Item findItemByIditem(Long id);
    Long deleteItemByIditem(Long id);

    List<Item> findAllByMediatypeOrAlbum_Genretype(String mediaType, String albumGenreType);
    List<Item> findAllByMediatype(String mediaType);

}
