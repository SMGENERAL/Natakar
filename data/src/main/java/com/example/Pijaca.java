package com.example;

/**
 * Created by SPACE MARINE GENERAL on 19.3.2016.
 */
public class Pijaca {
    private String Ime;
    private Double Cena;
    private String vrsta;

    public Pijaca(String ime, Double cena, String vrsta) {
        Ime = ime;
        Cena = cena;
        this.vrsta = vrsta;
    }

    public Pijaca() {
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public Double getCena() {
        return Cena;
    }

    public void setCena(Double cena) {
        Cena = cena;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    @Override
    public String toString() {
        return "Pijaca{" +
                "Ime='" + Ime + '\'' +
                ", Cena=" + Cena +
                ", vrsta='" + vrsta + '\'' +
                '}';
    }
}
