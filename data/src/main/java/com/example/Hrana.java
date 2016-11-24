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

    public boolean isOznacen() {
        return Oznacen;
    }

    public void setOznacen(boolean oznacen) {
        Oznacen = oznacen;
    }

    private boolean Oznacen;

    public Hrana() {
        this.Ime="Ni imena";
        this.SeznamSestavin=new ArrayList<String>();
        this.Cena=0.0;
        this.setOznacen(false);
    }

    public Hrana(String ime, ArrayList<String> sestavine, Double cena) {
        Ime = ime;
        this.SeznamSestavin=new ArrayList<String>();
        this.SeznamSestavin=sestavine;
        Cena = cena;
        this.setOznacen(false);
    }
    public Hrana(String ime, Double cena) {
        Ime = ime;
        this.SeznamSestavin=new ArrayList<String>();
        Cena = cena;
        this.setOznacen(false);
    }

    public Hrana vrniHrana() {
        Hrana tmp = new Hrana();
        tmp.Ime=this.getIme();
        tmp.SeznamSestavin=new ArrayList<String>();
        for (int i = 0; i < this.getSeznamSestavin().size(); i++) {
            tmp.getSeznamSestavin().add(this.getSeznamSestavin().get(i));
        }
        tmp.Cena=this.getCena();
        tmp.Oznacen=this.isOznacen();
        return tmp;
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
