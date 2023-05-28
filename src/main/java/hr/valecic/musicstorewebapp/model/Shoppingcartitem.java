package hr.valecic.musicstorewebapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shoppingcartitem")
public class Shoppingcartitem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcartitem")
    private Long idcartitem;
    @Basic
    @Column(name = "itemamount")
    private Integer itemamount;
    @Basic
    @Column(name = "totalprice")
    private Object totalprice;
    @Basic
    @Column(name = "itemid")
    private Long itemid;
    @ManyToOne
    @JoinColumn(name = "shoppingcartid", referencedColumnName = "idcart", nullable = false)
    private Shoppingcart shoppingcartid;

    public Long getIdcartitem() {
        return idcartitem;
    }

    public void setIdcartitem(Long idcartitem) {
        this.idcartitem = idcartitem;
    }

    public Integer getItemamount() {
        return itemamount;
    }

    public void setItemamount(Integer itemamount) {
        this.itemamount = itemamount;
    }

    public Object getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Object totalprice) {
        this.totalprice = totalprice;
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public Shoppingcart getShoppingcartid() {
        return shoppingcartid;
    }

    public void setShoppingcartid(Shoppingcart shoppingcartid) {
        this.shoppingcartid = shoppingcartid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shoppingcartitem that = (Shoppingcartitem) o;

        if (idcartitem != null ? !idcartitem.equals(that.idcartitem) : that.idcartitem != null) return false;
        if (itemamount != null ? !itemamount.equals(that.itemamount) : that.itemamount != null) return false;
        if (totalprice != null ? !totalprice.equals(that.totalprice) : that.totalprice != null) return false;
        if (itemid != null ? !itemid.equals(that.itemid) : that.itemid != null) return false;
        if (shoppingcartid != null ? !shoppingcartid.equals(that.shoppingcartid) : that.shoppingcartid != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcartitem != null ? idcartitem.hashCode() : 0;
        result = 31 * result + (itemamount != null ? itemamount.hashCode() : 0);
        result = 31 * result + (totalprice != null ? totalprice.hashCode() : 0);
        result = 31 * result + (itemid != null ? itemid.hashCode() : 0);
        result = 31 * result + (shoppingcartid != null ? shoppingcartid.hashCode() : 0);
        return result;
    }
}
