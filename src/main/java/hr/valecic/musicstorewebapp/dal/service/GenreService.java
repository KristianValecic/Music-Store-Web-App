package hr.valecic.musicstorewebapp.dal.service;

import hr.valecic.musicstorewebapp.dal.repository.GenreRepository;
import hr.valecic.musicstorewebapp.dal.repository.ItemRepository;
import hr.valecic.musicstorewebapp.model.Genre;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    public void saveGenre(String newGenreCategory) {
        genreRepository.save(new Genre(newGenreCategory));
    }

    @Transactional
    public void removeGenreById(Long id) {
        genreRepository.removeByIdgenre(id);
    }
}
