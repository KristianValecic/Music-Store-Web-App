package hr.valecic.musicstorewebapp.model;

import hr.valecic.musicstorewebapp.model.shopping.ShoppingCartItem;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Shoppingcartitem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcartitem", nullable = false)
    private Long idcartitem;
    @Basic
    @Column(name = "itemamount", nullable = false)
    private Integer itemamount;
    @Basic
    @Column(name = "totalprice", nullable = false)
    private BigDecimal totalprice;
    @ManyToOne
    @JoinColumn(name = "itemid", referencedColumnName = "iditem", nullable = false)
    private Item itemByItemid;
    @ManyToOne
    @JoinColumn(name = "shoppingcartid", referencedColumnName = "idcart", nullable = false)
    private Shoppingcart shoppingcartByShoppingcartid;

    public static Collection<Shoppingcartitem> convertFromShoppingCartItem(Collection<ShoppingCartItem> cartItemsListForPerson) {
        Collection<Shoppingcartitem> tempList = new ArrayList<>();
        cartItemsListForPerson.forEach(shoppingCartItem -> {
            Shoppingcartitem shoppingcartitem = new Shoppingcartitem();
            shoppingcartitem.setItemamount(shoppingCartItem.getItemAmount());
            shoppingcartitem.setTotalprice(shoppingCartItem.getTotalPrice());
            shoppingcartitem.setItemByItemid(shoppingCartItem.getItem());
            tempList.add(shoppingcartitem);
        });
        return tempList;
    }

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

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shoppingcartitem that = (Shoppingcartitem) o;

        if (idcartitem != null ? !idcartitem.equals(that.idcartitem) : that.idcartitem != null) return false;
        if (itemamount != null ? !itemamount.equals(that.itemamount) : that.itemamount != null) return false;
        if (totalprice != null ? !totalprice.equals(that.totalprice) : that.totalprice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcartitem != null ? idcartitem.hashCode() : 0;
        result = 31 * result + (itemamount != null ? itemamount.hashCode() : 0);
        result = 31 * result + (totalprice != null ? totalprice.hashCode() : 0);
        return result;
    }

    public Item getItemByItemid() {
        return itemByItemid;
    }

    public void setItemByItemid(Item itemByItemid) {
        this.itemByItemid = itemByItemid;
    }

    public Shoppingcart getShoppingcartByShoppingcartid() {
        return shoppingcartByShoppingcartid;
    }

    public void setShoppingcartByShoppingcartid(Shoppingcart shoppingcartByShoppingcartid) {
        this.shoppingcartByShoppingcartid = shoppingcartByShoppingcartid;
    }
}
