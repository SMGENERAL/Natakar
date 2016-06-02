package com.zigabincl.com.natakar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_Lokacije extends AppCompatActivity {
    public Podatki app;
    private MizeLokacije mizeLokacije;
    private int positionMiza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity__lokacije);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        app = (Podatki) getApplication();

        mizeLokacije = (MizeLokacije)findViewById(R.id.Platno);

        //back gumb
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //dobi index klika
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            positionMiza = (int) bd.get("POSITION_MIZA");
        }
        else
        {
            positionMiza =100;
        }

        //CustomView
        mizeLokacije.editMode=true;
        int[] poljeLokacij=new int[100];
        //nastavi vse na 0 (bela)
        for (int i = 0; i < poljeLokacij.length; i++) {
            poljeLokacij[i]=0;
        }
        //nastavi vse mize na 1 (črna)
        for (int i = 0; i < app.getAll().getSeznamVsehMiz().size(); i++) {
            int pos=app.getAll().getSeznamVsehMiz().get(i).getLokacija();
            poljeLokacij[pos]=1;
        }
        //nastavi izbrano na 2 (rdeča)
        poljeLokacij[app.getAll().getSeznamVsehMiz().get(positionMiza).getLokacija()]=2;
        mizeLokacije.setPoljeLokacij(poljeLokacij);
        mizeLokacije.setLokacija(positionMiza);
        //shrani gumb
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app.getAll().getSeznamVsehMiz().get(positionMiza).setLokacija(mizeLokacije.getLokacija());
                app.save();
                app.getAll().saved();
                Snackbar.make(view, "Shranil novo lokacijo mize "+(positionMiza+1), Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(app.getAll().ch)
        {
            app.load();
        }
        Intent drugoOkno = new Intent(this, Activity_2_Mize.class);
        startActivity(drugoOkno);

    }

}
