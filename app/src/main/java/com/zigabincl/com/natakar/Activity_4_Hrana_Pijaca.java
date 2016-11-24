package com.zigabincl.com.natakar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Activity_4_Hrana_Pijaca extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AdapterHranaPijaca mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public Podatki app;
    public int pozicijaMiza;
    public int pozicijaMeni;

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
            pozicijaMeni = (int) bd.get("POSITION_MENI");
        }
        else
        {
            pozicijaMiza =0;
            pozicijaMeni =0;
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.listElementov);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new AdapterHranaPijaca(app.getAll(), this, pozicijaMiza,pozicijaMeni);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        if(app.getAll().ch) {
            app.load();
        }
        Intent drugoOkno = new Intent(this, Activity_2_Mize.class);
        drugoOkno.putExtra("POSITION_MIZA",pozicijaMiza);
        startActivity(drugoOkno);

    }

}
