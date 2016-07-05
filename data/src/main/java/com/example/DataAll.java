package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DataAll {
   transient public boolean ch;
    private int IDnavrsti;
    private Uporabnik uporabnik;
    private ArrayList<Miza> seznamVsehMiz;
    private ArrayList<Meni> seznamVsehMenijev;
    private ArrayList<Hrana> seznamVseHrane;
    private ArrayList<Pijaca> seznamVsehPijac;
    private ArrayList<Miza> seznamZgodovina;

    public void change(){
    ch=true;
}
    public void saved(){
        ch=false;
    }

    public DataAll() {
        ch=false;
        this.uporabnik=new Uporabnik();
        this.seznamVsehMiz=new ArrayList<Miza>();
        this.seznamVsehMenijev=new ArrayList<Meni>();
        this.seznamVseHrane=new ArrayList<Hrana>();
        this.seznamVsehPijac=new ArrayList<Pijaca>();
        this.seznamZgodovina=new ArrayList<Miza>();
        this.IDnavrsti=1;
    }

    public DataAll(ArrayList<Miza> seznamVsehMiz, ArrayList<Meni> seznamVsehMenijev, ArrayList<Hrana> seznamVseHrane, ArrayList<Pijaca> seznamVsehPijac) {
        ch=false;
        this.uporabnik=new Uporabnik();
        this.seznamVsehMiz = seznamVsehMiz;
        this.seznamVsehMenijev = seznamVsehMenijev;
        this.seznamVseHrane = seznamVseHrane;
        this.seznamVsehPijac = seznamVsehPijac;
        this.seznamZgodovina=new ArrayList<Miza>();
        this.IDnavrsti=1;
    }

    public ArrayList<Miza> getSeznamVsehMiz() {
        return seznamVsehMiz;
    }

    public void setSeznamVsehMiz(ArrayList<Miza> seznamVsehMiz) {
        this.seznamVsehMiz = seznamVsehMiz;
    }

    public ArrayList<Meni> getSeznamVsehMenijev() {
        return seznamVsehMenijev;
    }

    public void setSeznamVsehMenijev(ArrayList<Meni> seznamVsehMenijev) {
        this.seznamVsehMenijev = seznamVsehMenijev;
    }

    public ArrayList<Hrana> getSeznamVseHrane() {
        return seznamVseHrane;
    }

    public void setSeznamVseHrane(ArrayList<Hrana> seznamVseHrane) {
        this.seznamVseHrane = seznamVseHrane;
    }

    public ArrayList<Pijaca> getSeznamVsehPijac() {
        return seznamVsehPijac;
    }

    public void setSeznamVsehPijac(ArrayList<Pijaca> seznamVsehPijac) {
        this.seznamVsehPijac = seznamVsehPijac;
    }

    public static DataAll getScenarijData() {
        DataAll vsipodatki = new DataAll();

        //PIJACA
        vsipodatki.getSeznamVsehPijac().add(new Pijaca("Coca Cola",1.0));
        vsipodatki.getSeznamVsehPijac().add(new Pijaca("Fanta",1.1));
        vsipodatki.getSeznamVsehPijac().add(new Pijaca("Kava z mlekom",1.8));
        vsipodatki.getSeznamVsehPijac().add(new Pijaca("Red Bull",2.0));
        vsipodatki.getSeznamVsehPijac().add(new Pijaca("Pomarancni sok",0.5));

        //HRANA
        vsipodatki.getSeznamVseHrane().add(new Hrana("Pizza margarita",3.5));
        vsipodatki.getSeznamVseHrane().get(0).getSeznamSestavin().add("sir");
        vsipodatki.getSeznamVseHrane().get(0).getSeznamSestavin().add("olive");
        vsipodatki.getSeznamVseHrane().get(0).getSeznamSestavin().add("gobe");

        vsipodatki.getSeznamVseHrane().add(new Hrana("Pizza mleto meso",4.2));
        vsipodatki.getSeznamVseHrane().get(1).getSeznamSestavin().add("mleto meso");
        vsipodatki.getSeznamVseHrane().get(1).getSeznamSestavin().add("paprika");
        vsipodatki.getSeznamVseHrane().get(1).getSeznamSestavin().add("cebula");
        vsipodatki.getSeznamVseHrane().get(1).getSeznamSestavin().add("olive");
        vsipodatki.getSeznamVseHrane().get(1).getSeznamSestavin().add("naco sir");

        vsipodatki.getSeznamVseHrane().add(new Hrana("Banana split",1.5));
        vsipodatki.getSeznamVseHrane().get(2).getSeznamSestavin().add("banana");
        vsipodatki.getSeznamVseHrane().get(2).getSeznamSestavin().add("sladoled");

        vsipodatki.getSeznamVseHrane().add(new Hrana("Dunajski zrezek",4.9));
        vsipodatki.getSeznamVseHrane().get(3).getSeznamSestavin().add("dunajski zrezek");
        vsipodatki.getSeznamVseHrane().get(3).getSeznamSestavin().add("pecen krompir");
        vsipodatki.getSeznamVseHrane().get(3).getSeznamSestavin().add("zelenjavna podloga");

        vsipodatki.getSeznamVseHrane().add(new Hrana("Korenckova juha",2.1));
        vsipodatki.getSeznamVseHrane().get(4).getSeznamSestavin().add("korencek");

        //MENI
        vsipodatki.getSeznamVsehMenijev().add(new Meni("Student"));
        vsipodatki.getSeznamVsehMenijev().get(0).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(0));
        vsipodatki.getSeznamVsehMenijev().get(0).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(2));
        vsipodatki.getSeznamVsehMenijev().get(0).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(0));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("Pizza Combo"));
        vsipodatki.getSeznamVsehMenijev().get(1).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(1));
        vsipodatki.getSeznamVsehMenijev().get(1).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(3));
        vsipodatki.getSeznamVsehMenijev().get(1).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(1));
        vsipodatki.getSeznamVsehMenijev().get(1).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(2));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("Druzinski"));
        vsipodatki.getSeznamVsehMenijev().get(2).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(0));
        vsipodatki.getSeznamVsehMenijev().get(2).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(3));
        vsipodatki.getSeznamVsehMenijev().get(2).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(4));
        vsipodatki.getSeznamVsehMenijev().get(2).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(1));
        vsipodatki.getSeznamVsehMenijev().get(2).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(2));
        vsipodatki.getSeznamVsehMenijev().get(2).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(4));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("Osnovno"));
        vsipodatki.getSeznamVsehMenijev().get(3).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(4));
        vsipodatki.getSeznamVsehMenijev().get(3).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(4));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("Malica"));
        vsipodatki.getSeznamVsehMenijev().get(4).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(0));
        vsipodatki.getSeznamVsehMenijev().get(4).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(3));
        vsipodatki.getSeznamVsehMenijev().get(4).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(4));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("Par Mix"));
        vsipodatki.getSeznamVsehMenijev().get(5).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(1));
        vsipodatki.getSeznamVsehMenijev().get(5).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(2));
        vsipodatki.getSeznamVsehMenijev().get(5).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(3));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("Lacen"));
        vsipodatki.getSeznamVsehMenijev().get(6).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(2));
        vsipodatki.getSeznamVsehMenijev().get(6).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(3));
        vsipodatki.getSeznamVsehMenijev().get(6).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(4));
        vsipodatki.getSeznamVsehMenijev().get(6).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(3));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("Mesni"));
        vsipodatki.getSeznamVsehMenijev().get(7).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(3));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("Posladek"));
        vsipodatki.getSeznamVsehMenijev().get(8).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(2));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("Vegi"));
        vsipodatki.getSeznamVsehMenijev().get(9).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(0));
        vsipodatki.getSeznamVsehMenijev().get(9).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(0));

        //MIZE / NAROČILA
        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 1 (VIP)"));
        vsipodatki.getSeznamVsehMiz().get(0).setLokacija(0);
        //vsipodatki.getSeznamVsehMiz().get(0).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(0));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 2"));
        vsipodatki.getSeznamVsehMiz().get(1).setLokacija(2);
        //vsipodatki.getSeznamVsehMiz().get(1).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(1));
        //vsipodatki.getSeznamVsehMiz().get(1).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(4));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 3 (terasa)"));
        vsipodatki.getSeznamVsehMiz().get(2).setLokacija(5);

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 4"));
        vsipodatki.getSeznamVsehMiz().get(3).setLokacija(8);
        //vsipodatki.getSeznamVsehMiz().get(3).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(6));
        //vsipodatki.getSeznamVsehMiz().get(3).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(7));
        //getSeznamVsehMiz().get(3).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(9));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 5 (dvojna)"));
        vsipodatki.getSeznamVsehMiz().get(4).setLokacija(12);
        //vsipodatki.getSeznamVsehMiz().get(4).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(2));
        //vsipodatki.getSeznamVsehMiz().get(4).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(4));
        //vsipodatki.getSeznamVsehMiz().get(4).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(6));
        //vsipodatki.getSeznamVsehMiz().get(4).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(7));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 6"));
        vsipodatki.getSeznamVsehMiz().get(5).setLokacija(17);

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 7"));
        vsipodatki.getSeznamVsehMiz().get(6).setLokacija(18);

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 8 (terasa)"));
        vsipodatki.getSeznamVsehMiz().get(7).setLokacija(43);
        //vsipodatki.getSeznamVsehMiz().get(7).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(2));
        //vsipodatki.getSeznamVsehMiz().get(7).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(5));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 9 (terasa)"));
        vsipodatki.getSeznamVsehMiz().get(8).setLokacija(47);
        //vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(1));
        //vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(3));
        //vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(5));
        //vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(7));
        //vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(8));
        //vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(9));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 10 (dvojna)"));
        vsipodatki.getSeznamVsehMiz().get(9).setLokacija(84);

        return vsipodatki;
    }

    public ArrayList<Miza> getSeznamZgodovina() {
        return seznamZgodovina;
    }

    public void setSeznamZgodovina(ArrayList<Miza> seznamZgodovina) {
        this.seznamZgodovina = seznamZgodovina;
    }

    public int getIDnavrsti() {
        return IDnavrsti;
    }

    public void setIDnavrsti(int IDnavrsti) {
        this.IDnavrsti = IDnavrsti;
    }

    public void inc() {
        this.IDnavrsti++;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

    @Override
    public String toString() {
        return "DataAll{" +
                "IDnavrsti=" + IDnavrsti +
                ", uporabnik=" + uporabnik +
                ", ch=" + ch +
                ", seznamVsehMiz=" + seznamVsehMiz +
                ", seznamVsehMenijev=" + seznamVsehMenijev +
                ", seznamZgodovina=" + seznamZgodovina +
                ", seznamVseHrane=" + seznamVseHrane +
                ", seznamVsehPijac=" + seznamVsehPijac +
                '}';
    }
}
