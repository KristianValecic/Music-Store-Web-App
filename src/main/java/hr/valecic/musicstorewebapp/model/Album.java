package hr.valecic.musicstorewebapp.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Base64;

@Entity
@Table(name="album")
public class Album {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idalbum", nullable = false)
    private Long idalbum;
    @Basic
    @Column(name = "albumname", nullable = false, length = 255)
    private String albumname;
    @Basic
    @Column(name = "genretype", nullable = true, length = 255)
    private String genretype;
    @Basic
    @Column(name = "image", nullable = false)
    private byte[] image;
    @Basic
    @Column(name = "genreid", nullable = true)
    private Long genreid;
    @ManyToOne
    @JoinColumn(name = "artistid", referencedColumnName = "idartist", nullable = false)
    private Artist artist;

    public Long getIdalbum() {
        return idalbum;
    }

    public void setIdalbum(Long idalbum) {
        this.idalbum = idalbum;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public String getGenretype() {
        return genretype;
    }

    public void setGenretype(String genretype) {
        this.genretype = genretype;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public Long getGenreid() {
        return genreid;
    }

    public void setGenreid(Long genreid) {
        this.genreid = genreid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (idalbum != null ? !idalbum.equals(album.idalbum) : album.idalbum != null) return false;
        if (albumname != null ? !albumname.equals(album.albumname) : album.albumname != null) return false;
        if (genretype != null ? !genretype.equals(album.genretype) : album.genretype != null) return false;
        if (!Arrays.equals(image, album.image)) return false;
        if (genreid != null ? !genreid.equals(album.genreid) : album.genreid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idalbum != null ? idalbum.hashCode() : 0;
        result = 31 * result + (albumname != null ? albumname.hashCode() : 0);
        result = 31 * result + (genretype != null ? genretype.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        result = 31 * result + (genreid != null ? genreid.hashCode() : 0);
        return result;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }


    public String getImageInBase64() {
        return Base64.getEncoder().encodeToString(image);
    }

    @Override
    public String toString() {
        return "Album{" +
                "idalbum=" + idalbum +
                ", albumname='" + albumname + '\'' +
                ", genretype='" + genretype + '\'' +
                ", genreid=" + genreid +
                ", artist=" + artist.getArtistname() +
                '}';
    }
}
