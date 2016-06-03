package com.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by SPACE MARINE GENERAL on 29.4.2016.
 */
public class Miza {
    private String ime;
    private ArrayList<Meni> SeznamMenijev;
    private Double SkupnaCena;
    private int Lokacija;
    private int Id;

    public Miza() {
        this.ime="Ni imena";
        this.SeznamMenijev=new ArrayList<Meni>();
        this.SkupnaCena=0.0;
        this.Lokacija=100;
        this.Id=0;
    }

    public Miza(String ime, ArrayList<Meni> seznamMenijev) {
        this.ime = ime;
        this.SeznamMenijev = seznamMenijev;
        izracunajSkupnoCeno();
        this.Lokacija=100;
        this.Id=0;
    }
    public Miza(String ime) {
        this.ime = ime;
        this.SeznamMenijev=new ArrayList<Meni>();
        this.SkupnaCena=0.0;
        this.Lokacija=100;
        this.Id=0;
    }

    public Miza(Miza druga) {
        this.ime = druga.getIme();
        this.SeznamMenijev=druga.getSeznamMenijev();
        this.SkupnaCena=druga.getSkupnaCena();
        this.Lokacija=druga.getLokacija();
        this.Id=druga.getId();
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
        izracunajSkupnoCeno();
        return SkupnaCena;
    }

    public void setSkupnaCena(Double skupnaCena) {
        SkupnaCena = skupnaCena;
    }

    public int getLokacija() {
        return Lokacija;
    }

    public void setLokacija(int lokacija) {
        Lokacija = lokacija;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Miza{" +
                "ime='" + ime + '\'' +
                ", SeznamMenijev=" + SeznamMenijev +
                ", SkupnaCena=" + SkupnaCena +
                ", Lokacija=" + Lokacija +
                ", Id=" + Id +
                '}';
    }
}
