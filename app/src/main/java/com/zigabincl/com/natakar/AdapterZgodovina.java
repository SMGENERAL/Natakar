package com.zigabincl.com.natakar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

/**
 * Created by SPACE MARINE GENERAL on 7.6.2016.
 */
public class AdapterZgodovina extends RecyclerView.Adapter<AdapterZgodovina.ViewHolder> {
    //public static final String PARAMETER_POSITION_1 = "POSITION_Miza";
    private DataAll mDataset;
    Activity ac;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNaslov;
        public TextView txtNarocilo;
        public TextView txtCena;
        public TextView txtMeniji;
        public TextView txtID;
        public RelativeLayout elementVrste;
        public ImageView iv;

        public ViewHolder(View v) {
            super(v);
            txtNaslov = (TextView) v.findViewById(R.id.txtNaslov);
            txtCena = (TextView) v.findViewById(R.id.txtCena);
            txtMeniji = (TextView) v.findViewById(R.id.txtMeniji);
            txtNarocilo = (TextView) v.findViewById(R.id.txtHranaNarocilo);
            txtID = (TextView) v.findViewById(R.id.txtID);
            elementVrste = (RelativeLayout) v.findViewById(R.id.elementVrste);
            iv = (ImageView)v.findViewById(R.id.icon);
        }
    }
    public AdapterZgodovina(DataAll myDataset, Activity ac) {
        this.ac = ac;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterZgodovina.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_zgodovinamiz, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int positionMiza) {
        holder.txtNaslov.setText(mDataset.getSeznamZgodovina().get(positionMiza).getIme());
        holder.txtCena.setText(String.format("%.2f", mDataset.getSeznamZgodovina().get(positionMiza).getSkupnaCena())+" €");
        int velikost=mDataset.getSeznamZgodovina().get(positionMiza).getSeznamMenijev().size();
        holder.txtNarocilo.setText("N: "+velikost);
        holder.txtID.setText("ID: "+mDataset.getSeznamZgodovina().get(positionMiza).getId());
        holder.elementVrste.setBackgroundColor(Color.rgb(221,221,221)); //siva

        String menijiText="";
        for (int i = 0; i < mDataset.getSeznamZgodovina().get(positionMiza).getSeznamMenijev().size(); i++) {
            menijiText+=mDataset.getSeznamZgodovina().get(positionMiza).getSeznamMenijev().get(i).getKolicina()+"x ";
            menijiText+=mDataset.getSeznamZgodovina().get(positionMiza).getSeznamMenijev().get(i).getIme()+"\n";
        }
        holder.txtMeniji.setText(menijiText);
        //slika
        holder.iv.setImageDrawable(this.  ac.getDrawable(R.drawable.orderhistoryblack));

        holder.elementVrste.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Potrditev?")
                        .setMessage("Ali želite izbrisati izbrano naročilo iz zgodovine?")
                        .setPositiveButton("Izbrisi", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mDataset.getSeznamZgodovina().remove(positionMiza);
                                //restart activity
                                ac.finish();
                                ac.startActivity(ac.getIntent());
                            }
                        })
                        .setNegativeButton("Preklici", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.getSeznamZgodovina().size();
    }
}
