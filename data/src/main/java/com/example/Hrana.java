package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SPACE MARINE GENERAL on 19.3.2016.
 */
public class Hrana {
    private String Ime;
    private ArrayList<String> SeznamSestavin;
    private Double Cena;

    public Hrana() {
        this.Ime="Ni imena";
        this.SeznamSestavin=new ArrayList<String>();
        this.Cena=0.0;
    }

    public Hrana(String ime, ArrayList<String> sestavine, Double cena) {
        Ime = ime;
        this.SeznamSestavin=new ArrayList<String>();
        this.SeznamSestavin=sestavine;
        Cena = cena;
    }
    public Hrana(String ime, Double cena) {
        Ime = ime;
        this.SeznamSestavin=new ArrayList<String>();
        Cena = cena;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public ArrayList<String> getSeznamSestavin() {
        return SeznamSestavin;
    }

    public void setSeznamSestavin(ArrayList<String> seznamSestavin) {
        SeznamSestavin = seznamSestavin;
    }

    public Double getCena() {
        return Cena;
    }

    public void setCena(Double cena) {
        Cena = cena;
    }

    @Override
    public String toString() {
        return "Hrana{" +
                "Ime='" + Ime + '\'' +
                ", SeznamSestavin=" + SeznamSestavin +
                ", Cena=" + Cena +
                '}';
    }
}
