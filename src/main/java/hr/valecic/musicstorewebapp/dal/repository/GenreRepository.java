package hr.valecic.musicstorewebapp.dal.repository;

import hr.valecic.musicstorewebapp.model.Artist;
import hr.valecic.musicstorewebapp.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository  extends JpaRepository<Genre, Long> {
    void removeByIdgenre(Long id);
}
