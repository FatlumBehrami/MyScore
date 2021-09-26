package com.example.myscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SingleMatchActivity extends AppCompatActivity {

    Match matchResponse;
    ImageView imgTeam1, imgTeam2;
    TextView txtTeam1, txtTeam2, txtLeague, txtVenue, txtTime, txtScore1, txtScore2, txtOffsides1, txtOffsides2, txtRedcards1, txtRedcards2, txtYellowcards1, txtYellowcards2, txtFouls1, txtFouls2, txtShotsontarget1, txtShotsontarget2, txtShotsofftarget1, txtShotsofftarget2, txtCorners1, txtCorners2, txtPossesion1, txtPossesion2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_match1);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key");

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        imgTeam1 = findViewById(R.id.imgTeam1);
        imgTeam2 = findViewById(R.id.imgTeam2);
        txtTeam1 = findViewById(R.id.txtTeam1);
        txtTeam2 = findViewById(R.id.txtTeam2);
        txtLeague = findViewById(R.id.txtLeague);
        txtVenue = findViewById(R.id.txtVenue);
        txtTime = findViewById(R.id.txtTime);
        txtScore1 = findViewById(R.id.txtScore1);
        txtScore2 = findViewById(R.id.txtScore2);
        txtCorners1 = findViewById(R.id.txtCorners1);
        txtCorners2 = findViewById(R.id.txtCorners2);
        txtOffsides1 = findViewById(R.id.txtOffsides1);
        txtOffsides2 = findViewById(R.id.txtOffsides2);
        txtPossesion1 = findViewById(R.id.txtPossession1);
        txtPossesion2 = findViewById(R.id.txtPossession2);
        txtFouls1 = findViewById(R.id.txtFouls1);
        txtFouls2 = findViewById(R.id.txtFouls2);
        txtRedcards1 = findViewById(R.id.txtRedcards1);
        txtRedcards2 = findViewById(R.id.txtRedcards2);
        txtYellowcards1 = findViewById(R.id.txtYellowcards1);
        txtYellowcards2 = findViewById(R.id.txtYellowcards2);
        txtShotsontarget1 = findViewById(R.id.txtShotsontarget1);
        txtShotsontarget2 = findViewById(R.id.txtShotsontarget2);
        txtShotsofftarget1 = findViewById(R.id.txtShotsofftarget1);
        txtShotsofftarget2 = findViewById(R.id.txtShotsofftarget2);

        final LivescoreService livescoreService = new LivescoreService(SingleMatchActivity.this);
        livescoreService.getSoccerMatch(value, new LivescoreService.MatchRequestListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(SingleMatchActivity.this,message,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onResponse(Match matchResponse) {
                txtTeam1.setText(matchResponse.Teams[0].getName());
                txtScore1.setText(String.valueOf(matchResponse.Teams[0].getScore()));
                txtTeam2.setText(matchResponse.Teams[1].getName());
                txtScore2.setText(String.valueOf(matchResponse.Teams[1].getScore()));
                txtTime.setText(matchResponse.getTime());
                txtVenue.setText(matchResponse.getVenue());
                txtLeague.setText(matchResponse.getLeague());
                txtShotsontarget1.setText(String.valueOf(matchResponse.Teams[0].Shotsontarget));
                txtShotsontarget2.setText(String.valueOf(matchResponse.Teams[1].Shotsontarget));
                txtShotsofftarget1.setText(String.valueOf(matchResponse.Teams[0].Shotsofftarget));
                txtShotsofftarget2.setText(String.valueOf(matchResponse.Teams[1].Shotsofftarget));
                txtPossesion1.setText(String.valueOf(matchResponse.Teams[0].Possesion));
                txtPossesion2.setText(String.valueOf(matchResponse.Teams[1].Possesion));
                txtFouls1.setText(String.valueOf(matchResponse.Teams[0].Fouls));
                txtFouls2.setText(String.valueOf(matchResponse.Teams[1].Fouls));
                txtYellowcards1.setText(String.valueOf(matchResponse.Teams[0].Yellowcards));
                txtYellowcards2.setText(String.valueOf(matchResponse.Teams[1].Yellowcards));
                txtRedcards1.setText(String.valueOf(matchResponse.Teams[0].Redcards));
                txtRedcards2.setText(String.valueOf(matchResponse.Teams[1].Redcards));
                txtOffsides1.setText(String.valueOf(matchResponse.Teams[0].Offsides));
                txtOffsides2.setText(String.valueOf(matchResponse.Teams[1].Offsides));
                txtCorners1.setText(String.valueOf(matchResponse.Teams[0].Corners));
                txtCorners2.setText(String.valueOf(matchResponse.Teams[1].Corners));

                imgTeam1.setImageBitmap(getImage(matchResponse.Teams[0].getIconURL()));
                imgTeam2.setImageBitmap(getImage(matchResponse.Teams[1].getIconURL()));
            }
        });
    }

    protected Bitmap getImage(String img){
        URL url = null;
        try {
            url = new URL(img);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    public void onClick(View view) {

    }
}