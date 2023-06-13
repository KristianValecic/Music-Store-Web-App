package hr.valecic.musicstorewebapp.dal.service;

import hr.valecic.musicstorewebapp.dal.repository.AlbumRepository;
import hr.valecic.musicstorewebapp.model.Album;
import hr.valecic.musicstorewebapp.model.Artist;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlbumService {

    private final ArtistService artistService;
    private final AlbumRepository albumRepository;

    public Long saveAlbum(Album album) {
//        if album exists reutrn existsing albums id
        if (albumRepository.existsAlbumByAlbumnameAndGenretypeAndArtist_Artistname(album.getAlbumname(),
                album.getGenretype(), album.getArtist().getArtistname())){
            Album tempalbum = albumRepository.getAlbumByAlbumnameAndGenretypeAndArtist_Artistname(
                    album.getAlbumname(), album.getGenretype(), album.getArtist().getArtistname()
            );
            return tempalbum.getIdalbum();
        }
//        otherwise check if artist exists, if exists save the album by existing artist
        if (artistService.existsByArtistname(album.getArtist().getArtistname())) {
            Artist artistByName = artistService.getArtistByName(album.getArtist().getArtistname());
            album.getArtist().setIdartist(artistByName.getIdartist());
        }

        artistService.saveArtist(album.getArtist());
        albumRepository.save(album);

        return album.getIdalbum();
    }

    public Album getAlbumByNameArtistGenre(String artistName, String albumName, String albumGenre) {
        System.out.println(artistName + ", " +  albumName+ ", " +  albumGenre);
        return albumRepository.getAlbumByAlbumnameAndGenretypeAndArtist_Artistname(albumName, albumGenre, artistName);
    }

    public boolean existsByAlbumNameGenreTypeArtistName(String artistName, String albumName, String albumGenre) {
        return albumRepository.existsAlbumByAlbumnameAndGenretypeAndArtist_Artistname(albumName, albumGenre, artistName);
    }

    public Album getOne(Long idalbum) {
        return albumRepository.getOne(idalbum);
    }
}
