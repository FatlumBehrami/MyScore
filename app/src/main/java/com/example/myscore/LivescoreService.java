package com.example.myscore;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivescoreService {

    Context ctx;

    public LivescoreService(Context ctx) {
        this.ctx = ctx;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(List<League> leagues);
    }

    public List<League> getFootballData(VolleyResponseListener volleyResponseListener) {
        String url = "https://livescore6.p.rapidapi.com/matches/v2/list-live?Category=soccer";
        List<League> leagues = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray vargu = response.getJSONArray("Stages");
                League league;
                JSONObject obj;
                Match match;

                for (int i = 0; i < vargu.length(); i++) {
                    obj = vargu.getJSONObject(i);
                    league = new League();
                    league.setId(obj.getString("Sid"));
                    league.setLeague(obj.getString("Snm"));
                    league.setCountry(obj.getString("Cnm"));

                    List<Match> matches = new ArrayList<>();
                    JSONArray events = obj.getJSONArray("Events");

                    for (int j = 0; j < events.length(); j++) {
                        match = new Match();

                        JSONObject mt = events.getJSONObject(j);

                        match.setTime(mt.getString("Eps"));

                        Team[] teams = new Team[2];
                        for (int k = 0; k < 2; k++) {
                            Team team = new Team();
                            team.setName(mt.getJSONArray("T" + (k + 1)).getJSONObject(0).getString("Nm"));
                            if (mt.has("Tr" + (k+1))){
                                team.setScore(Integer.parseInt(mt.getString("Tr" + (k + 1))));
                            } else{
                                team.setScore(0);
                            }
                            team.setIconURL("https://lsm-static-prod.livescore.com/medium/" + mt.getJSONArray("T" + (k + 1)).getJSONObject(0).getString("Img"));
                            teams[k] = team;
                        }

                        match.setTeams(teams);
                        matches.add(match);
                    }
                    league.setMatches(matches);
                    leagues.add(league);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            volleyResponseListener.onResponse(leagues);
        }, error -> {{
//            Toast.makeText(ctx, "Something wrong.", Toast.LENGTH_SHORT).show();
            volleyResponseListener.onError("Something wrong");
        }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-rapidapi-key", "62183c7184msh10a9b9ba8656549p1d00bfjsn1b0561c178bb");
                params.put("x-rapidapi-host", "livescore6.p.rapidapi.com");

                return params;
            }
        };

        DataSingleton.getInstance(ctx).addToRequestQueue(request);

        return leagues;
    }


    public void getBasketballData() {


    }
}
//    public List<TennisModel> getTennisData(){
//
//    }

