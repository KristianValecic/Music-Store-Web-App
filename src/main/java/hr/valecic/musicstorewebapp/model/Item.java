package hr.valecic.musicstorewebapp.model;

import hr.valecic.musicstorewebapp.viewmodel.InsertItemViewModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;

@Entity
@Table(name="item")
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iditem", nullable = false)
    private Long iditem;
    @Basic
    @Column(name = "mediatype", nullable = false, length = 255)
    private String mediatype;
    @Basic
    @Column(name = "amountinstock", nullable = false)
    private Long amountinstock;
    @ManyToOne
    @JoinColumn(name = "albumid", referencedColumnName = "idalbum", nullable = false)
    private Album album;
    @Basic
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public static Item getItemFromViewModelToItem(InsertItemViewModel insertedItem) throws IOException {
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

    public Long getIditem() {
        return iditem;
    }

    public void setIditem(Long iditem) {
        this.iditem = iditem;
    }

    public String getMediatype() {
        return mediatype;
    }

    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }

    public Long getAmountinstock() {
        return amountinstock;
    }

    public void setAmountinstock(Long amountinstock) {
        this.amountinstock = amountinstock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (iditem != null ? !iditem.equals(item.iditem) : item.iditem != null) return false;
        if (mediatype != null ? !mediatype.equals(item.mediatype) : item.mediatype != null) return false;
        if (amountinstock != null ? !amountinstock.equals(item.amountinstock) : item.amountinstock != null)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Item{" +
                "iditem=" + iditem +
                ", mediatype='" + mediatype + '\'' +
                ", amountinstock=" + amountinstock +
                ", album=" + album.getAlbumname() +
                ", price=" + price +
                '}';
    }

    @Override
    public int hashCode() {
        int result = iditem != null ? iditem.hashCode() : 0;
        result = 31 * result + (mediatype != null ? mediatype.hashCode() : 0);
        result = 31 * result + (amountinstock != null ? amountinstock.hashCode() : 0);
        return result;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
