package hr.valecic.musicstorewebapp.publisher;

import hr.valecic.musicstorewebapp.event.CustomSpringEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomSpringEventPublisher {
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final String email) {
//        System.out.println("Publishing custom event. ");
        CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, email);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
