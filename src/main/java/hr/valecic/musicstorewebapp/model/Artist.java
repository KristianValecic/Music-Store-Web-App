package hr.valecic.musicstorewebapp.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name="artist")
public class Artist {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idartist", nullable = false)
    private Long idartist;
    @Basic
    @Column(name = "artistname", nullable = false, length = 255)
    private String artistname;
    @OneToMany(mappedBy = "artist")
    private Collection<Album> albums;

    public Long getIdartist() {
        return idartist;
    }

    public void setIdartist(Long idartist) {
        this.idartist = idartist;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (idartist != null ? !idartist.equals(artist.idartist) : artist.idartist != null) return false;
        if (artistname != null ? !artistname.equals(artist.artistname) : artist.artistname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idartist != null ? idartist.hashCode() : 0;
        result = 31 * result + (artistname != null ? artistname.hashCode() : 0);
        return result;
    }

    public Collection<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Collection<Album> albums) {
        this.albums = albums;
    }
    @Override
    public String toString() {
        return "Artist{" +
                "idartist=" + idartist +
                ", artistname='" + artistname + '\'' +
                ", albums=" + albums +
                '}';
    }
}
