package hr.valecic.musicstorewebapp.annotation.validation;

import hr.valecic.musicstorewebapp.annotation.PasswordMatches;
import hr.valecic.musicstorewebapp.model.dto.PersonDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        PersonDTO user = (PersonDTO) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
