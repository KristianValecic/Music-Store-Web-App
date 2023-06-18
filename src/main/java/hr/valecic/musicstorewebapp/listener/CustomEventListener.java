package hr.valecic.musicstorewebapp.listener;

import hr.valecic.musicstorewebapp.controller.RegisterController;
import hr.valecic.musicstorewebapp.event.CustomSpringEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomEventListener implements ApplicationListener<CustomSpringEvent> {
    private RegisterController registerController;
    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
//        System.out.println("Received spring custom event - " + event.getMessage());
//        ideja: napravi unos u bazu da je registriran

        if (event.getEmail().equals("ad@min.com")){
            throw new BadCredentialsException("Forbidden e mail adress.");
        }
    }
}