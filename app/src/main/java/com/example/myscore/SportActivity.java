package com.example.myscore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class SportActivity extends AppCompatActivity {
    Button btn_liveMatches;
    RecyclerView rv_competitions;
    RvAdapter rvAdapter;
    final LivescoreService livescoreService = new LivescoreService(SportActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport1);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key");
        btn_liveMatches = findViewById(R.id.btn_liveMatches);


        btn_liveMatches.setOnClickListener(v -> {
            Intent myIntent = new Intent(SportActivity.this, MatchesActivity.class);
            myIntent.putExtra("key", value);
            SportActivity.this.startActivity(myIntent);
        });

        rv_competitions = findViewById(R.id.rv_competitions);

        livescoreService.getCompetitionsBySport(value, new LivescoreService.VolleyCompetitionsResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(SportActivity.this,message,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(List<Competition> competitions) {
                rv_competitions.setLayoutManager(new LinearLayoutManager(SportActivity.this));
                rvAdapter = new RvAdapter(SportActivity.this, competitions, value);
                rv_competitions.setAdapter(rvAdapter);
            }
        });

    }
}