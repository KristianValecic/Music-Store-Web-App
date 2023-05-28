package hr.valecic.musicstorewebapp.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "purchase")
public class Purchase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idpurchase", nullable = false)
    private Long idpurchase;
    @Basic
    @Column(name = "timeofpurchase", nullable = false)
    private Timestamp timeofpurchase;
    @Basic
    @Column(name = "paymentmethod", nullable = false, length = 255)
    private String paymentmethod;
//    @Basic
//    @Column(name = "personid", nullable = false)
//    private Integer personid;
    @OneToMany(mappedBy = "purchaseByPurchaseid")
    private Collection<Itempurchase> itempurchasesByIdpurchase;
    @ManyToOne
    @JoinColumn(name = "personid", referencedColumnName = "idperson", nullable = false)
    private Person personid; //personByPersonid
    @OneToOne
    @JoinColumn(name = "shoppingcartid", referencedColumnName = "idcart", nullable = false)
    private Shoppingcart shoppingcartid;
//    @Basic
//    @Column(name = "personid")
//    private Long personid;

    public Long getIdpurchase() {
        return idpurchase;
    }

    public void setIdpurchase(Long idpurchase) {
        this.idpurchase = idpurchase;
    }

    public Timestamp getTimeofpurchase() {
        return timeofpurchase;
    }

    public void setTimeofpurchase(Timestamp timeofpurchase) {
        this.timeofpurchase = timeofpurchase;
    }

//    public Integer getPersonid() {
//        return personid;
//    }
//
//    public void setPersonid(Integer personid) {
//        this.personid = personid;
//    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase purchase = (Purchase) o;

        if (idpurchase != null ? !idpurchase.equals(purchase.idpurchase) : purchase.idpurchase != null) return false;
        if (timeofpurchase != null ? !timeofpurchase.equals(purchase.timeofpurchase) : purchase.timeofpurchase != null)
            return false;
        if (paymentmethod != null ? !paymentmethod.equals(purchase.paymentmethod) : purchase.paymentmethod != null)
            return false;
//        if (personid != null ? !personid.equals(purchase.personid) : purchase.personid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idpurchase != null ? idpurchase.hashCode() : 0;
        result = 31 * result + (timeofpurchase != null ? timeofpurchase.hashCode() : 0);
        result = 31 * result + (paymentmethod != null ? paymentmethod.hashCode() : 0);
//        result = 31 * result + (personid != null ? personid.hashCode() : 0);
        return result;
    }

    public Collection<Itempurchase> getItempurchasesByIdpurchase() {
        return itempurchasesByIdpurchase;
    }

    public void setItempurchasesByIdpurchase(Collection<Itempurchase> itempurchasesByIdpurchase) {
        this.itempurchasesByIdpurchase = itempurchasesByIdpurchase;
    }

//    public Person getPersonByPersonid() {
//        return personByPersonid;
//    }
//
//    public void setPersonByPersonid(Person personByPersonid) {
//        this.personByPersonid = personByPersonid;
//    }

    public Shoppingcart getShoppingcartid() {
        return shoppingcartid;
    }

    public void setShoppingcartid(Shoppingcart shoppingcartid) {
        this.shoppingcartid = shoppingcartid;
    }

    public Person getPersonid() {
        return personid;
    }

    public void setPersonid(Person personid) {
        this.personid = personid;
    }
}
