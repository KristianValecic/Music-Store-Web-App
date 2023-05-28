package hr.valecic.musicstorewebapp.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "shoppingcart")
public class Shoppingcart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcart", nullable = false)
    private Long idcart;
    @Basic
    @Column(name = "creationtime")
    private Timestamp creationtime;
    @OneToOne
    @JoinColumn(name = "personid", referencedColumnName = "idperson", nullable = false)
    private Person personid;
    @OneToMany(mappedBy = "shoppingcartid")
    private Collection<Shoppingcartitem> cartitemid;

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

    public Person getPersonid() {
        return personid;
    }

    public void setPersonid(Person person) {
        this.personid = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shoppingcart that = (Shoppingcart) o;

        if (idcart != null ? !idcart.equals(that.idcart) : that.idcart != null) return false;
        if (creationtime != null ? !creationtime.equals(that.creationtime) : that.creationtime != null) return false;
        if (personid != null ? !personid.equals(that.personid) : that.personid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcart != null ? idcart.hashCode() : 0;
        result = 31 * result + (creationtime != null ? creationtime.hashCode() : 0);
        result = 31 * result + (personid != null ? personid.hashCode() : 0);
        return result;
    }
}
