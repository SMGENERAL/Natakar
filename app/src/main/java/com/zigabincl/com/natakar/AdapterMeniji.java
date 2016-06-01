package com.zigabincl.com.natakar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.DataAll;
import com.example.Meni;

import java.util.ArrayList;

/**
 * Created by SPACE MARINE GENERAL on 2.5.2016.
 */
public class AdapterMeniji extends RecyclerView.Adapter<AdapterMeniji.ViewHolder> {
    private DataAll mDataset;
    Activity_3_Meniji ac;
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
    public AdapterMeniji(DataAll myDataset, Activity_3_Meniji ac, int pozicijaM) {
        this.ac = ac;
        mDataset = myDataset;
        positionMiza=pozicijaM;

        //klik na shrani naročilo
        GumbShrani();
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

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //nastavi barve in podatke glede ali je meni v naročilu
        mDataset.getSeznamVsehMenijev().get(positionMeni).setOznacen(false);
        holder.btnPlus.setEnabled(false);
        holder.btnMinus.setEnabled(false);
        holder.elementVrste.setBackgroundColor(Color.rgb(221,221,221)); //siva
        for (int i = 0; i <mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().size() ; i++) {
            if(mDataset.getSeznamVsehMenijev().get(positionMeni).getIme()==mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getIme())
            {
                holder.elementVrste.setBackgroundColor(Color.rgb(120,255,120)); //zelena

                mDataset.getSeznamVsehMenijev().get(positionMeni).setOznacen(true);
                holder.txtCena.setText(String.format("%.2f", mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getCena())+" €");
                holder.txtKolicina.setText(mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getKolicina()+"x");
                holder.btnPlus.setEnabled(true);
                holder.btnMinus.setEnabled(true);
            }
        }
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //slika
        holder.iv.setImageDrawable(this.ac.getDrawable(R.drawable.meni));

        //klik na sliko za activity hrane in pijače
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(ac.findViewById(holder.elementVrste.getId()), "Pojdi na podrobnosti menija "+(positionMeni+1), Snackbar.LENGTH_LONG).show();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //izbira kolicine
        // +
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().size() ; i++) {
                    if(mDataset.getSeznamVsehMenijev().get(positionMeni).getIme()==mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getIme())
                    {
                        if(mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getKolicina()<20)
                        {
                            mDataset.change();
                            mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).inc();
                            holder.txtCena.setText(String.format("%.2f", mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getCena())+" €");
                            holder.txtKolicina.setText(mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getKolicina()+"x");
                        }
                        else
                        {
                            Snackbar.make(ac.findViewById(holder.elementVrste.getId()), "MAX. 20x", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
        // -
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().size() ; i++) {
                    if(mDataset.getSeznamVsehMenijev().get(positionMeni).getIme()==mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getIme())
                    {
                        if(mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getKolicina()>1)
                        {
                            mDataset.change();
                            mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).decr();
                            holder.txtCena.setText(String.format("%.2f", mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getCena())+" €");
                            holder.txtKolicina.setText(mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getKolicina()+"x");
                        }
                        else
                        {
                            Snackbar.make(ac.findViewById(holder.elementVrste.getId()), "MIN. 1x", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //klik na meni (označi / odznači)
        holder.elementVrste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDataset.getSeznamVsehMenijev().get(positionMeni).isOznacen())
                {
                    mDataset.getSeznamVsehMenijev().get(positionMeni).setOznacen(false);
                    holder.elementVrste.setBackgroundColor(Color.rgb(221,221,221)); //siva
                    mDataset.change();
                  //  mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).setKolicina(1);
                    holder.btnPlus.setEnabled(false);
                    holder.btnMinus.setEnabled(false);
                    mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().remove(mDataset.getSeznamVsehMenijev().get(positionMeni));
                }
                else
                {
                    mDataset.getSeznamVsehMenijev().get(positionMeni).setOznacen(true);
                    holder.elementVrste.setBackgroundColor(Color.rgb(120,255,120)); //zelena
                    mDataset.change();
                    holder.btnPlus.setEnabled(true);
                    holder.btnMinus.setEnabled(true);
                    mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().add(mDataset.getSeznamVsehMenijev().get(positionMeni));
                }
            }
        });
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //shrani gumb
    public void GumbShrani()
    {
        final FloatingActionButton fab = (FloatingActionButton) ac.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDataset.ch) {
                    ac.app.save();
                    mDataset.saved();
                }
                Intent drugoOkno = new Intent(ac, Activity_2_Mize.class);
                drugoOkno.putExtra("POSITION_MIZA",positionMiza);
                ac.startActivity(drugoOkno);
            }

        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.getSeznamVsehMenijev().size();
    }
}
