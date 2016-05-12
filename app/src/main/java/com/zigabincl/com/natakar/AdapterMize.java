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
import com.example.Miza;

import java.util.ArrayList;

/**
 * Created by SPACE MARINE GENERAL on 3.4.2016.
 */
public class AdapterMize extends RecyclerView.Adapter<AdapterMize.ViewHolder> {
    //public static final String PARAMETER_POSITION_1 = "POSITION_Miza";
    private DataAll mDataset;
    Activity ac;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNaslov;
        public TextView txtNarocilo;
        public TextView txtCena;
        public TextView txtStatus;
        public RelativeLayout elementVrste;
        public ImageView iv;

        public ViewHolder(View v) {
            super(v);
            txtNaslov = (TextView) v.findViewById(R.id.txtNaslov);
            txtCena = (TextView) v.findViewById(R.id.txtCena);
            txtStatus = (TextView) v.findViewById(R.id.txtStatus);
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
    @Override
    public void onBindViewHolder(ViewHolder holder, final int positionMiza) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ArrayList<Miza> trenutni = (ArrayList<Miza>) mDataset.getSeznamVsehMiz();
        holder.txtNaslov.setText(trenutni.get(positionMiza).getIme());
        holder.txtCena.setText(String.format("%.2f", trenutni.get(positionMiza).getSkupnaCena())+" €");
        int velikost=trenutni.get(positionMiza).getSeznamNarocil().size();
        holder.txtNarocilo.setText("Narocila: "+velikost);
        if (velikost>0)
        {
            holder.txtStatus.setText("Zasedena");
            holder.elementVrste.setBackgroundColor(Color.rgb(255,180,180));
        }
        else
        {
            holder.txtStatus.setText("Prosta");
            holder.elementVrste.setBackgroundColor(Color.rgb(180,255,180));
        }
        holder.iv.setImageDrawable(this.  ac.getDrawable(R.drawable.miza));

        //klik na element
        holder.elementVrste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent drugoOkno = new Intent(ac, Activity_3_Narocila.class);
                //drugoOkno.putExtra(PARAMETER_POSITION_1,position);
                drugoOkno.putExtra("POSITION_MIZA",positionMiza);
                ac.startActivity(drugoOkno);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.getSeznamVsehMiz().size();
    }

}
