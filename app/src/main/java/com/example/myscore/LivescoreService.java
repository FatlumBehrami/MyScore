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

    public interface MatchRequestListener {
        void onError(String message);

        void onResponse(Match match);
    }

    public interface VolleyCompetitionsResponseListener {
        void onError(String message);

        void onResponse(List<Competition> competitions);
    }

    public interface VolleyMatchesResponseListener {
        void onError(String message);

        void onResponse(List<Match> matches);
    }

    public void getFootballData(VolleyResponseListener volleyResponseListener) {
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
                        match.setID(mt.getString("Eid"));
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
                            if (mt.getJSONArray("T" + (k + 1)).getJSONObject(0).has("Img")){
                                team.setIconURL("https://lsm-static-prod.livescore.com/medium/" + mt.getJSONArray("T" + (k + 1)).getJSONObject(0).getString("Img"));
                            } else{
                                team.setIconURL("");
                            }
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
            volleyResponseListener.onError("Something wrong");
        }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-rapidapi-key", "87a0cafc43msh57408ba1669b6aep12b7d2jsnb800b5f0169c");
                params.put("x-rapidapi-host", "livescore6.p.rapidapi.com");

                return params;
            }
        };

        DataSingleton.getInstance(ctx).addToRequestQueue(request);
    }

    public void getBasketballData(VolleyResponseListener volleyResponseListener) {
        String url = "https://livescore6.p.rapidapi.com/matches/v2/list-live?Category=basketball";
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
                        match.setID(mt.getString("Eid"));

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
            volleyResponseListener.onError("Something wrong");
        }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-rapidapi-key", "87a0cafc43msh57408ba1669b6aep12b7d2jsnb800b5f0169c");
                params.put("x-rapidapi-host", "livescore6.p.rapidapi.com");

                return params;
            }
        };

        DataSingleton.getInstance(ctx).addToRequestQueue(request);
    }

    public void getTennisData(VolleyResponseListener volleyResponseListener){
        String url = "https://livescore6.p.rapidapi.com/matches/v2/list-live?Category=tennis";
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
                        match.setID(mt.getString("Eid"));
                        Team[] teams = new Team[2];
                        for (int k = 0; k < 2; k++) {
                            Team team = new Team();
                            team.setName(mt.getJSONArray("T" + (k + 1)).getJSONObject(0).getString("Nm"));
                            if (mt.has("Tr" + (k+1))){
                                team.setScore(Integer.parseInt(mt.getString("Tr" + (k + 1))));
                            } else{
                                team.setScore(0);
                            }
                            team.setIconURL("");
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
            volleyResponseListener.onError("Something wrong");
        }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-rapidapi-key", "87a0cafc43msh57408ba1669b6aep12b7d2jsnb800b5f0169c");
                params.put("x-rapidapi-host", "livescore6.p.rapidapi.com");

                return params;
            }
        };

        DataSingleton.getInstance(ctx).addToRequestQueue(request);
    }

    public void getSoccerMatch(String Id, MatchRequestListener MatchRequestListener) {
        String url = "https://livescore6.p.rapidapi.com/matches/v2/detail?Eid=" + Id + "&Category=soccer&LiveTable=false";
        Match match = new Match();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                match.setTime(response.getString("Eps"));

                Team[] teams = new Team[2];
                for (int k = 0; k < 2; k++) {
                    Team team = new Team();
                    team.setName(response.getJSONArray("T" + (k + 1)).getJSONObject(0).getString("Nm"));
                    if (response.has("Tr" + (k+1))){
                        team.setScore(Integer.parseInt(response.getString("Tr" + (k + 1))));
                    } else{
                        team.setScore(0);
                    }
                    team.setIconURL("https://lsm-static-prod.livescore.com/medium/" + response.getJSONArray("T" + (k + 1)).getJSONObject(0).getString("Img"));
                    JSONArray vargu = response.getJSONArray("Stat");
                    JSONObject teamStats = vargu.getJSONObject(k);
                    team.Corners = Integer.parseInt(teamStats.getString("Cos"));
                    team.Fouls = Integer.parseInt(teamStats.getString("Fls"));
                    team.Offsides = Integer.parseInt(teamStats.getString("Ofs"));
                    team.Yellowcards = Integer.parseInt(teamStats.getString("Ycs"));
                    team.Redcards = Integer.parseInt(teamStats.getString("Rcs"));
                    team.Possesion = Integer.parseInt(teamStats.getString("Pss"));
                    team.Shotsontarget = Integer.parseInt(teamStats.getString("Shon"));
                    team.Shotsofftarget = Integer.parseInt(teamStats.getString("Shof"));
                    teams[k] = team;
                }
                match.setVenue(response.getString("Vnm"));
                JSONObject vargu = response.getJSONObject("Stg");
                match.setLeague(vargu.getString("Csnm"));
                match.setTeams(teams);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            MatchRequestListener.onResponse(match);
        }, error -> {{
//            Toast.makeText(ctx, "Something wrong.", Toast.LENGTH_SHORT).show();
            MatchRequestListener.onError("Something went wrong!");
        }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-rapidapi-key", "87a0cafc43msh57408ba1669b6aep12b7d2jsnb800b5f0169c");
                params.put("x-rapidapi-host", "livescore6.p.rapidapi.com");

                return params;
            }
        };

        DataSingleton.getInstance(ctx).addToRequestQueue(request);
    }

    public void getCompetitionsBySport(String sport, VolleyCompetitionsResponseListener volleyCompetitionsResponseListener) {
        String url = "https://livescore6.p.rapidapi.com/leagues/v2/list?Category=" + sport;
        ArrayList<Competition> competitions = new ArrayList<>();
        ArrayList<League> leagues = new ArrayList<>();


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
//                Toast.makeText(ctx, "here 3", Toast.LENGTH_SHORT).show();
                JSONObject obj;
                JSONArray vargu = response.getJSONArray("Ccg");
//                Toast.makeText(ctx, obj.toString(), Toast.LENGTH_SHORT).show();


                Competition competition;
                for (int i = 0; i < vargu.length(); i++) {
                    obj = vargu.getJSONObject(i);
                    competition = new Competition();
                    competition.setId(obj.getString("Cid"));
                    competition.setName(obj.getString("Cnm"));
                    competitions.add(competition);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            volleyCompetitionsResponseListener.onResponse(competitions);

        }, error -> {{
            Toast.makeText(ctx, "Something wrong.", Toast.LENGTH_SHORT).show();
            volleyCompetitionsResponseListener.onError("Something wrong");
        }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-rapidapi-key", "87a0cafc43msh57408ba1669b6aep12b7d2jsnb800b5f0169c");
                params.put("x-rapidapi-host", "livescore6.p.rapidapi.com");

                return params;
            }
        };

        DataSingleton.getInstance(ctx).addToRequestQueue(request);

    }

    public void getLeaguesByCompetition(String sport, String competition, VolleyResponseListener volleyResponseListener) {
        String url = "https://livescore6.p.rapidapi.com/leagues/v2/list?Category=" + sport;
        ArrayList<Competition> competitions = new ArrayList<>();
        ArrayList<League> leagues = new ArrayList<>();


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject obj;
                JSONArray vargu = response.getJSONArray("Ccg");
//                Toast.makeText(ctx, obj.toString(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < vargu.length(); i++) {
                    obj = vargu.getJSONObject(i);
                    JSONObject objLeague;
                    JSONArray vargu2 = obj.getJSONArray("Stages");
                    League league;
                    if(obj.getString("Cnm").equals(competition)) {
                        for (int j = 0; j < vargu2.length(); j++) {
                            objLeague = vargu2.getJSONObject(j);
                            league = new League();
                            league.setId(objLeague.getString("Sid"));
                            league.setLeague(objLeague.getString("Sdn"));
                            league.setLeagueCode(objLeague.getString("Scd"));
//                            Toast.makeText(ctx, league.getLeagueCode(), Toast.LENGTH_SHORT).show();
                            leagues.add(league);
                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            volleyResponseListener.onResponse(leagues);

        }, error -> {{
            Toast.makeText(ctx, "Something wrong.", Toast.LENGTH_SHORT).show();
            volleyResponseListener.onError("Something wrong");
        }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-rapidapi-key", "87a0cafc43msh57408ba1669b6aep12b7d2jsnb800b5f0169c");
                params.put("x-rapidapi-host", "livescore6.p.rapidapi.com");

                return params;
            }
        };

        DataSingleton.getInstance(ctx).addToRequestQueue(request);

    }

    public void getMatchesByLeague(String sport, String _leagueCode, VolleyMatchesResponseListener volleyMatchesResponseListener) {
        String url = "https://livescore6.p.rapidapi.com/matches/v2/list-by-league?Category=" + sport + "&" + "Ccd=" + _leagueCode;

        List<Match> matches = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject obj;

                JSONArray vargu = response.getJSONArray("Stages");


                for (int i = 0; i < vargu.length(); i++) {
                    obj = vargu.getJSONObject(i);
                    JSONObject objMatch;
                    Match match = new Match();
                    if(obj.getString("Ccd").equals(_leagueCode)) {
                        JSONArray vargu2 = obj.getJSONArray("Events");
                        for (int j = 0; j < vargu2.length(); j++) {
                            objMatch = vargu2.getJSONObject(j);
                            match = new Match();
                            match.setID(objMatch.getString("Eid"));
                            Toast.makeText(ctx, match.getID(), Toast.LENGTH_SHORT).show();
                            matches.add(match);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            volleyMatchesResponseListener.onResponse(matches);
        }, error -> {{
//            Toast.makeText(ctx, "Something wrong.", Toast.LENGTH_SHORT).show();
            volleyMatchesResponseListener.onError("Something wrong");
        }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-rapidapi-key", "87a0cafc43msh57408ba1669b6aep12b7d2jsnb800b5f0169c");
                params.put("x-rapidapi-host", "livescore6.p.rapidapi.com");

                return params;
            }
        };

        DataSingleton.getInstance(ctx).addToRequestQueue(request);
    }

}



