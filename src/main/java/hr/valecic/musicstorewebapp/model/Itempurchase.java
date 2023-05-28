package hr.valecic.musicstorewebapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "itempurchase")
public class Itempurchase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iditempurchase", nullable = false)
    private Long iditempurchase;
    @Basic
    @Column(name = "amount", nullable = false)
    private Integer amount;
//    @Basic
//    @Column(name = "purchaseid", nullable = false)
//    private Integer purchaseid;
//    @Basic
//    @Column(name = "itemid", nullable = false)
//    private Integer itemid;
    @ManyToOne
    @JoinColumn(name = "itemid", referencedColumnName = "iditem", nullable = false)
    private Item itemByItemid;
    @ManyToOne
    @JoinColumn(name = "purchaseid", referencedColumnName = "idpurchase", nullable = false)
    private Purchase purchaseByPurchaseid;

    public Long getIditempurchase() {
        return iditempurchase;
    }

    public void setIditempurchase(Long iditempurchase) {
        this.iditempurchase = iditempurchase;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

//    public Integer getPurchaseid() {
//        return purchaseid;
//    }
//
//    public void setPurchaseid(Integer purchaseid) {
//        this.purchaseid = purchaseid;
//    }

//    public Integer getItemid() {
//        return itemid;
//    }
//
//    public void setItemid(Integer itemid) {
//        this.itemid = itemid;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Itempurchase that = (Itempurchase) o;

        if (iditempurchase != null ? !iditempurchase.equals(that.iditempurchase) : that.iditempurchase != null)
            return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
//        if (purchaseid != null ? !purchaseid.equals(that.purchaseid) : that.purchaseid != null) return false;
//        if (itemid != null ? !itemid.equals(that.itemid) : that.itemid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iditempurchase != null ? iditempurchase.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
//        result = 31 * result + (purchaseid != null ? purchaseid.hashCode() : 0);
//        result = 31 * result + (itemid != null ? itemid.hashCode() : 0);
        return result;
    }

    public Item getItemByItemid() {
        return itemByItemid;
    }

    public void setItemByItemid(Item itemByItemid) {
        this.itemByItemid = itemByItemid;
    }

    public Purchase getPurchaseByPurchaseid() {
        return purchaseByPurchaseid;
    }

    public void setPurchaseByPurchaseid(Purchase purchaseByPurchaseid) {
        this.purchaseByPurchaseid = purchaseByPurchaseid;
    }
}
