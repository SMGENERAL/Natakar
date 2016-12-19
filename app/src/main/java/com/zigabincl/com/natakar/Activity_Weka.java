package com.zigabincl.com.natakar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Activity_Weka extends AppCompatActivity {
    public TextView lblInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity__weka);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //back gumb
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lblInfo = (TextView) findViewById(R.id.lblInfo);

        //gumb prikaži podatke
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //PREBERI PODATKE IZ ARFF DATOTEKE
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(
                            new FileReader("/storage/82BD-1C08/emulated/0/data.arff"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Instances data = null;

                try {
                    data = new Instances(reader);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // setting class attribute
                data.setClassIndex(data.numAttributes() - 1);

                //DELAJ UČENJE
                String[] options = new String[1];
                options[0] = "-U";
                J48 tree = new J48();         // new instance of tree
                try {
                    tree.setOptions(options);     // set the options
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    tree.buildClassifier(data);   // build classifier
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //DOBI REZULTATE
                Evaluation eval = null;
                try {
                    eval = new Evaluation(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    eval.crossValidateModel(tree, data, 10, new Random(1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lblInfo.setText(eval.toSummaryString("\nResults\n======\n", false));
            }
        });
    }
}
