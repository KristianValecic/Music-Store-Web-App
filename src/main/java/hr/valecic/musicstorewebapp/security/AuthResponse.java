package hr.valecic.musicstorewebapp.security;

import lombok.Data;

@Data
public class AuthResponse {
    private String email;
    private String accessToken;

    public AuthResponse() { }
    public AuthResponse(String jwttoken) {
        this.accessToken = jwttoken;
    }

    public AuthResponse(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }
}
