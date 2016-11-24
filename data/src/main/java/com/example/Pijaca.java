package com.example;

import java.util.ArrayList;

/**
 * Created by SPACE MARINE GENERAL on 19.3.2016.
 */
public class Pijaca {
    private String Ime;
    private Double Cena;
    private boolean Oznacen;

    public boolean isOznacen() {
        return Oznacen;
    }

    public void setOznacen(boolean oznacen) {
        Oznacen = oznacen;
    }

    public Pijaca(String ime, Double cena) {
        Ime = ime;
        Cena = cena;
        this.setOznacen(false);
    }

    public Pijaca() {
        this.Ime="Ni imena";
        this.Cena=0.0;
        this.setOznacen(false);
    }

    public Pijaca vrniPijaca() {
        Pijaca tmp = new Pijaca();
        tmp.Ime=this.getIme();
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
