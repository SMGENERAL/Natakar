package com.zigabincl.com.natakar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
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
import com.example.Hrana;
import com.example.Meni;

import java.util.ArrayList;

/**
 * Created by SPACE MARINE GENERAL on 2.5.2016.
 */
public class AdapterHranaPijaca extends RecyclerView.Adapter<AdapterHranaPijaca.ViewHolder> {
    private DataAll mDataset;
    Activity_4_Hrana_Pijaca ac;
    public int positionMiza;
    public int positionMeni;
    public int indexHvsi;
    public int indexPvsi;
    public int indexH;
    public int indexP;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNaslov; //ime
        public TextView txtCena; //cena
        public RelativeLayout elementVrste;
        public ImageView iv;

        public ViewHolder(View v) {
            super(v);
            txtNaslov = (TextView) v.findViewById(R.id.txtNaslov);
            txtCena = (TextView) v.findViewById(R.id.txtCena);
            elementVrste = (RelativeLayout) v.findViewById(R.id.elementVrste);
            iv = (ImageView)v.findViewById(R.id.icon);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterHranaPijaca(DataAll myDataset, Activity_4_Hrana_Pijaca ac, int pozicijaMiza, int pozicijaMeni) {
        this.ac = ac;
        mDataset = myDataset;
        positionMiza=pozicijaMiza;
        positionMeni=pozicijaMeni;
        indexHvsi=0;
        indexPvsi=0;
        indexH=0;
        indexP=0;

        //klik na shrani meni
        GumbShrani();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterHranaPijaca.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_hrana_pijaca, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int positionHranaPijaca) {


        if(positionHranaPijaca<mDataset.getSeznamVseHrane().size()) //delaj hrano
        {
            holder.txtNaslov.setText(mDataset.getSeznamVseHrane().get(indexHvsi).getIme());
            holder.txtCena.setText(String.format("%.2f", mDataset.getSeznamVseHrane().get(indexHvsi).getCena())+" €");
            holder.iv.setImageDrawable(this.ac.getDrawable(R.drawable.food)); //slika

            //nastavi barve glede ali je hrana v meniju
            mDataset.getSeznamVseHrane().get(indexHvsi).setOznacen(false);
            holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaPrimaryLight)); //modra

            for (int i = 0; i <mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamHrane().size() ; i++) {
                if(mDataset.getSeznamVseHrane().get(indexHvsi).getIme()==mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamHrane().get(i).getIme())
                {
                    mDataset.getSeznamVseHrane().get(indexHvsi).setOznacen(true);
                    holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaAccent)); //zelena
                }
            }

            //klik na hrano (označi / odznači)
            holder.elementVrste.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mDataset.getSeznamVseHrane().get(indexHvsi).isOznacen())
                    {
                        mDataset.getSeznamVseHrane().get(indexHvsi).setOznacen(false);
                        holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaPrimaryLight)); //modra
                        mDataset.change();
                        for (int i = 0; i < mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamHrane().size(); i++) {
                            if(mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamHrane().get(i).getIme()==mDataset.getSeznamVseHrane().get(indexHvsi).getIme())
                            {
                                mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamHrane().remove(i);
                            }
                        }
                    }
                    else
                    {
                        mDataset.getSeznamVseHrane().get(indexHvsi).setOznacen(true);
                        holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaAccent)); //zelena
                        mDataset.change();
                        mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamHrane().add(mDataset.getSeznamVseHrane().get(indexHvsi).vrniHrana());
                    }
                }
            });
            indexHvsi++;
        }
        else //delaj pijačo
        {
            holder.txtNaslov.setText(mDataset.getSeznamVsehPijac().get(indexPvsi).getIme());
            holder.txtCena.setText(String.format("%.2f", mDataset.getSeznamVsehPijac().get(indexPvsi).getCena())+" €");
            holder.iv.setImageDrawable(this.ac.getDrawable(R.drawable.drink)); //slika

            

            indexPvsi++;
        }


//        if(positionHranaPijaca<mDataset.getSeznamVseHrane().size()) //delaj hrano
//        {
//            holder.txtNaslov.setText(mDataset.getSeznamVseHrane().get(indexHvsi).getIme());
//            holder.txtCena.setText(String.format("%.2f", mDataset.getSeznamVseHrane().get(indexHvsi).getCena())+" €");
//            holder.iv.setImageDrawable(this.ac.getDrawable(R.drawable.food)); //slika
//
//            //nastavi barve glede ali je hrana v meniju
//            mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamHrane().get(indexH).setOznacen(false);
//            holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaPrimaryLight)); //modra
//            for (int i = 0; i <mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamHrane().size() ; i++) {
//                if(mDataset.getSeznamVseHrane().get(indexHvsi).getIme()==mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamHrane().get(i).getIme())
//                {
//                    holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaAccent)); //zelena
//                    mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamHrane().get(indexH).setOznacen(true);
//                }
//            }
//
//            //klik na hrano (označi / odznači)
//            holder.elementVrste.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamHrane().get(indexH).isOznacen())
//                    {
//                        mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamHrane().get(indexH).setOznacen(false);
//                        holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaPrimaryLight)); //modra
//                        mDataset.change();
//                        for (int i = 0; i < mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamHrane().size(); i++) {
//                            if(mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamHrane().get(i).getIme()==mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamHrane().get(indexH).getIme())
//                            {
//                                mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamHrane().remove(i);
//                            }
//                        }
//                    }
//                    else
//                    {
//                        mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamHrane().get(indexH).setOznacen(true);
//                        holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaAccent)); //zelena
//                        mDataset.change();
//                        mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamHrane().add(mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamHrane().get(indexH).vrniHrana());
//                    }
//                }
//            });
//            indexHvsi++;
//        }
//        else //delaj pijačo
//        {
//            holder.txtNaslov.setText(mDataset.getSeznamVsehPijac().get(indexP).getIme());
//            holder.txtCena.setText(String.format("%.2f", mDataset.getSeznamVsehPijac().get(indexP).getCena())+" €");
//            holder.iv.setImageDrawable(this.ac.getDrawable(R.drawable.drink)); //slika
//
//            //nastavi barve glede ali je pijača v meniju
//            mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamPijace().get(indexP).setOznacen(false);
//            holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaPrimaryLight)); //modra
//            for (int i = 0; i <mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamPijace().size() ; i++) {
//                if(mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamPijace().get(indexP).getIme()==mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamPijace().get(i).getIme())
//                {
//                    holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaAccent)); //zelena
//                    mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamPijace().get(indexP).setOznacen(true);
//                }
//            }
//
//            //klik na hrano (označi / odznači)
//            holder.elementVrste.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamPijace().get(indexP).isOznacen())
//                    {
//                        mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamPijace().get(indexP).setOznacen(false);
//                        holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaPrimaryLight)); //modra
//                        mDataset.change();
//                        for (int i = 0; i < mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamPijace().size(); i++) {
//                            if(mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamPijace().get(i).getIme()==mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamPijace().get(indexP).getIme())
//                            {
//                                mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamPijace().remove(i);
//                            }
//                        }
//                    }
//                    else
//                    {
//                        mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamPijace().get(indexP).setOznacen(true);
//                        holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaAccent)); //zelena
//                        mDataset.change();
//                        mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(positionMeni).getSeznamPijace().add(mDataset.getSeznamVsehMenijev().get(positionMeni).getSeznamPijace().get(indexP).vrniPijaca());
//                    }
//                }
//            });
//            indexPvsi++;
//        }
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
                Intent drugoOkno = new Intent(ac, Activity_3_Meniji.class);
                drugoOkno.putExtra("POSITION_MIZA",positionMiza);
                ac.startActivity(drugoOkno);
            }

        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.getSeznamVseHrane().size()+mDataset.getSeznamVsehPijac().size();
    }
}
