package com.zigabincl.com.natakar;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.DataAll;
import com.example.MeniWeka;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SPACE MARINE GENERAL on 10.1.2017.
 */

public class AdapterWeka extends RecyclerView.Adapter<AdapterWeka.ViewHolder> {
    private DataAll mDataset;
    Activity_Weka ac;
    public ArrayList<MeniWeka> seznamMenijevWeka;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNaslov;
        public TextView txtSeznamHrane;
        public TextView txtSeznamPijace;
        public TextView txtCena; //cena
        public TextView txtPopularnost;
        public RelativeLayout elementVrste;
        public ImageView iv;
        private ProgressBar loading;

        public ViewHolder(View v) {
            super(v);
            txtNaslov = (TextView) v.findViewById(R.id.txtNaslov);
            txtSeznamHrane = (TextView) v.findViewById(R.id.txtSeznamhrane);
            txtSeznamPijace = (TextView) v.findViewById(R.id.txtSeznamPijace);
            txtCena = (TextView) v.findViewById(R.id.txtCena);
            txtPopularnost = (TextView) v.findViewById(R.id.txtPopularnost);
            elementVrste = (RelativeLayout) v.findViewById(R.id.elementVrste);
            iv = (ImageView)v.findViewById(R.id.icon);
            loading = (ProgressBar) v.findViewById(R.id.progressBarPopularnost);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterWeka(Activity_Weka ac, ArrayList<MeniWeka> seznamWeka) {
        this.ac = ac;
        seznamMenijevWeka=seznamWeka;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterWeka.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_weka, parent, false);
        // set the view's size, margins, paddings and layout parameters
        AdapterWeka.ViewHolder vh = new AdapterWeka.ViewHolder(v);
        return vh;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(final AdapterWeka.ViewHolder holder, final int index) {
        //nastavi vse texte na trenutni meni
        holder.txtNaslov.setText(seznamMenijevWeka.get(index).getIme());
        holder.txtCena.setText(String.format("%.2f", seznamMenijevWeka.get(index).getCena())+" €");
        holder.txtPopularnost.setText(seznamMenijevWeka.get(index).getPopularnost()+" popularen");

        String nizHrane="";
        int velikostHrane=seznamMenijevWeka.get(index).getSeznamHrane().size();
        for (int j = 0; j <velikostHrane ; j++) {
            nizHrane+="-"+seznamMenijevWeka.get(index).getSeznamHrane().get(j).getIme()+"\n";
        }
        holder.txtSeznamHrane.setText(nizHrane);

        String nizPijace="";
        int velikostPijace=seznamMenijevWeka.get(index).getSeznamPijace().size();
        for (int j = 0; j <velikostPijace ; j++) {
            nizPijace+="-"+seznamMenijevWeka.get(index).getSeznamPijace().get(j).getIme()+"\n";
        }
        holder.txtSeznamPijace.setText(nizPijace);
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //slika
        holder.iv.setImageDrawable(this.ac.getDrawable(R.drawable.meni));
        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //progressbar
        holder.loading.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        int duration=1000;
        int premik=(holder.loading.getMax()/2)/3;
        if(seznamMenijevWeka.get(index).getPopularnost().equals("najmanj"))
        {
            holder.loading.setProgress(0);
            ObjectAnimator progressAnimator1= ObjectAnimator.ofInt(holder.loading, "progress", 0, premik);
            progressAnimator1.setDuration(duration);
            progressAnimator1.setInterpolator(new LinearInterpolator());
            progressAnimator1.start();
        }
        else if(seznamMenijevWeka.get(index).getPopularnost().equals("manj"))
        {
            holder.loading.setProgress(0);
            ObjectAnimator progressAnimator2 = ObjectAnimator.ofInt(holder.loading, "progress", 0, 2*premik);
            progressAnimator2.setDuration(duration);
            progressAnimator2.setInterpolator(new LinearInterpolator());
            progressAnimator2.start();
        }
        else if(seznamMenijevWeka.get(index).getPopularnost().equals("srednje"))
        {
            holder.loading.setProgress(0);
            ObjectAnimator progressAnimator3 = ObjectAnimator.ofInt(holder.loading, "progress", 0, 3*premik);
            progressAnimator3.setDuration(duration);
            progressAnimator3.setInterpolator(new LinearInterpolator());
            progressAnimator3.start();
        }
        else if(seznamMenijevWeka.get(index).getPopularnost().equals("bolj"))
        {
            holder.loading.setProgress(0);
            ObjectAnimator progressAnimator4 = ObjectAnimator.ofInt(holder.loading, "progress", 0, 4*premik);
            progressAnimator4.setDuration(duration);
            progressAnimator4.setInterpolator(new LinearInterpolator());
            progressAnimator4.start();
        }
        else if(seznamMenijevWeka.get(index).getPopularnost().equals("najbolj"))
        {
            holder.loading.setProgress(0);
            ObjectAnimator progressAnimator5 = ObjectAnimator.ofInt(holder.loading, "progress", 0, holder.loading.getMax());
            progressAnimator5.setDuration(duration);
            progressAnimator5.setInterpolator(new LinearInterpolator());
            progressAnimator5.start();
        }
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return seznamMenijevWeka.size();
    }
}
