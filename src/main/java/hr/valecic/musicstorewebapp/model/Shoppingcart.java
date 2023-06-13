package hr.valecic.musicstorewebapp.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Shoppingcart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcart", nullable = false)
    private Long idcart;
    @Basic
    @Column(name = "creationtime", nullable = false)
    private Timestamp creationtime;
    @ManyToOne
    @JoinColumn(name = "personid", referencedColumnName = "idperson", nullable = false)
    private Person person;
    @OneToMany(mappedBy = "shoppingcartByShoppingcartid")
    private Collection<Shoppingcartitem> shoppingcartitemsByIdcart;
//    @Basic
//    @Column(name = "personid", nullable = false)
//    private Long personid;

    public Long getIdcart() {
        return idcart;
    }

    public void setIdcart(Long idcart) {
        this.idcart = idcart;
    }

    public Timestamp getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Timestamp creationtime) {
        this.creationtime = creationtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shoppingcart that = (Shoppingcart) o;

        if (idcart != null ? !idcart.equals(that.idcart) : that.idcart != null) return false;
        if (creationtime != null ? !creationtime.equals(that.creationtime) : that.creationtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcart != null ? idcart.hashCode() : 0;
        result = 31 * result + (creationtime != null ? creationtime.hashCode() : 0);
        return result;
    }

    public Person getPersonByPersonid() {
        return person;
    }

    public void setPersonByPersonid(Person personByPersonid) {
        this.person = personByPersonid;
    }

    public Collection<Shoppingcartitem> getShoppingcartitemsByIdcart() {
        return shoppingcartitemsByIdcart;
    }

    public void setShoppingcartitemsByIdcart(Collection<Shoppingcartitem> shoppingcartitemsByIdcart) {
        this.shoppingcartitemsByIdcart = shoppingcartitemsByIdcart;
    }

//    public Long getPersonid() {
//        return personid;
//    }
//
//    public void setPersonid(Long personid) {
//        this.personid = personid;
//    }
}
