package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DataAll {

    private ArrayList<Miza> seznamVsehMiz;
    private ArrayList<Meni> seznamVsehMenijev;
    private ArrayList<Hrana> seznamVseHrane;
    private ArrayList<Pijaca> seznamVsehPijac;

    public DataAll() {
        this.seznamVsehMiz=new ArrayList<Miza>();
        this.seznamVsehMenijev=new ArrayList<Meni>();
        this.seznamVseHrane=new ArrayList<Hrana>();
        this.seznamVsehPijac=new ArrayList<Pijaca>();
    }

    public DataAll(ArrayList<Miza> seznamVsehMiz, ArrayList<Meni> seznamVsehMenijev, ArrayList<Hrana> seznamVseHrane, ArrayList<Pijaca> seznamVsehPijac) {
        this.seznamVsehMiz = seznamVsehMiz;
        this.seznamVsehMenijev = seznamVsehMenijev;
        this.seznamVseHrane = seznamVseHrane;
        this.seznamVsehPijac = seznamVsehPijac;
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

    @Override
    public String toString() {
        return "DataAll{" +
                "seznamVsehMiz=" + seznamVsehMiz +
                ", seznamVsehMenijev=" + seznamVsehMenijev +
                ", seznamVseHrane=" + seznamVseHrane +
                ", seznamVsehPijac=" + seznamVsehPijac +
                '}';
    }

    public static DataAll getScenarijData() {
        DataAll vsipodatki = new DataAll();

        //PIJACA
        vsipodatki.getSeznamVsehPijac().add(new Pijaca("P1",1.2));
        vsipodatki.getSeznamVsehPijac().add(new Pijaca("P2",0.7));
        vsipodatki.getSeznamVsehPijac().add(new Pijaca("P3",1.6));
        vsipodatki.getSeznamVsehPijac().add(new Pijaca("P4",2.5));
        vsipodatki.getSeznamVsehPijac().add(new Pijaca("P5",1.3));

        //HRANA
        vsipodatki.getSeznamVseHrane().add(new Hrana("H1",2.3));
        vsipodatki.getSeznamVseHrane().get(0).getSeznamSestavin().add("S1");
        vsipodatki.getSeznamVseHrane().get(0).getSeznamSestavin().add("S2");
        vsipodatki.getSeznamVseHrane().get(0).getSeznamSestavin().add("S3");

        vsipodatki.getSeznamVseHrane().add(new Hrana("H2",4.2));
        vsipodatki.getSeznamVseHrane().get(1).getSeznamSestavin().add("S1");
        vsipodatki.getSeznamVseHrane().get(1).getSeznamSestavin().add("S2");
        vsipodatki.getSeznamVseHrane().get(1).getSeznamSestavin().add("S3");
        vsipodatki.getSeznamVseHrane().get(1).getSeznamSestavin().add("S4");
        vsipodatki.getSeznamVseHrane().get(1).getSeznamSestavin().add("S5");

        vsipodatki.getSeznamVseHrane().add(new Hrana("H3",5.1));
        vsipodatki.getSeznamVseHrane().get(2).getSeznamSestavin().add("S1");
        vsipodatki.getSeznamVseHrane().get(2).getSeznamSestavin().add("S2");

        vsipodatki.getSeznamVseHrane().add(new Hrana("H4",4.9));
        vsipodatki.getSeznamVseHrane().get(3).getSeznamSestavin().add("S1");
        vsipodatki.getSeznamVseHrane().get(3).getSeznamSestavin().add("S2");
        vsipodatki.getSeznamVseHrane().get(3).getSeznamSestavin().add("S3");

        vsipodatki.getSeznamVseHrane().add(new Hrana("H5",3.6));
        vsipodatki.getSeznamVseHrane().get(4).getSeznamSestavin().add("S1");

        //MENI
        vsipodatki.getSeznamVsehMenijev().add(new Meni("M1"));
        vsipodatki.getSeznamVsehMenijev().get(0).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(0));
        vsipodatki.getSeznamVsehMenijev().get(0).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(2));
        vsipodatki.getSeznamVsehMenijev().get(0).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(0));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("M2"));
        vsipodatki.getSeznamVsehMenijev().get(1).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(1));
        vsipodatki.getSeznamVsehMenijev().get(1).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(3));
        vsipodatki.getSeznamVsehMenijev().get(1).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(1));
        vsipodatki.getSeznamVsehMenijev().get(1).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(2));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("M3"));
        vsipodatki.getSeznamVsehMenijev().get(2).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(0));
        vsipodatki.getSeznamVsehMenijev().get(2).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(3));
        vsipodatki.getSeznamVsehMenijev().get(2).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(4));
        vsipodatki.getSeznamVsehMenijev().get(2).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(1));
        vsipodatki.getSeznamVsehMenijev().get(2).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(2));
        vsipodatki.getSeznamVsehMenijev().get(2).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(4));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("M4"));
        vsipodatki.getSeznamVsehMenijev().get(3).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(4));
        vsipodatki.getSeznamVsehMenijev().get(3).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(4));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("M5"));
        vsipodatki.getSeznamVsehMenijev().get(4).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(0));
        vsipodatki.getSeznamVsehMenijev().get(4).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(3));
        vsipodatki.getSeznamVsehMenijev().get(4).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(4));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("M6"));
        vsipodatki.getSeznamVsehMenijev().get(5).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(1));
        vsipodatki.getSeznamVsehMenijev().get(5).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(2));
        vsipodatki.getSeznamVsehMenijev().get(5).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(3));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("M7"));
        vsipodatki.getSeznamVsehMenijev().get(6).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(2));
        vsipodatki.getSeznamVsehMenijev().get(6).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(3));
        vsipodatki.getSeznamVsehMenijev().get(6).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(4));
        vsipodatki.getSeznamVsehMenijev().get(6).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(3));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("M8"));
        vsipodatki.getSeznamVsehMenijev().get(7).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(3));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("M9"));
        vsipodatki.getSeznamVsehMenijev().get(8).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(1));

        vsipodatki.getSeznamVsehMenijev().add(new Meni("M10"));
        vsipodatki.getSeznamVsehMenijev().get(9).getSeznamHrane().add(vsipodatki.getSeznamVseHrane().get(0));
        vsipodatki.getSeznamVsehMenijev().get(9).getSeznamPijace().add(vsipodatki.getSeznamVsehPijac().get(0));

        //MIZE / NAROČILA
        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 1"));
        vsipodatki.getSeznamVsehMiz().get(0).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(0));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 2"));
        vsipodatki.getSeznamVsehMiz().get(1).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(1));
        vsipodatki.getSeznamVsehMiz().get(1).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(4));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 3"));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 4"));
        vsipodatki.getSeznamVsehMiz().get(3).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(6));
        vsipodatki.getSeznamVsehMiz().get(3).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(7));
        vsipodatki.getSeznamVsehMiz().get(3).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(9));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 5"));
        vsipodatki.getSeznamVsehMiz().get(4).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(2));
        vsipodatki.getSeznamVsehMiz().get(4).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(4));
        vsipodatki.getSeznamVsehMiz().get(4).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(6));
        vsipodatki.getSeznamVsehMiz().get(4).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(7));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 6"));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 7"));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 8"));
        vsipodatki.getSeznamVsehMiz().get(7).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(2));
        vsipodatki.getSeznamVsehMiz().get(7).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(5));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 9"));
        vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(1));
        vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(3));
        vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(5));
        vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(7));
        vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(8));
        vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().add(vsipodatki.getSeznamVsehMenijev().get(9));

        vsipodatki.getSeznamVsehMiz().add(new Miza("Miza 10"));

        //KOLICINE NAROCIL
        //vsipodatki.getSeznamVsehMiz().get(0).getSeznamMenijev().get(0).setKolicina(2);
        //vsipodatki.getSeznamVsehMiz().get(1).getSeznamMenijev().get(1).setKolicina(4);
        //vsipodatki.getSeznamVsehMiz().get(4).getSeznamMenijev().get(2).setKolicina(2);
        //vsipodatki.getSeznamVsehMiz().get(7).getSeznamMenijev().get(0).setKolicina(2);
        //vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().get(2).setKolicina(3);
        //vsipodatki.getSeznamVsehMiz().get(8).getSeznamMenijev().get(5).setKolicina(2);

        return vsipodatki;
    }
}
