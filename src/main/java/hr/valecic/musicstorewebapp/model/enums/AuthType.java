package hr.valecic.musicstorewebapp.model.enums;

public enum AuthType {
    ANONYMOUS_USER("anonymousUser"),
    ROLE_ANONYMOUS("ROLE_ANONYMOUS"),
    ADMIN("ad@min.com");

    public final String value;

    private AuthType(String value) {
        this.value = value;
    }
}
