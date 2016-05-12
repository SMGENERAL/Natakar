package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SPACE MARINE GENERAL on 19.3.2016.
 */
public class Hrana {
    private String Ime;
    private ArrayList<String> SeznamSestavine;
    private Double Cena;
    private String Vrsta;

    public Hrana(String ime, ArrayList<String> sestavine, Double cena, String vrsta) {
        Ime = ime;
        this.SeznamSestavine=new ArrayList<String>();
        this.SeznamSestavine.addAll(sestavine);
        //SeznamSestavine = sestavine;
        Cena = cena;
        Vrsta = vrsta;
    }
    public Hrana(String ime, Double cena, String vrsta) {
        Ime = ime;
        this.SeznamSestavine=new ArrayList<String>();
        Cena = cena;
        Vrsta = vrsta;
    }

    public Hrana() {
        this.SeznamSestavine=new ArrayList<String>();
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public List<String> getSestavine() {
        return SeznamSestavine;
    }

    public void setSestavine(ArrayList<String> sestavine) {
        SeznamSestavine = sestavine;
    }

    public Double getCena() {
        return Cena;
    }

    public void setCena(Double cena) {
        Cena = cena;
    }

    public String getVrsta() {
        return Vrsta;
    }

    public void setVrsta(String vrsta) {
        Vrsta = vrsta;
    }

    public void dodajSestavino(String nova) {
        SeznamSestavine.add(nova);
    }

    public void izbrisiSestavino(int index) {
        SeznamSestavine.remove(index);
    }

    @Override
    public String toString() {
        return "Hrana{" +
                "Ime='" + Ime + '\'' +
                ", Sestavine=" + SeznamSestavine +
                ", Cena=" + Cena +
                ", Vrsta='" + Vrsta + '\'' +
                '}';
    }
}
