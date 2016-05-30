package com.zigabincl.com.natakar;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.DataAll;
import com.example.Hrana;
import com.example.Miza;

import java.util.ArrayList;

/**
 * Created by SPACE MARINE GENERAL on 3.4.2016.
 */
public class AdapterMize extends RecyclerView.Adapter<AdapterMize.ViewHolder> {
    //public static final String PARAMETER_POSITION_1 = "POSITION_Miza";
    private DataAll mDataset;
    private Podatki app;
    Activity ac;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNaslov;
        public TextView txtNarocilo;
        public TextView txtCena;
        public TextView txtMeniji;
        public RelativeLayout elementVrste;
        public ImageView iv;

        public ViewHolder(View v) {
            super(v);
            txtNaslov = (TextView) v.findViewById(R.id.txtNaslov);
            txtCena = (TextView) v.findViewById(R.id.txtCena);
            txtMeniji = (TextView) v.findViewById(R.id.txtMeniji);
            txtNarocilo = (TextView) v.findViewById(R.id.txtHranaNarocilo);
            elementVrste = (RelativeLayout) v.findViewById(R.id.elementVrste);
            iv = (ImageView)v.findViewById(R.id.icon);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterMize(DataAll myDataset, Activity ac) {
        this.ac = ac;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterMize.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_mize, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int positionMiza) {
        holder.txtNaslov.setText(mDataset.getSeznamVsehMiz().get(positionMiza).getIme());
        holder.txtCena.setText(String.format("%.2f", mDataset.getSeznamVsehMiz().get(positionMiza).getSkupnaCena())+" €");
        final int velikost=mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().size();
        holder.txtNarocilo.setText("Narocila: "+velikost);
        if (velikost>0)
        {
            holder.elementVrste.setBackgroundColor(Color.rgb(100,200,255)); //modra
        }
        else
        {
            holder.elementVrste.setBackgroundColor(Color.rgb(120,255,120)); //zelena
        }

        String menijiText="";
        for (int i = 0; i < mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().size(); i++) {
            menijiText+=mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getKolicina()+"x ";
            menijiText+=mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getIme()+"\n";
        }
        holder.txtMeniji.setText(menijiText);

        holder.iv.setImageDrawable(this.  ac.getDrawable(R.drawable.miza));
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //klik na element
        holder.elementVrste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent drugoOkno = new Intent(ac, Activity_3_Meniji.class);
                drugoOkno.putExtra("POSITION_MIZA",positionMiza);
                ac.startActivity(drugoOkno);
            }
        });

        holder.elementVrste.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                if(velikost>0)
                {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Potrditev?")
                            .setMessage("Ali želite potrditi izbrano naročilo mize "+(positionMiza+1)+"?")
                            .setPositiveButton("Potrdi", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().clear();


                                    //natisni račun
                                    //restart activity
                                    ac.finish();
                                    ac.startActivity(ac.getIntent());
                                }
                            })
                            .setNegativeButton("Počisti", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().clear();
                                    mDataset.change();

                                    //restart activity
                                    ac.finish();
                                    ac.startActivity(ac.getIntent());
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else
                {
                    Snackbar snackbar = Snackbar
                            .make(v, "Izbrali ste prazno mizo.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

                return true;
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.getSeznamVsehMiz().size();
    }

}
