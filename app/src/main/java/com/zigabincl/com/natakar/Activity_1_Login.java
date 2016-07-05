package com.zigabincl.com.natakar;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Activity_1_Login extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks,
        View.OnClickListener {

    private Podatki vsiPodatki = new Podatki();
    public static final String LOGOUT = "LOGOUT";
    private static final String TAG = "Activity_1_Login";
    private static final int RC_SIGN_IN = 9001;
    private ProgressDialog mProgressDialog;
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressBar loading;
    private ImageView profilePicture;
    private boolean forceLogout;
    public Activity ac;
    public boolean slikaOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //dovoljenja
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CAMERA},1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        this.ac=this;
        this.slikaOK=false;
        profilePicture=(ImageView)  findViewById(R.id.slikaProfila);
        loading = (ProgressBar) findViewById(R.id.progressBar);
        mStatusTextView = (TextView) findViewById(R.id.lblStatus);
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        vsiPodatki = (Podatki) getApplication();
        vsiPodatki.save();

        if(vsiPodatki.getAll().getUporabnik().getId()!="")
        {
            final DownloadImageTask naloga=(DownloadImageTask)new DownloadImageTask((ImageView) findViewById(R.id.slikaProfila)).execute(vsiPodatki.getAll().getUporabnik().getSlikaUrl());

            //Declare the timer
            final Timer t = new Timer();
            t.scheduleAtFixedRate(new TimerTask() {
                                      @Override
                                      public void run() {
                                          //Called each time
                                          if (naloga.getStatus() == AsyncTask.Status.FINISHED) {
                                              t.cancel();
                                              slikaOK=true;
                                          }
                                      }
                                  },
                    //Set how long before to start calling the TimerTask (in milliseconds)
                    0,
                    //Set the amount of time between each execution (in milliseconds)
                    100);
        }

        mStatusTextView.setText(vsiPodatki.loginStatusText);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Odhajate?")
                .setMessage("Ali resno želite zapreti aplikacijo?")
                .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("NE", null)
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                loading.setVisibility(View.VISIBLE);
                mStatusTextView.setText("Loading...");
                signIn();
                break;
            /*
            case R.id.sign_out_button:
                signOut();
                break;
            case R.id.disconnect_button:
                revokeAccess();
                break;
            */
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            vsiPodatki.loginStatusText = "Prijavljeni kot: " + acct.getDisplayName();

            if (vsiPodatki.getAll().getUporabnik().getId() != acct.getId())   //ni shranjen
            {
                vsiPodatki.getAll().getUporabnik().setId(acct.getId());
                vsiPodatki.getAll().getUporabnik().setIme(acct.getDisplayName());
                vsiPodatki.getAll().getUporabnik().setEmail(acct.getEmail());
                vsiPodatki.getAll().getUporabnik().setSlikaUrl(acct.getPhotoUrl().toString());
                vsiPodatki.save();
            }


            if (slikaOK == false) {
                final DownloadImageTask naloga = (DownloadImageTask) new DownloadImageTask((ImageView) findViewById(R.id.slikaProfila)).execute(acct.getPhotoUrl().toString());

                if (!forceLogout) {

                    //Declare the timer
                    final Timer t = new Timer();
                    t.scheduleAtFixedRate(new TimerTask() {
                                              @Override
                                              public void run() {
                                                  //Called each time
                                                  if (naloga.getStatus() == AsyncTask.Status.FINISHED) {
                                                      t.cancel();
                                                      Intent druga = new Intent(ac, Activity_2_Mize.class);
                                                      startActivity(druga);
                                                  }
                                              }
                                          },
                            //Set how long before to start calling the TimerTask (in milliseconds)
                            0,
                            //Set the amount of time between each execution (in milliseconds)
                            100);
                } else {
                    // Signed out, show unauthenticated UI.
                    mStatusTextView.setText("NAPAKA. Preverite omrezje.");
                }
            }
            else
            {
                if (!forceLogout) {
                    Intent druga = new Intent(ac, Activity_2_Mize.class);
                    startActivity(druga);
                } else {
                    // Signed out, show unauthenticated UI.
                    mStatusTextView.setText("NAPAKA. Preverite omrezje.");
                }
            }
        }

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("ERROR: Picture, ", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (forceLogout) {
            forceLogout = false; //only when it is called from different activity
            signOut();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    private void updateUI(boolean signedIn) {

    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop(): disconnecting");
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        vsiPodatki.getAll().getUporabnik().setId("");
                        vsiPodatki.getAll().getUporabnik().setIme("");
                        vsiPodatki.getAll().getUporabnik().setEmail("");
                        vsiPodatki.getAll().getUporabnik().setSlikaUrl(null);
                        vsiPodatki.save();
                        profilePicture.setImageDrawable(ac.getDrawable(R.drawable.userguest));
                        slikaOK=false;
                        vsiPodatki.loginStatusText="Odjavljeni.";
                        mStatusTextView.setText("Odjavljeni.");
                        // [END_EXCLUDE]
                    }
                });
    }
}


