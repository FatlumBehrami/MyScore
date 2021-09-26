package com.example.myscore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity{

    ListView lv_scores;

    final LivescoreService livescoreService = new LivescoreService(MatchesActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);


        Intent intent = getIntent();
        String value = intent.getStringExtra("key");
        String leagueCode = intent.getStringExtra("leagueCode");
        lv_scores = findViewById(R.id.lv_matches);

        if(leagueCode == null) {
            if (value.equals("soccer")) {
                livescoreService.getFootballData(new LivescoreService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MatchesActivity.this, message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(List<League> leagues) {
                        ArrayAdapter adapter = new ArrayAdapter(MatchesActivity.this, android.R.layout.simple_list_item_1, listMatches(leagues));
                        lv_scores.setAdapter(adapter);
                    }
                });
                lv_scores.setOnItemClickListener((parent, view, position, id) -> {
                    Match obj = (Match) lv_scores.getAdapter().getItem(position);
                    String matchID = obj.getID();
                    Intent myIntent = new Intent(MatchesActivity.this, SingleMatchActivity.class);
                    myIntent.putExtra("key", matchID); //Optional parameters
                    MatchesActivity.this.startActivity(myIntent);
                });

            } else if (value.equals("basketball")) {
                livescoreService.getBasketballData(new LivescoreService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MatchesActivity.this, message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(List<League> leagues) {
                        ArrayAdapter adapter = new ArrayAdapter(MatchesActivity.this, android.R.layout.simple_list_item_1, listMatches(leagues));
                        lv_scores.setAdapter(adapter);
                    }
                });
            } else if (value.equals("tennis")) {
                livescoreService.getTennisData(new LivescoreService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MatchesActivity.this, message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(List<League> leagues) {
                        ArrayAdapter adapter = new ArrayAdapter(MatchesActivity.this, android.R.layout.simple_list_item_1, listMatches(leagues));
                        lv_scores.setAdapter(adapter);
                    }
                });
                ;
            }
        }
        else {
            livescoreService.getMatchesByLeague(value, leagueCode, new LivescoreService.VolleyMatchesResponseListener() {
                @Override
                public void onError(String message) {
                    Toast.makeText(MatchesActivity.this,message,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onResponse(List<Match> matches) {
                    if(matches.size() == 0){
                        Toast.makeText(MatchesActivity.this, "No available match info for this league", Toast.LENGTH_LONG).show();
                    }else{
                    ArrayAdapter adapter = new ArrayAdapter(MatchesActivity.this, android.R.layout.simple_list_item_1, matches);
                    lv_scores.setAdapter(adapter);
                    }
                }
            });
        }
    }

    public List<Match> listMatches(List<League> leagues){
        List<Match> adapterList = new ArrayList<>();

        for(int i = 0; i < leagues.size(); i++){
            League league = leagues.get(i);
            for( Object mt : league.getMatches()){
                adapterList.add((Match)mt);
            }
        }


        return adapterList;

    }
}