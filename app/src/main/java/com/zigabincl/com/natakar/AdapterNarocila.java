package com.zigabincl.com.natakar;

import android.app.Activity;
import android.content.Intent;
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

import java.util.ArrayList;
/**
 * Created by SPACE MARINE GENERAL on 30.4.2016.
 */
public class AdapterNarocila extends RecyclerView.Adapter<AdapterNarocila.ViewHolder> {
    //public static final String PARAMETER_POSITION_1 = "POSITION_Narocilo";
    private DataAll mDataset;
    Activity ac;
    public int positionMiza;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNaslov; //ime
        public TextView txtStMenijev; //stevilo menijev
        public TextView txtCena; //cena
        public TextView txtSeznamMenijev; //seznam menijev
        public RelativeLayout elementVrste;
        public ImageView iv;

        public ViewHolder(View v) {
            super(v);
            txtNaslov = (TextView) v.findViewById(R.id.txtNaslov);
            txtStMenijev = (TextView) v.findViewById(R.id.txtStMenijev);
            txtCena = (TextView) v.findViewById(R.id.txtCena);
            txtSeznamMenijev = (TextView) v.findViewById(R.id.txtSeznamMenijev);
            elementVrste = (RelativeLayout) v.findViewById(R.id.elementVrste);
            iv = (ImageView)v.findViewById(R.id.icon);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterNarocila(DataAll myDataset, Activity ac, int pozicija) {
        this.ac = ac;
        mDataset = myDataset;
        positionMiza=pozicija;

        //klik na dodaj novo naročilo
        GumbEvent();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterNarocila.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_narocila, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(ViewHolder holder, final int positionNarocilo) {
        // - get element from your dataset at this position
        ArrayList<Narocilo> trenutni = (ArrayList<Narocilo>) mDataset.getDolocenoMizo(positionMiza).getSeznamNarocil();
        holder.txtNaslov.setText(trenutni.get(positionNarocilo).getIme());
        holder.txtCena.setText(String.format("%.2f", trenutni.get(positionNarocilo).getSkupnaCena())+" €");
        int velikost=trenutni.get(positionNarocilo).getSeznamMenijev().size();
        holder.txtStMenijev.setText("ST Menijev: "+velikost);

        String nizMenijev="";
        for (int i=0;i<velikost;i++)
        {
            nizMenijev+=trenutni.get(positionNarocilo).getSeznamMenijev().get(i).getIme()+", ";
        }
        holder.txtSeznamMenijev.setText(nizMenijev);
        holder.iv.setImageDrawable(this.ac.getDrawable(R.drawable.order));

        //klik na element
        holder.elementVrste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent drugoOkno = new Intent(ac, Activity_4_Meniji.class);
                //drugoOkno.putExtra(PARAMETER_POSITION_1,position);
                drugoOkno.putExtra("POSITION_MIZA",positionMiza);
                drugoOkno.putExtra("POSITION_NAROCILO",positionNarocilo);
                ac.startActivity(drugoOkno);
            }
        });

        //klik na element
        holder.elementVrste.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar.make(ac.findViewById(R.id.toolbar), "Označil to naročilo.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            }
        });
    }

    public void GumbEvent()
    {
        FloatingActionButton fab = (FloatingActionButton) ac.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent drugoOkno = new Intent(ac, Activity_4_Meniji.class);
                //drugoOkno.putExtra(PARAMETER_POSITION_1,position);
                drugoOkno.putExtra("POSITION_MIZA",positionMiza);
                drugoOkno.putExtra("POSITION_NAROCILO",-1);
                ac.startActivity(drugoOkno);
            }
        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.getDolocenoMizo(positionMiza).getSeznamNarocil().size();
    }
}
