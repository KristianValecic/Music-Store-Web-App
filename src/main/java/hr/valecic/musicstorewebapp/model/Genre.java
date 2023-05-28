package hr.valecic.musicstorewebapp.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "genre")
public class Genre {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idgenre", nullable = false)
    private Long idgenre;
    @Basic
    @Column(name = "genrename", nullable = false, length = 255)
    private String genrename;

    public Genre() {
    }

    public Genre(String genrename) {
        this.genrename = genrename;
    }

    public Long getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(Long idgenre) {
        this.idgenre = idgenre;
    }

    public String getGenrename() {
        return genrename;
    }

    public void setGenrename(String genrename) {
        this.genrename = genrename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (idgenre != null ? !idgenre.equals(genre.idgenre) : genre.idgenre != null) return false;
        if (genrename != null ? !genrename.equals(genre.genrename) : genre.genrename != null) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = idgenre != null ? idgenre.hashCode() : 0;
        result = 31 * result + (genrename != null ? genrename.hashCode() : 0);
        return result;
    }

}
