package com.zigabincl.com.natakar;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.DataAll;
import com.example.MeniWeka;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;

import weka.core.converters.ConverterUtils;
import weka.core.converters.ConverterUtils.DataSource;

import static android.R.attr.path;

public class Activity_Weka extends AppCompatActivity {
    public TextView lblInfo;
    public Podatki app;
    private DataAll mDataset;
    private Context context;
    private RecyclerView mRecyclerView;
    private AdapterWeka mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity__weka);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        app = (Podatki) getApplication();
        mDataset=app.getAll();
        //back gumb
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lblInfo = (TextView) findViewById(R.id.lblInfo);
        //__________________________________________________________________________________________________________________________________________________
        //TESTNI PODATKI
        //PREBERI PODATKE IZ ARFF DATOTEKE
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new FileReader("/storage/82BD-1C08/emulated/0/data2.arff"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Instances dataTest = null;
        try {
            dataTest = new Instances(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // setting class attribute
        dataTest.setClassIndex(dataTest.numAttributes() - 1);

        //DELAJ UČENJE
        J48 klasifikator = new J48();         // new instance of tree
        try {
            String[] options = new String[1];
            options[0] = "-U";
            klasifikator.setOptions(options);     // set the options
            klasifikator.buildClassifier(dataTest);   // build classifier
        } catch (Exception e) {
            e.printStackTrace();
        }

        //DOBI REZULTATE
        Evaluation eval = null;
        try {
            eval = new Evaluation(dataTest);
            eval.crossValidateModel(klasifikator, dataTest, 10, new Random(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        lblInfo.setText(eval.toSummaryString("\nResults\n======\n", false));
        lblInfo.setText("Natančnost: "+String.format("%.2f", eval.pctCorrect())+"%");

        //__________________________________________________________________________________________________________________________________________________
        //PRAVI PODATKI
        //naredi podatke iz menijev
        ArrayList<MeniWeka> seznamMenijevWeka=new ArrayList<>();
        for (int i = 0; i < mDataset.getSeznamVsehMenijev().size(); i++)
        {
            seznamMenijevWeka.add(new MeniWeka(mDataset.getSeznamVsehMenijev().get(i)));
        }

        String nizPodatki = "@relation Natakar\n" +
                "\n" +
                "@attribute Dan numeric\n" +
                "@attribute Cas numeric\n" +
                "@attribute LetniCas numeric\n" +
                "@attribute Miza numeric\n" +
                "@attribute Kolicina numeric\n" +
                "@attribute Popularnost {najmanj,manj,srednje,bolj,najbolj}\n" +
                "\n" +
                "@data\n" +
                "\n";

        for (int i = 0; i < seznamMenijevWeka.size(); i++) {
            nizPodatki+=seznamMenijevWeka.get(i).getDan()+","+seznamMenijevWeka.get(i).getCas()+","+seznamMenijevWeka.get(i).getLetniCas()+","+seznamMenijevWeka.get(i).getMiza()+","+seznamMenijevWeka.get(i).getKolicina()+","+seznamMenijevWeka.get(i).getPopularnost()+"\n";
        }
        Log.d("Podatki", nizPodatki);

        //zapis podatkov v datoteko
        File datoteka = new File(context.getFilesDir(), "dataReal.arff");
        try
        {
            if(!datoteka.exists())
            {
                if(!datoteka.createNewFile()) {
                    throw new RuntimeException("Can't create file.");
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(datoteka);
            fileOutputStream.write(nizPodatki.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (IOException | RuntimeException ex)
        {
            ex.printStackTrace();
        }

        //branje podatkov iz datoteke
        reader = null;
        try {
            reader = new BufferedReader(
                    new FileReader(datoteka));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Instances dataReal = null;
        try {
            dataReal = new Instances(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // setting class attribute
        dataReal.setClassIndex(dataReal.numAttributes() - 1);
        //filter
        try
        {
            String[] options2 = new String[6];
            options2[0] = "-B";
            options2[1] = "10";
            options2[2] = "-M";
            options2[3] = "-1.0";
            options2[4] = "-R";
            options2[5] = "first-last";
            Discretize mojFilter = new Discretize();
            mojFilter.setOptions(options2);
            mojFilter.setInputFormat(dataReal);
            Instances dataNew = Filter.useFilter(dataReal, mojFilter);

            //dobi vrednosti
            for(int i=0; i<dataNew.numInstances(); i++)
            {
                double rez = klasifikator.classifyInstance(dataNew.instance(i));
                dataNew.instance(i).setClassValue(rez);
            }

            //nastavi in sortiraj dataNew
            for (int i = 0; i < dataNew.size(); i++)
            {
                seznamMenijevWeka.get(i).setPopularnost(dataNew.get(i).stringValue(dataNew.get(i).classAttribute()));
            }

            ArrayList<MeniWeka> seznam1=new ArrayList<>();
            ArrayList<MeniWeka> seznam2=new ArrayList<>();
            ArrayList<MeniWeka> seznam3=new ArrayList<>();
            ArrayList<MeniWeka> seznam4=new ArrayList<>();
            ArrayList<MeniWeka> seznam5=new ArrayList<>();

            for (int i = 0; i < seznamMenijevWeka.size(); i++) {
                String podatek=seznamMenijevWeka.get(i).getPopularnost();
                if(podatek.equals("najmanj"))
                {
                    seznam1.add(seznamMenijevWeka.get(i).vrniMeniWeka());
                }
                else if(podatek.equals("manj"))
                {
                    seznam2.add(seznamMenijevWeka.get(i).vrniMeniWeka());
                }
                else if(podatek.equals("srednje"))
                {
                    seznam3.add(seznamMenijevWeka.get(i).vrniMeniWeka());
                }
                else if(podatek.equals("bolj"))
                {
                    seznam4.add(seznamMenijevWeka.get(i).vrniMeniWeka());
                }
                else if(podatek.equals("najbolj"))
                {
                    seznam5.add(seznamMenijevWeka.get(i).vrniMeniWeka());
                }
            }

            ArrayList<MeniWeka> seznamUrejeni=new ArrayList<>();
            for (int i = 0; i < seznam5.size(); i++) {
                seznamUrejeni.add(seznam5.get(i).vrniMeniWeka());
            }
            for (int i = 0; i < seznam4.size(); i++) {
                seznamUrejeni.add(seznam4.get(i).vrniMeniWeka());
            }
            for (int i = 0; i < seznam3.size(); i++) {
                seznamUrejeni.add(seznam3.get(i).vrniMeniWeka());
            }
            for (int i = 0; i < seznam2.size(); i++) {
                seznamUrejeni.add(seznam2.get(i).vrniMeniWeka());
            }
            for (int i = 0; i < seznam1.size(); i++) {
                seznamUrejeni.add(seznam1.get(i).vrniMeniWeka());
            }

            mRecyclerView = (RecyclerView) findViewById(R.id.listElementov);
            mRecyclerView.setHasFixedSize(true);
            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            // specify an adapter (see also next example)
            mAdapter = new AdapterWeka(this, seznamUrejeni);
            mRecyclerView.setAdapter(mAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
