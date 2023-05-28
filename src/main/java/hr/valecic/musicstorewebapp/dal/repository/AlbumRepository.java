package hr.valecic.musicstorewebapp.dal.repository;

import hr.valecic.musicstorewebapp.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    boolean existsAlbumByAlbumnameAndGenretypeAndArtist_Artistname(String albumName, String genreType, String artistName);
    Album getAlbumByAlbumnameAndGenretypeAndArtist_Artistname(String albumName, String genreType, String artistName);

}
