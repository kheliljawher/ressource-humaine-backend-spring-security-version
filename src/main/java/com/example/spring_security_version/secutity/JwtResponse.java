package com.example.spring_security_version.secutity;

import com.example.spring_security_version.entity.Utilisateur;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtResponse {

    private String token;

    private String refreshtToken;

    private UserDetails userDetails;

    private Utilisateur utilisateur;


    public JwtResponse(String token, String refreshtToken, Utilisateur utilisateur) {
        this.token = token;
        this.refreshtToken = refreshtToken;
        this.utilisateur = utilisateur;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshtToken() {
        return refreshtToken;
    }


    public void setRefreshtToken(String refreshtToken) {
        this.refreshtToken = refreshtToken;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
