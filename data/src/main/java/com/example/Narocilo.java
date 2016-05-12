package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SPACE MARINE GENERAL on 19.3.2016.
 */
public class Narocilo {
    private String Ime;
    private ArrayList<Meni> SeznamMenijev;
    private Double SkupnaCena;
    private int zaizbris;

    public Narocilo(String ime, ArrayList<Meni> seznamMenijev) {
        Ime = ime;
        SeznamMenijev = seznamMenijev;
        izracunajSkupnoCeno();
    }

    public Narocilo() {
        this.SeznamMenijev=new ArrayList<Meni>();
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public List<Meni> getSeznamMenijev() {
        return SeznamMenijev;
    }

    public void setSeznamMenijev(ArrayList<Meni> seznamMenijev) {
        SeznamMenijev = seznamMenijev;
    }

    public Double getSkupnaCena() {
        izracunajSkupnoCeno();
        return SkupnaCena;
    }

    public void setSkupnaCena(Double skupnaCena) {
        SkupnaCena = skupnaCena;
    }

    public void dodajMeni(Meni nov) {
        SeznamMenijev.add(nov);
        izracunajSkupnoCeno();
    }
    public void izbrisiMeni(int index) {
        SeznamMenijev.remove(index);
        izracunajSkupnoCeno();
    }
    public void izracunajSkupnoCeno() {
        this.SkupnaCena=0.0;
        for (int i = 0; i < SeznamMenijev.size(); i++) {
            this.SkupnaCena = this.SkupnaCena + SeznamMenijev.get(i).getCena();
        }
    }

    @Override
    public String toString() {
        return "Narocilo{" +
                "Ime='" + Ime + '\'' +
                ", SeznamMenijev=" + SeznamMenijev +
                ", SkupnaCena=" + SkupnaCena +
                '}';
    }
}
