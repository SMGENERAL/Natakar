package com.example;

import java.util.ArrayList;

/**
 * Created by SPACE MARINE GENERAL on 29.4.2016.
 */
public class Miza {
    private String ime;
    private ArrayList<Narocilo> SeznamNarocil;
    private Double SkupnaCena;

    public Miza() {
        this.SeznamNarocil=new ArrayList<Narocilo>();
        this.SkupnaCena=0.0;
    }

    public Miza(String ime, ArrayList<Narocilo> seznamNarocil) {
        this.ime = ime;
        SeznamNarocil = seznamNarocil;
        izracunajSkupnoCeno();
    }
    public Miza(String ime) {
        this.ime = ime;
        this.SeznamNarocil=new ArrayList<Narocilo>();
        izracunajSkupnoCeno();
    }

    public ArrayList<Narocilo> getSeznamNarocil() {
        return SeznamNarocil;
    }

    public void setSeznamNarocil(ArrayList<Narocilo> seznamNarocil) {
        SeznamNarocil = seznamNarocil;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void dodajNarocilo(Narocilo nov) {
        SeznamNarocil.add(nov);
        izracunajSkupnoCeno();
    }
    public void izbrisiNarocilo(int index) {
        SeznamNarocil.remove(index);
        izracunajSkupnoCeno();
    }
    public void izracunajSkupnoCeno() {
        this.SkupnaCena=0.0;
        for (int i = 0; i < SeznamNarocil.size(); i++) {
            this.SkupnaCena = this.SkupnaCena + SeznamNarocil.get(i).getSkupnaCena();
        }
    }

    @Override
    public String toString() {
        return "Miza{" +
                "ime='" + ime + '\'' +
                ", SeznamNarocil=" + SeznamNarocil +
                ", SkupnaCena=" + SkupnaCena +
                '}';
    }

    public Double getSkupnaCena() {
        return SkupnaCena;
    }

    public void setSkupnaCena(Double skupnaCena) {
        SkupnaCena = skupnaCena;
    }
}
