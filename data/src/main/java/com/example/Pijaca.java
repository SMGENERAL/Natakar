package com.example;

/**
 * Created by SPACE MARINE GENERAL on 19.3.2016.
 */
public class Pijaca {
    private String Ime;
    private Double Cena;

    public Pijaca(String ime, Double cena) {
        Ime = ime;
        Cena = cena;
    }

    public Pijaca() {
        this.Ime="Ni imena";
        this.Cena=0.0;
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

    @Override
    public String toString() {
        return "Pijaca{" +
                "Ime='" + Ime + '\'' +
                ", Cena=" + Cena +
                '}';
    }
}
