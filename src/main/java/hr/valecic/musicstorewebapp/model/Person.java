package hr.valecic.musicstorewebapp.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "person")
public class Person implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idperson", nullable = false)
    private Long idperson;
    @Basic
    @Column(name = "firstname", nullable = false, length = 255)
    private String firstname;
    @Basic
    @Column(name = "lastname", nullable = false, length = 255)
    private String lastname;
    @Basic
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "adress", nullable = false, length = 255)
    private String adress;
    @Basic
    @Column(name = "password", nullable = false, length = -1)
    private String password;
//    @Basic
//    @Column(name = "roleid", nullable = false)
//    private Integer roleid;
    @ManyToOne
    @JoinColumn(name = "roleid", referencedColumnName = "idrole", nullable = false)
    private Roles role;

    public Long getIdperson() {
        return idperson;
    }

    public void setIdperson(Long idperson) {
        this.idperson = idperson;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; //TODO: change to getting authorities
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (idperson != null ? !idperson.equals(person.idperson) : person.idperson != null) return false;
        if (firstname != null ? !firstname.equals(person.firstname) : person.firstname != null) return false;
        if (lastname != null ? !lastname.equals(person.lastname) : person.lastname != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (adress != null ? !adress.equals(person.adress) : person.adress != null) return false;
        if (password != null ? !password.equals(person.password) : person.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idperson != null ? idperson.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

//    public Integer getRoleid() {
//        return roleid;
//    }
//
//    public void setRoleid(Integer roleid) {
//        this.roleid = roleid;
//    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
