package com.example.myscore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LeaguesActivity extends AppCompatActivity {
    RecyclerView rv_leagues;
    RvLeaguesAdapter rvLeaguesAdapter;
    public String sport;
    String competition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);

        rv_leagues = findViewById(R.id.rv_leagues);

        Intent intent = getIntent();
        sport = intent.getStringExtra("sport");
        competition = intent.getStringExtra("competition");

        final LivescoreService livescoreService = new LivescoreService(LeaguesActivity.this);

        livescoreService.getLeaguesByCompetition(sport, competition, new LivescoreService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(LeaguesActivity.this,message,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(List<League> leagues) {
                rv_leagues.setLayoutManager(new LinearLayoutManager(LeaguesActivity.this));
                rvLeaguesAdapter = new RvLeaguesAdapter(LeaguesActivity.this, leagues, sport);
                rv_leagues.setAdapter(rvLeaguesAdapter);
            }
        });



    }
}