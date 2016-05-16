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
import android.widget.Button;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNaslov; //ime
        public TextView txtSeznamHrane; //seznam hrane
        public TextView txtCena; //cena
        public TextView txtKolicina; //kolicina
        public TextView txtSeznamPijace; //seznam pijace
        public RelativeLayout elementVrste;
        public ImageView iv;
        public Button btnPlus;
        public Button btnMinus;

        public ViewHolder(View v) {
            super(v);
            txtNaslov = (TextView) v.findViewById(R.id.txtNaslov);
            txtSeznamHrane = (TextView) v.findViewById(R.id.txtSeznamhrane);
            txtCena = (TextView) v.findViewById(R.id.txtCena);
            txtKolicina = (TextView) v.findViewById(R.id.txtKolicina);
            txtSeznamPijace = (TextView) v.findViewById(R.id.txtSeznamPijace);
            elementVrste = (RelativeLayout) v.findViewById(R.id.elementVrste);
            iv = (ImageView)v.findViewById(R.id.icon);
            btnPlus = (Button) v.findViewById(R.id.btnPlus);
            btnMinus = (Button) v.findViewById(R.id.btnMinus);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterMeniji(DataAll myDataset, Activity ac, int pozicijaM) {
        this.ac = ac;
        mDataset = myDataset;
        positionMiza=pozicijaM;

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
        //nastavi vse texte na trenutni meni
        holder.txtNaslov.setText(mDataset.getSeznamVsehMenijev().get(positionMeni).getIme());
        holder.txtCena.setText(String.format("%.2f", mDataset.getSeznamVsehMenijev().get(positionMeni).getCena())+" €");
        holder.txtKolicina.setText(mDataset.getSeznamVsehMenijev().get(positionMeni).getKolicina()+"x");

        String nizHrane="";
        int velikostHrane=mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamHrane().size();
        for (int j = 0; j <velikostHrane ; j++) {
            nizHrane+=mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamHrane().get(j).getIme()+"\n";
        }
        holder.txtSeznamHrane.setText(nizHrane);

        String nizPijace="";
        int velikostPijace=mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamPijace().size();
        for (int j = 0; j <velikostPijace ; j++) {
            nizPijace+=mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamPijace().get(j).getIme()+"\n";
        }
        holder.txtSeznamPijace.setText(nizPijace);

        //nastavi barve glede ali je meni v naročilu
        mDataset.getSeznamVsehMenijev().get(positionMeni).setOznacen(false);
        holder.elementVrste.setBackgroundColor(Color.rgb(255,180,180)); //svetlo rdeča
        for (int i = 0; i <mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().size() ; i++) {
            if(mDataset.getSeznamVsehMenijev().get(positionMeni).getIme()==mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getIme())
            {
                holder.elementVrste.setBackgroundColor(Color.rgb(180,255,180)); //svetlo zelena
                mDataset.getSeznamVsehMenijev().get(positionMeni).setOznacen(true);
            }
        }
        holder.iv.setImageDrawable(this.ac.getDrawable(R.drawable.meni));

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nkolicina=mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getKolicina();
                if(nkolicina<10)
                {
                    mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).setKolicina(nkolicina+1);
                    holder.txtCena.setText(String.format("%.2f", mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getCena())+" €");
                    holder.txtKolicina.setText(mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getKolicina()+"x");
                }
                else
                {
                    Snackbar.make(ac.findViewById(holder.elementVrste.getId()), "MAX. 10x", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nkolicina=mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getKolicina();
                if(nkolicina>1)
                {
                    mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).setKolicina(nkolicina-1);
                    holder.txtCena.setText(String.format("%.2f", mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getCena())+" €");
                    holder.txtKolicina.setText(mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getKolicina()+"x");
                }
                else
                {
                    Snackbar.make(ac.findViewById(holder.elementVrste.getId()), "MIN. 1x", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        holder.elementVrste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDataset.getSeznamVsehMenijev().get(positionMeni).isOznacen())
                {
                    mDataset.getSeznamVsehMenijev().get(positionMeni).setOznacen(false);
                    holder.elementVrste.setBackgroundColor(Color.rgb(255,180,180)); //svetlo rdeča
                }
                else
                {
                    mDataset.getSeznamVsehMenijev().get(positionMeni).setOznacen(true);
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



            }
        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.getSeznamVsehMenijev().size();
    }
}
