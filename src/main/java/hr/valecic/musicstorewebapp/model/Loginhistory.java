package hr.valecic.musicstorewebapp.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "loginhistory")
public class Loginhistory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idloginhistory", nullable = false)
    private Long idloginhistory;
    @Basic
    @Column(name = "ipadress", nullable = false, length = 255)
    private String ipadress;
    @Basic
    @Column(name = "timeoflogin", nullable = false)
    private Timestamp timeoflogin;
    @Basic
    @Column(name = "personid", nullable = false)
    private Integer personid;

    public Long getIdloginhistory() {
        return idloginhistory;
    }

    public void setIdloginhistory(Long idloginhistory) {
        this.idloginhistory = idloginhistory;
    }

    public String getIpadress() {
        return ipadress;
    }

    public void setIpadress(String ipadress) {
        this.ipadress = ipadress;
    }

    public Timestamp getTimeoflogin() {
        return timeoflogin;
    }

    public void setTimeoflogin(Timestamp timeoflogin) {
        this.timeoflogin = timeoflogin;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loginhistory that = (Loginhistory) o;

        if (idloginhistory != null ? !idloginhistory.equals(that.idloginhistory) : that.idloginhistory != null)
            return false;
        if (ipadress != null ? !ipadress.equals(that.ipadress) : that.ipadress != null) return false;
        if (timeoflogin != null ? !timeoflogin.equals(that.timeoflogin) : that.timeoflogin != null) return false;
        if (personid != null ? !personid.equals(that.personid) : that.personid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idloginhistory != null ? idloginhistory.hashCode() : 0;
        result = 31 * result + (ipadress != null ? ipadress.hashCode() : 0);
        result = 31 * result + (timeoflogin != null ? timeoflogin.hashCode() : 0);
        result = 31 * result + (personid != null ? personid.hashCode() : 0);
        return result;
    }
}
