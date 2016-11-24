package com.zigabincl.com.natakar;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
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
 * Created by SPACE MARINE GENERAL on 3.4.2016.
 */
public class AdapterMize extends RecyclerView.Adapter<AdapterMize.ViewHolder> {
    //public static final String PARAMETER_POSITION_1 = "POSITION_Miza";
    private DataAll mDataset;
    Activity ac;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNaslov;
        public TextView txtNarocilo;
        public TextView txtCena;
        public TextView txtMeniji;
        public RelativeLayout elementVrste;
        public ImageView iv;
        public CustomView_LokacijeMiz mizeLokacije;

        public ViewHolder(View v) {
            super(v);
            txtNaslov = (TextView) v.findViewById(R.id.txtNaslov);
            txtCena = (TextView) v.findViewById(R.id.txtCena);
            txtMeniji = (TextView) v.findViewById(R.id.txtMeniji);
            txtNarocilo = (TextView) v.findViewById(R.id.txtHranaNarocilo);
            elementVrste = (RelativeLayout) v.findViewById(R.id.elementVrste);
            mizeLokacije = (CustomView_LokacijeMiz)v.findViewById(R.id.Platno);
        }
    }
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
        int skupnaKolicina=0;
        for (int i = 0; i < mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().size(); i++) {
            skupnaKolicina+=mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getKolicina();
        }
        final int velikost=skupnaKolicina;
        holder.txtNarocilo.setText("N: "+velikost);
        if (velikost>0)
        {
            holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaPrimaryLight)); //modra
        }
        else
        {
            holder.elementVrste.setBackgroundColor(ContextCompat.getColor(ac, R.color.mojaAccent)); //zelena
        }

        String menijiText="";
        for (int i = 0; i < mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().size(); i++) {
            menijiText+=mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getKolicina()+"x ";
            menijiText+=mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().get(i).getIme()+"\n";
        }
        holder.txtMeniji.setText(menijiText);

        //CustomView
        holder.mizeLokacije.editMode=false;
        int[] poljeLokacij=new int[100];
        //nastavi vse na 0 (bela)
        for (int i = 0; i < poljeLokacij.length; i++) {
            poljeLokacij[i]=0;
        }
        //nastavi vse mize na 1 (črna)
        for (int i = 0; i < mDataset.getSeznamVsehMiz().size(); i++) {
            int pos=mDataset.getSeznamVsehMiz().get(i).getLokacija();
            poljeLokacij[pos]=1;
        }
        //nastavi izbrano na 2 (rdeča)
        poljeLokacij[mDataset.getSeznamVsehMiz().get(positionMiza).getLokacija()]=2;
        holder.mizeLokacije.setPoljeLokacij(poljeLokacij);
       // holder.mizeLokacije.;
        holder.mizeLokacije.setLokacija(positionMiza);
        holder.mizeLokacije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent drugoOkno = new Intent(ac, Activity_LokacijeMiz.class);
                drugoOkno.putExtra("POSITION_MIZA",positionMiza);
                ac.startActivity(drugoOkno);
            }
        });

        //holder.iv.setImageDrawable(this.  ac.getDrawable(R.drawable.miza));
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
                                    //natisni račun(mizo dodaj v zgodovino)
                                    mDataset.getSeznamVsehMiz().get(positionMiza).setId(mDataset.getIDnavrsti());
                                    mDataset.getSeznamZgodovina().add(mDataset.getSeznamVsehMiz().get(positionMiza).vrniMizo());
                                    mDataset.inc();
                                    //sprazne mizo
                                    mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().clear();
                                    mDataset.getSeznamVsehMiz().get(positionMiza).setId(0);
                                    mDataset.change();
                                    //restart activity
                                    ac.finish();
                                    ac.startActivity(ac.getIntent());
                                }
                            })
                            .setNegativeButton("Počisti", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //sprazne mizo
                                    mDataset.getSeznamVsehMiz().get(positionMiza).getSeznamMenijev().clear();
                                    mDataset.getSeznamVsehMiz().get(positionMiza).setId(0);
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
