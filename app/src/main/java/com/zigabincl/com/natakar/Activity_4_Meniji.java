package com.zigabincl.com.natakar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Activity_4_Meniji extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Podatki app;
    public int pozicijaMiza;
    public int pozicijaNarocilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        app = (Podatki) getApplication();

        //back gumb
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //dobi index klika
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            pozicijaMiza = (int) bd.get("POSITION_MIZA");
            pozicijaNarocilo = (int) bd.get("POSITION_NAROCILO");
        }
        else
        {
            pozicijaMiza =0;
            pozicijaNarocilo=0;
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.listElementov);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new AdapterMeniji(app.getAll(), this, pozicijaMiza, pozicijaNarocilo);
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onBackPressed() {
        Intent drugoOkno = new Intent(this, Activity_3_Narocila.class);
        drugoOkno.putExtra("POSITION_MIZA",pozicijaMiza);
        startActivity(drugoOkno);
    }

}
