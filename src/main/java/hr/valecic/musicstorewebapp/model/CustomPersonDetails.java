package hr.valecic.musicstorewebapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomPersonDetails implements UserDetails {
    public CustomPersonDetails(Person person) {
        this.person = person;
    }

    private Person person;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return person.getAuthorities();
    }
    public String getRole() {
        return person.getRole().getRolename();
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getEmail();
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
}
