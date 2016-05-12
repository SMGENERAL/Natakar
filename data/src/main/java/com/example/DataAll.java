package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DataAll {

    private ArrayList<Miza> seznamVsehMiz;
    private ArrayList<Narocilo> seznamVsehNarocil;
    private ArrayList<Meni> seznamVsehMenijev;
    private ArrayList<Hrana> seznamVseHrane;
    private ArrayList<Pijaca> seznamVsehPijac;

    public DataAll() {
        this.seznamVsehMiz=new ArrayList<Miza>();
        this.seznamVsehNarocil=new ArrayList<Narocilo>();
        this.seznamVsehMenijev=new ArrayList<Meni>();
        this.seznamVseHrane=new ArrayList<Hrana>();
        this.seznamVsehPijac=new ArrayList<Pijaca>();
    }

    public DataAll(ArrayList<Miza> seznamVsehMiz, ArrayList<Narocilo> seznamVsehNarocil, ArrayList<Meni> seznamVsehMenijev, ArrayList<Hrana> seznamVseHrane, ArrayList<Pijaca> seznamVsehPijac) {
        this.seznamVsehMiz = seznamVsehMiz;
        this.seznamVsehNarocil = seznamVsehNarocil;
        this.seznamVsehMenijev = seznamVsehMenijev;
        this.seznamVseHrane = seznamVseHrane;
        this.seznamVsehPijac = seznamVsehPijac;
    }

    public void dodaj(Miza nov){
        seznamVsehMiz.add(nov);
    }
    public void dodaj(Narocilo nov){
        seznamVsehNarocil.add(nov);
    }
    public void dodaj(Meni nov){
        seznamVsehMenijev.add(nov);
    }
    public void dodaj(Hrana nov){
        seznamVseHrane.add(nov);
    }
    public void dodaj(Pijaca nov){seznamVsehPijac.add(nov);
    }

    public Miza getDolocenoMizo(int index) {
        return seznamVsehMiz.get(index);
    }

    public ArrayList<Miza> getSeznamVsehMiz() {
        return seznamVsehMiz;
    }

    public void setSeznamVsehMiz(ArrayList<Miza> seznamVsehMiz) {
        this.seznamVsehMiz = seznamVsehMiz;
    }

    public List<Narocilo> getSeznamVsehNarocil() {
        return seznamVsehNarocil;
    }

    public void setSeznamVsehNarocil(ArrayList<Narocilo> seznamVsehNarocil) {
        this.seznamVsehNarocil = seznamVsehNarocil;
    }

    public List<Meni> getSeznamVsehMenijev() {
        return seznamVsehMenijev;
    }

    public void setSeznamVsehMenijev(ArrayList<Meni> seznamVsehMenijev) {
        this.seznamVsehMenijev = seznamVsehMenijev;
    }

    public List<Hrana> getSeznamVseHrane() {
        return seznamVseHrane;
    }

    public void setSeznamVseHrane(ArrayList<Hrana> seznamVseHrane) {
        this.seznamVseHrane = seznamVseHrane;
    }

    public List<Pijaca> getSeznamVsehPijac() {
        return seznamVsehPijac;
    }

    public void setSeznamVsehPijac(ArrayList<Pijaca> seznamVsehPijac) {
        this.seznamVsehPijac = seznamVsehPijac;
    }

    public static DataAll getScenarijData() {
        DataAll vsipodatki = new DataAll();
        ArrayList<String> sestavine = new ArrayList<String>();

        /*
        //PIJACA
        vsipodatki.dodaj(new Pijaca("coca cola",1.8,"Gazirana"));
        vsipodatki.dodaj(new Pijaca("kava z mlekom",3.8,"Kofeinska"));
        vsipodatki.dodaj(new Pijaca("fanta",0.9,"Gazirana"));
        vsipodatki.dodaj(new Pijaca("ledeni caj",1.3,"Sok"));
        vsipodatki.dodaj(new Pijaca("red bull",2.0,"Energijska"));

        //HRANA
        //H1
        sestavine.add("sunka");
        sestavine.add("naco sir");
        sestavine.add("olive");
        vsipodatki.dodaj(new Hrana("pizza",sestavine,4.12,"Glavna jed"));
        //H2
        sestavine = new ArrayList<String>();
        sestavine.add("pecen krompir");
        sestavine.add("pleskavica");
        sestavine.add("ajvar");
        sestavine.add("jajce");
        vsipodatki.dodaj(new Hrana("pleskavica na zaru",sestavine,3.6,"Glavna jed"));
        //H3
        sestavine = new ArrayList<String>();
        sestavine.add("banana");
        sestavine.add("jagodni sladoled");
        sestavine.add("cokoladni sladoled");
        sestavine.add("sladka smetana");
        vsipodatki.dodaj(new Hrana("banana split",sestavine,1.2,"Sladica"));
        //H4
        sestavine = new ArrayList<String>();
        sestavine.add("korencek");
        sestavine.add("nudeljci");
        sestavine.add("cmoki");
        vsipodatki.dodaj(new Hrana("korenckova juha",sestavine,1.1,"Predjed"));
        */

        //MENIJI
        //MENI1
        Meni novMeni1=new Meni();
        novMeni1.setIme("Studentski meni 1");
        novMeni1.dodajPijaco(new Pijaca("Coca Cola",1.8,"Gazirana")); //P1
        sestavine = new ArrayList<String>();
        sestavine.add("korencek");
        sestavine.add("nudeljci");
        sestavine.add("cmoki");
        novMeni1.dodajHrano(new Hrana("Korenckova juha",sestavine,1.1,"Predjed")); //H1
        sestavine = new ArrayList<String>();
        sestavine.add("pecen krompir");
        sestavine.add("pleskavica");
        sestavine.add("ajvar");
        sestavine.add("jajce");
        novMeni1.dodajHrano(new Hrana("Pleskavica na zaru",sestavine,3.6,"Glavna jed")); //H2
        vsipodatki.dodaj(novMeni1);
        //MENI2
        Meni novMeni2=new Meni();
        novMeni2.setIme("Pizza mehicano combo");
        novMeni2.dodajPijaco(new Pijaca("Ledeni caj",1.3,"Sok")); //P1
        sestavine = new ArrayList<String>();
        sestavine.add("banana");
        sestavine.add("jagodni sladoled");
        sestavine.add("cokoladni sladoled");
        sestavine.add("sladka smetana");
        novMeni2.dodajHrano(new Hrana("Banana split",sestavine,1.2,"Sladica")); //H1
        sestavine = new ArrayList<String>();
        sestavine.add("sunka");
        sestavine.add("naco sir");
        sestavine.add("olive");
        novMeni2.dodajHrano(new Hrana("Pizza Mehiska",sestavine,4.2,"Glavna jed")); //H2
        vsipodatki.dodaj(novMeni2);
        //MENI3
        Meni novMeni3=new Meni();
        novMeni3.setIme("Red Bull Limona");
        novMeni3.dodajPijaco(new Pijaca("Red bull",2.0,"Energijska")); //P1
        vsipodatki.dodaj(novMeni3);
        //MENI4
        Meni novMeni4=new Meni();
        novMeni4.setIme("Francoska pojedina");
        novMeni4.dodajPijaco(new Pijaca("Rdece vino Bonjour",4.8,"alkoholna")); //P1
        novMeni4.dodajPijaco(new Pijaca("Voda",0.2,"default")); //P2
        sestavine = new ArrayList<String>();
        sestavine.add("morske skoljke");
        sestavine.add("rakovica");
        novMeni4.dodajHrano(new Hrana("Morske skoljke z kuhano rakovico",sestavine,7.3,"Glavna jed")); //H1
        sestavine = new ArrayList<String>();
        sestavine.add("olive");
        sestavine.add("kuhani polzi");
        sestavine.add("roglicek");
        novMeni4.dodajHrano(new Hrana("France speciale",sestavine,2.7,"Predjed")); //H2
        sestavine = new ArrayList<String>();
        sestavine.add("roglicek");
        sestavine.add("marelicna marmelada");
        novMeni4.dodajHrano(new Hrana("Francoski roglicek",sestavine,0.7,"Sladica")); //H3
        vsipodatki.dodaj(novMeni4);
        //MENI5
        Meni novMeni5=new Meni();
        novMeni5.setIme("Turska kava z mlekom");
        novMeni5.dodajPijaco(new Pijaca("Turska kava",1.9,"Kofeinska")); //P1
        novMeni5.dodajPijaco(new Pijaca("Polmasno mleko",0.4,"Mlecna")); //P2
        vsipodatki.dodaj(novMeni5);

        //MIZE
        vsipodatki.dodaj(new Miza("Miza 1 (pri vhodu)"));
        vsipodatki.dodaj(new Miza("Miza 2 (pri vhodu)"));
        vsipodatki.dodaj(new Miza("Miza 3 (pri vhodu)"));
        vsipodatki.dodaj(new Miza("Miza 4 (pri vhodu)"));
        vsipodatki.dodaj(new Miza("Miza 5 (pri vhodu)"));
        vsipodatki.dodaj(new Miza("Miza 6 (na terasi)"));
        vsipodatki.dodaj(new Miza("Miza 7 (na terasi)"));
        vsipodatki.dodaj(new Miza("Miza 8 (na terasi)"));
        vsipodatki.dodaj(new Miza("Miza 9 (na terasi)"));
        vsipodatki.dodaj(new Miza("Miza 10 (na terasi)"));
        vsipodatki.dodaj(new Miza("Miza 11 (na terasi)"));
        vsipodatki.dodaj(new Miza("Miza 12 (noter)"));
        vsipodatki.dodaj(new Miza("Miza 13 (noter)"));
        vsipodatki.dodaj(new Miza("Miza 14 (noter)"));
        vsipodatki.dodaj(new Miza("Miza 15 (noter)"));

        //NAROcILA
        //N1
        Narocilo N1=new Narocilo();
        N1.setIme("1");
        N1.dodajMeni(novMeni1);
        N1.dodajMeni(novMeni3);
        N1.izracunajSkupnoCeno();
        vsipodatki.dodaj(N1);
        //N2
        Narocilo N2=new Narocilo();
        N2.setIme("2");
        N2.dodajMeni(novMeni1);
        N2.dodajMeni(novMeni2);
        N2.izracunajSkupnoCeno();
        vsipodatki.dodaj(N2);
        //N3
        Narocilo N3=new Narocilo();
        N3.setIme("3");
        N3.dodajMeni(novMeni3);
        N3.izracunajSkupnoCeno();
        vsipodatki.dodaj(N3);
        //N4
        Narocilo N4=new Narocilo();
        N4.setIme("4");
        N4.dodajMeni(novMeni1);
        N4.dodajMeni(novMeni4);
        N4.dodajMeni(novMeni5);
        N4.izracunajSkupnoCeno();
        vsipodatki.dodaj(N4);
        //N5
        Narocilo N5=new Narocilo();
        N5.setIme("5");
        N5.dodajMeni(novMeni5);
        N5.izracunajSkupnoCeno();
        vsipodatki.dodaj(N5);
        //N6
        Narocilo N6=new Narocilo();
        N6.setIme("6");
        N6.dodajMeni(novMeni2);
        N6.izracunajSkupnoCeno();
        vsipodatki.dodaj(N6);
        //N7
        Narocilo N7=new Narocilo();
        N7.setIme("7");
        N7.dodajMeni(novMeni4);
        N7.dodajMeni(novMeni1);
        N7.izracunajSkupnoCeno();
        vsipodatki.dodaj(N7);
        //N8
        Narocilo N8=new Narocilo();
        N8.setIme("8");
        N8.dodajMeni(novMeni1);
        N8.dodajMeni(novMeni2);
        N8.dodajMeni(novMeni3);
        N8.dodajMeni(novMeni4);
        N8.dodajMeni(novMeni5);
        N8.izracunajSkupnoCeno();
        vsipodatki.dodaj(N8);

        //Dodajanje narocil v mize
        vsipodatki.seznamVsehMiz.get(0).dodajNarocilo(N1);
        vsipodatki.seznamVsehMiz.get(2).dodajNarocilo(N2);
        vsipodatki.seznamVsehMiz.get(5).dodajNarocilo(N4);
        vsipodatki.seznamVsehMiz.get(5).dodajNarocilo(N7);
        vsipodatki.seznamVsehMiz.get(5).dodajNarocilo(N2);
        vsipodatki.seznamVsehMiz.get(7).dodajNarocilo(N1);
        vsipodatki.seznamVsehMiz.get(10).dodajNarocilo(N3);
        vsipodatki.seznamVsehMiz.get(11).dodajNarocilo(N5);
        vsipodatki.seznamVsehMiz.get(13).dodajNarocilo(N8);
        vsipodatki.seznamVsehMiz.get(13).dodajNarocilo(N6);
        vsipodatki.seznamVsehMiz.get(13).dodajNarocilo(N7);
        vsipodatki.seznamVsehMiz.get(13).dodajNarocilo(N8);

        return vsipodatki;
    }


    @Override
    public String toString() {
        return "DataAll{" +
                "seznamVsehMiz=" + seznamVsehMiz +
                ", seznamVsehNarocil=" + seznamVsehNarocil +
                ", seznamVsehMenijev=" + seznamVsehMenijev +
                ", seznamVseHrane=" + seznamVseHrane +
                ", seznamVsehPijac=" + seznamVsehPijac +
                '}';
    }
}
