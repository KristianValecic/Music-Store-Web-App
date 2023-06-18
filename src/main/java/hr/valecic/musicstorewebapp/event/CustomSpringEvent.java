package hr.valecic.musicstorewebapp.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class CustomSpringEvent extends ApplicationEvent {
    private String email;

    public CustomSpringEvent(Object source, String email) {
        super(source);
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
