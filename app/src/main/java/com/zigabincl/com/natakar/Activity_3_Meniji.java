package com.zigabincl.com.natakar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class Activity_3_Meniji extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AdapterMeniji mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public Podatki app;
    public int pozicijaMiza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_3);
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
        }
        else
        {
            pozicijaMiza =0;
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.listElementov);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new AdapterMeniji(app.getAll(),app.getAll(), this, pozicijaMiza);
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onBackPressed() {
        if(app.getAll().ch) {


            new AlertDialog.Builder(this)
                    .setTitle("Potrditev?")
                    .setMessage("Ali želite shraniti naročilo?")
                    .setPositiveButton("Shrani", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            app.save();
                            app.getAll().saved();
                           // onBackPressed();
                        }
                    })
                    .setNegativeButton("Prekliči", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            app.load();
                           // onBackPressed();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        Intent drugoOkno = new Intent(this, Activity_2_Mize.class);
        drugoOkno.putExtra("POSITION_MIZA",pozicijaMiza);
        startActivity(drugoOkno);
    }

}
