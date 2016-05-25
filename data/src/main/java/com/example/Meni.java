package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SPACE MARINE GENERAL on 19.3.2016.
 */
public class Meni {
    private String Ime;
    private ArrayList<Hrana> SeznamHrane;
    private ArrayList<Pijaca> SeznamPijace;
    private Double Cena;
    private boolean Oznacen;
    private int Kolicina;

    public Meni(String ime, ArrayList<Hrana> seznamHrane, ArrayList<Pijaca> seznamPijace) {
        Ime = ime;
        SeznamHrane = seznamHrane;
        SeznamPijace = seznamPijace;
        izracunajCeno();
        this.setOznacen(false);
        this.Kolicina=1;
    }

    public Meni(String ime) {
        Ime = ime;
        this.SeznamHrane=new ArrayList();
        this.SeznamPijace=new ArrayList();
        this.Cena=0.0;
        this.setOznacen(false);
        this.Kolicina=1;
    }

    public Meni() {
        this.SeznamHrane=new ArrayList();
        this.SeznamPijace=new ArrayList();
        this.Cena=0.0;
        this.setOznacen(false);
        this.Kolicina=1;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public List<Hrana> getSeznamHrane() {
        return SeznamHrane;
    }

    public void setSeznamHrane(ArrayList<Hrana> seznamHrane) {
        SeznamHrane = seznamHrane;
        izracunajCeno();
    }

    public List<Pijaca> getSeznamPijace() {
        return SeznamPijace;
    }

    public void setSeznamPijace(ArrayList<Pijaca> seznamPijace) {
        SeznamPijace = seznamPijace;
        izracunajCeno();
    }

    public Double getCena() {
        izracunajCeno();
        return Cena;
    }

    public void setCena(Double cena) {
        Cena = cena;
    }

    public void izracunajCeno() {
        this.Cena=0.0;
        for (int i = 0; i < SeznamHrane.size(); i++) {
            this.Cena=this.Cena+SeznamHrane.get(i).getCena();
        }
        for (int i = 0; i < SeznamPijace.size(); i++) {
            this.Cena=this.Cena+SeznamPijace.get(i).getCena();
        }
        this.Cena=this.Cena*this.Kolicina;
    }

    public boolean isOznacen() {
        return Oznacen;
    }

    public void setOznacen(boolean oznacen) {
        Oznacen = oznacen;
    }

    public int getKolicina() {
        return Kolicina;
    }

    public void setKolicina(int kolicina) {
        Kolicina = kolicina;
        izracunajCeno();
    }

    public void inc() {
        Kolicina++;
    }
    public void decr() {
        Kolicina--;
    }
    @Override
    public String toString() {
        return "Meni{" +
                "Ime='" + Ime + '\'' +
                ", SeznamHrane=" + SeznamHrane +
                ", SeznamPijace=" + SeznamPijace +
                ", Cena=" + Cena +
                ", Oznacen=" + Oznacen +
                ", Kolicina=" + Kolicina +
                '}';
    }


}
