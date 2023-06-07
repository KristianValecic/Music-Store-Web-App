package hr.valecic.musicstorewebapp.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "roles")
public class Roles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idrole", nullable = false)
    private Integer idrole;
    @Basic
    @Column(name = "rolename", nullable = false, length = 255)
    private String rolename;
    @OneToMany(mappedBy = "role")
    private Collection<Person> peopleByIdrole;

    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Roles roles = (Roles) o;

        if (idrole != null ? !idrole.equals(roles.idrole) : roles.idrole != null) return false;
        if (rolename != null ? !rolename.equals(roles.rolename) : roles.rolename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idrole != null ? idrole.hashCode() : 0;
        result = 31 * result + (rolename != null ? rolename.hashCode() : 0);
        return result;
    }

    public Collection<Person> getPeopleByIdrole() {
        return peopleByIdrole;
    }

    public void setPeopleByIdrole(Collection<Person> peopleByIdrole) {
        this.peopleByIdrole = peopleByIdrole;
    }
}
