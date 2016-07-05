package com.example;

import com.sun.jndi.toolkit.url.Uri;

/**
 * Created by SPACE MARINE GENERAL on 1.7.2016.
 */
public class Uporabnik {
    private String ime;
    private String email;
    private String id;
    private String slikaUrl;

    public Uporabnik() {
        this.id = "";
        this.ime = "";
        this.email = "";
        this.slikaUrl = null;
    }

    public Uporabnik(String id, String ime, String email, String slikaUrl) {
        this.id = id;
        this.ime = ime;
        this.email = email;
        this.slikaUrl = slikaUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getSlikaUrl() {
        return slikaUrl;
    }

    public void setSlikaUrl(String slikaUrl) {
        this.slikaUrl = slikaUrl;
    }

    @Override
    public String toString() {
        return "Uporabnik{" +
                "id='" + id + '\'' +
                ", ime='" + ime + '\'' +
                ", email='" + email + '\'' +
                ", slikaUrl='" + slikaUrl + '\'' +
                '}';
    }
}
