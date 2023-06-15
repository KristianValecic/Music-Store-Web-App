package hr.valecic.musicstorewebapp.security;

import java.io.IOException;

import hr.valecic.musicstorewebapp.dal.service.LoginhistoryService;
import hr.valecic.musicstorewebapp.model.Loginhistory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component
@AllArgsConstructor
public class LoginFilter extends OncePerRequestFilter {

    private LoginhistoryService loginhistoryService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // User is already authenticated, no need to execute filter logic
            chain.doFilter(request, response);
            return;
        }

        insertLoginToDb();

//        chain.doFilter(request, response);
    }

    private void insertLoginToDb() {
        Loginhistory loginhistoryEntry = new Loginhistory();
        System.out.println("insertLoginToDb");
//        loginhistoryEntry.setPersonByPersonid();
//        loginhistoryEntry.setIpadress();
//        loginhistoryEntry.setTimeoflogin();

    }

}
