package hr.valecic.musicstorewebapp.dal.service;

import hr.valecic.musicstorewebapp.dal.repository.ArtistRepository;
import hr.valecic.musicstorewebapp.model.Artist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public Long saveArtist(Artist artist){
        if (existsByArtistname(artist.getArtistname())){
            return artistRepository.getTArtistByArtistname(artist.getArtistname()).getIdartist();
        }
        artistRepository.save(artist);
        return artist.getIdartist();
    }

    public boolean existsByArtistname(String artistname) {
        return artistRepository.existsByArtistname(artistname);
    }

    public Artist getArtistByName(String artistname) {
        return artistRepository.getTArtistByArtistname(artistname);
    }

    public Artist getOne(Long idartist) {
        return artistRepository.getOne(idartist);
    }
}
