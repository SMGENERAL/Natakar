package com.zigabincl.com.natakar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.Result;

import java.io.IOException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Activity_code extends AppCompatActivity {
    private SurfaceView cameraView;
    private TextView barcodeID;
    private TextView barcodeDATA;
    private CameraSource cameraSource;
    private BarcodeDetector barcodeDetector;
    private Activity ac;
    Podatki app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_code);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ac=this;

        //back gumb
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        app = (Podatki) getApplication();

        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        barcodeID = (TextView) findViewById(R.id.code_id);
        barcodeDATA = (TextView) findViewById(R.id.code_data);

        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(640, 480).build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ContextCompat.checkSelfPermission(ac, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ac,
                                new String[]{android.Manifest.permission.CAMERA},1);
                    }
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    barcodeID.post(new Runnable() {
                        public void run() {
                            barcodeID.setText("ID: "+barcodes.valueAt(0).displayValue);
                            //isci v zgodovini narocil.
                            Boolean nasel=false;
                            int idQR=Integer.parseInt(barcodes.valueAt(0).displayValue);
                            for (int i = 0; i < app.getAll().getSeznamZgodovina().size(); i++) {
                                int id=app.getAll().getSeznamZgodovina().get(i).getId();
                                if(id==idQR)
                                {
                                    nasel=true;
                                    String niz=app.getAll().getSeznamZgodovina().get(i).getIme()+" | Cena: "+String.format("%.2f", app.getAll().getSeznamZgodovina().get(i).getSkupnaCena())+"€ | Lokacija: "+app.getAll().getSeznamZgodovina().get(i).getLokacija()+"\n"+"Meniji: "+"\n";
                                    for (int j = 0; j < app.getAll().getSeznamZgodovina().get(i).getSeznamMenijev().size(); j++) {
                                        niz+=app.getAll().getSeznamZgodovina().get(i).getSeznamMenijev().get(j).getKolicina()+"x"+app.getAll().getSeznamZgodovina().get(i).getSeznamMenijev().get(j).getIme()+"\n";
                                    }
                                    barcodeDATA.setText(niz);
                                    break;
                                }
                            }
                            if(!nasel)
                            {
                                barcodeDATA.setText("Narocila nisem nasel v bazi.");
                            }
                        }
                    });
                }
            }
        });
    }
}
