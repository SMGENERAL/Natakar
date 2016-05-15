package com.example;

import java.util.ArrayList;

/**
 * Created by SPACE MARINE GENERAL on 29.4.2016.
 */
public class Miza {
    private String ime;
    private ArrayList<Meni> SeznamMenijev;
    private Double SkupnaCena;

    public Miza() {
        this.ime="Ni imena";
        this.SeznamMenijev=new ArrayList<Meni>();
        this.SkupnaCena=0.0;
    }

    public Miza(String ime, ArrayList<Meni> seznamMenijev) {
        this.ime = ime;
        this.SeznamMenijev = seznamMenijev;
        izracunajSkupnoCeno();
    }
    public Miza(String ime) {
        this.ime = ime;
        this.SeznamMenijev=new ArrayList<Meni>();
        this.SkupnaCena=0.0;
    }
    public void izracunajSkupnoCeno() {
        this.SkupnaCena=0.0;
        for (int i = 0; i < SeznamMenijev.size(); i++) {
            this.SkupnaCena = this.SkupnaCena + SeznamMenijev.get(i).getCena();
        }
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public ArrayList<Meni> getSeznamMenijev() {
        return SeznamMenijev;
    }

    public void setSeznamMenijev(ArrayList<Meni> seznamMenijev) {
        SeznamMenijev = seznamMenijev;
        this.izracunajSkupnoCeno();
    }

    public Double getSkupnaCena() {
        return SkupnaCena;
    }

    public void setSkupnaCena(Double skupnaCena) {
        SkupnaCena = skupnaCena;
    }

    @Override
    public String toString() {
        return "Miza{" +
                "ime='" + ime + '\'' +
                ", SeznamMenijev=" + SeznamMenijev +
                ", SkupnaCena=" + SkupnaCena +
                '}';
    }
}
