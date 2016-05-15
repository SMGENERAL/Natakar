package com.zigabincl.com.natakar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.DataAll;
import com.example.Meni;

import java.util.ArrayList;

/**
 * Created by SPACE MARINE GENERAL on 2.5.2016.
 */
public class AdapterMeniji extends RecyclerView.Adapter<AdapterMeniji.ViewHolder> {
    //public static final String PARAMETER_POSITION_1 = "POSITION_Narocilo";
    private DataAll mDataset;
    Activity ac;
    public int positionMiza;
    public int positionNarocilo;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNaslov; //ime
        public TextView txtSeznamHrane; //seznam hrane
        public TextView txtCena; //cena
        public TextView txtSeznamPijace; //seznam pijace
        public RelativeLayout elementVrste;
        public ImageView iv;

        public ViewHolder(View v) {
            super(v);
            txtNaslov = (TextView) v.findViewById(R.id.txtNaslov);
            txtSeznamHrane = (TextView) v.findViewById(R.id.txtSeznamhrane);
            txtCena = (TextView) v.findViewById(R.id.txtCena);
            txtSeznamPijace = (TextView) v.findViewById(R.id.txtSeznamPijace);
            elementVrste = (RelativeLayout) v.findViewById(R.id.elementVrste);
            iv = (ImageView)v.findViewById(R.id.icon);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterMeniji(DataAll myDataset, Activity ac, int pozicijaM, int pozicijaN) {
        this.ac = ac;
        mDataset = myDataset;
        positionMiza=pozicijaM;
        positionNarocilo=pozicijaN;

        //klik na shrani naročilo
        GumbEvent();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterMeniji.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_meniji, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int positionMeni) {
        ArrayList<Meni> trenutniMeniji=new ArrayList<>();
        if(positionNarocilo!=-1)
        {
            trenutniMeniji = (ArrayList<Meni>) mDataset.getDolocenoMizo(positionMiza).getSeznamNarocil().get(positionNarocilo).getSeznamMenijev();
        }
        final ArrayList<Meni> vsiMeniji=(ArrayList<Meni>) mDataset.getSeznamVsehMenijev();

        //nastavi vse texte na trenutni meni
        holder.txtNaslov.setText(vsiMeniji.get(positionMeni).getIme());
        holder.txtCena.setText(String.format("%.2f", vsiMeniji.get(positionMeni).getCena())+" €");

        String nizHrane="";
        int velikostHrane=vsiMeniji.get(positionMeni).getSeznamHrane().size();
        for (int j = 0; j <velikostHrane ; j++) {
            nizHrane+=vsiMeniji.get(positionMeni).getSeznamHrane().get(j).getIme()+", ";
        }
        holder.txtSeznamHrane.setText(nizHrane);

        String nizPijace="";
        int velikostPijace=vsiMeniji.get(positionMeni).getSeznamPijace().size();
        for (int j = 0; j <velikostPijace ; j++) {
            nizPijace+=vsiMeniji.get(positionMeni).getSeznamPijace().get(j).getIme()+", ";
        }
        holder.txtSeznamPijace.setText(nizPijace);

        vsiMeniji.get(positionMeni).setOznacen(false);
        //nastavi barve glede ali je meni v naročilu
        holder.elementVrste.setBackgroundColor(Color.rgb(255,180,180)); //svetlo rdeča
        for (int i = 0; i <trenutniMeniji.size() ; i++) {
            if(trenutniMeniji.get(i).getIme()==vsiMeniji.get(positionMeni).getIme())
            {
                holder.elementVrste.setBackgroundColor(Color.rgb(180,255,180)); //svetlo zelena
                vsiMeniji.get(positionMeni).setOznacen(true);
            }
        }
        holder.iv.setImageDrawable(this.ac.getDrawable(R.drawable.meni));

        holder.elementVrste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vsiMeniji.get(positionMeni).isOznacen())
                {
                    vsiMeniji.get(positionMeni).setOznacen(false);
                    holder.elementVrste.setBackgroundColor(Color.rgb(255,180,180)); //svetlo rdeča
                }
                else
                {
                    vsiMeniji.get(positionMeni).setOznacen(true);
                    holder.elementVrste.setBackgroundColor(Color.rgb(180,255,180)); //svetlo zelena
                }
            }
        });
    }

    public void GumbEvent()
    {
        final FloatingActionButton fab = (FloatingActionButton) ac.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Meni> vsiMeniji=(ArrayList<Meni>) mDataset.getSeznamVsehMenijev();
                boolean prazen=true;
                for (int i = 0; i < vsiMeniji.size(); i++) {
                    if(vsiMeniji.get(i).isOznacen())
                    {
                        prazen=false;
                    }
                }
                if(positionNarocilo==-1) //naredi novo naročilo
                {
                    if (prazen)
                    {
                        Snackbar.make(ac.findViewById(R.id.toolbar), "Izberite vsaj 1 meni.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else
                    {
                        Narocilo novo=new Narocilo();
                        novo.setIme("novo"+positionNarocilo);
                        for (int i = 0; i < vsiMeniji.size(); i++) {
                            if(vsiMeniji.get(i).isOznacen())
                            {
                                novo.dodajMeni(vsiMeniji.get(i));
                            }
                        }
                        novo.izracunajSkupnoCeno();
                        mDataset.getDolocenoMizo(positionMiza).dodajNarocilo(novo);
                        mDataset.dodaj(novo);

                        Intent drugoOkno = new Intent(ac, Activity_3_Narocila.class);
                        drugoOkno.putExtra("POSITION_MIZA",positionMiza);
                        ac.startActivity(drugoOkno);
                    }
                }
                else //update staro naročilo
                {
                    if (prazen)
                    {
                        Snackbar.make(ac.findViewById(R.id.toolbar), "Izberite vsaj 1 meni.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else
                    {
                        Narocilo obstojece=mDataset.getDolocenoMizo(positionMiza).getSeznamNarocil().get(positionNarocilo);
                        obstojece.setIme("update"+positionNarocilo);
                        ArrayList<Meni> noviSeznamMenijev=new ArrayList<Meni>();
                        for (int i = 0; i < vsiMeniji.size(); i++) {
                            if(vsiMeniji.get(i).isOznacen())
                            {
                                noviSeznamMenijev.add(vsiMeniji.get(i));
                            }
                        }
                        obstojece.setSeznamMenijev(noviSeznamMenijev);
                        obstojece.izracunajSkupnoCeno();
                        mDataset.getDolocenoMizo(positionMiza).getSeznamNarocil().set(positionNarocilo,obstojece);

                        Intent drugoOkno = new Intent(ac, Activity_3_Narocila.class);
                        drugoOkno.putExtra("POSITION_MIZA",positionMiza);
                        ac.startActivity(drugoOkno);
                    }
                }
            }
        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.getSeznamVsehMenijev().size();
    }
}
