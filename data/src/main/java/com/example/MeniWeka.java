package com.example;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by SPACE MARINE GENERAL on 7.1.2017.
 */

public class MeniWeka {
    private String Ime;
    private ArrayList<Hrana> SeznamHrane;
    private ArrayList<Pijaca> SeznamPijace;
    private Double Cena;

    private int Dan;
    private int Cas;
    private int LetniCas;
    private int Miza;
    private int Kolicina;
    private String Popularnost;



    public MeniWeka() {
        Ime="";
        SeznamHrane=new ArrayList<>();
        SeznamPijace=new ArrayList<>();
        Cena=0.0;
        Dan=1;
        Cas=1;
        LetniCas=1;
        Miza=1;
        Kolicina=1;
        Popularnost="najmanj";
    }

    public MeniWeka(Meni meni) {
        Ime=meni.getIme();
        SeznamHrane=meni.getSeznamHrane();
        SeznamPijace=meni.getSeznamPijace();
        Cena=meni.getCena();

        Random rnd = new Random();
        Dan= rnd.nextInt(7 - 1) + 1;
        Cas=rnd.nextInt(23 - 1) + 1;
        LetniCas=rnd.nextInt(4 - 1) + 1;
        Miza=rnd.nextInt(10 - 1) + 1;
        Kolicina=rnd.nextInt(10 - 1) + 1;
        Popularnost="najmanj";
    }

    public MeniWeka vrniMeniWeka() {
        MeniWeka tmp = new MeniWeka();
        tmp.Ime=this.getIme();
        tmp.SeznamHrane=new ArrayList<Hrana>();
        for (int i = 0; i < this.getSeznamHrane().size(); i++) {
            tmp.getSeznamHrane().add(this.getSeznamHrane().get(i));
        }
        tmp.SeznamPijace=new ArrayList<Pijaca>();
        for (int i = 0; i < this.getSeznamPijace().size(); i++) {
            tmp.getSeznamPijace().add(this.getSeznamPijace().get(i));
        }
        tmp.Cena=this.getCena();
        tmp.Dan=this.getDan();
        tmp.Cas=this.getCas();
        tmp.LetniCas=this.getLetniCas();
        tmp.Miza=this.getMiza();
        tmp.Kolicina=this.getKolicina();
        tmp.Popularnost=this.getPopularnost();
        return tmp;
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

    public int getKolicina() {
        return Kolicina;
    }

    public void setKolicina(int kolicina) {
        Kolicina = kolicina;
        izracunajCeno();
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public ArrayList<Hrana> getSeznamHrane() {
        return SeznamHrane;
    }

    public void setSeznamHrane(ArrayList<Hrana> seznamHrane) {
        SeznamHrane = seznamHrane;
    }

    public ArrayList<Pijaca> getSeznamPijace() {
        return SeznamPijace;
    }

    public void setSeznamPijace(ArrayList<Pijaca> seznamPijace) {
        SeznamPijace = seznamPijace;
    }

    public Double getCena() {
        return Cena;
    }

    public void setCena(Double cena) {
        Cena = cena;
    }

    public int getDan() {
        return Dan;
    }

    public void setDan(int dan) {
        Dan = dan;
    }

    public int getCas() {
        return Cas;
    }

    public void setCas(int cas) {
        Cas = cas;
    }

    public int getLetniCas() {
        return LetniCas;
    }

    public void setLetniCas(int letniCas) {
        LetniCas = letniCas;
    }

    public int getMiza() {
        return Miza;
    }

    public void setMiza(int miza) {
        Miza = miza;
    }

    public String getPopularnost() {
        return Popularnost;
    }

    public void setPopularnost(String popularnost) {
        Popularnost = popularnost;
    }

    @Override
    public String toString() {
        return "MeniWeka{" +
                "Ime='" + Ime + '\'' +
                ", SeznamHrane=" + SeznamHrane +
                ", SeznamPijace=" + SeznamPijace +
                ", Cena=" + Cena +
                ", Dan=" + Dan +
                ", Cas=" + Cas +
                ", LetniCas=" + LetniCas +
                ", Miza=" + Miza +
                ", Kolicina=" + Kolicina +
                ", Popularnost=" + Popularnost +
                '}';
    }
}
