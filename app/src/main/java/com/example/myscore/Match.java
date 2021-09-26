package com.example.myscore;

import com.example.myscore.Team;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Calendar;

public class Match {
    String ID = "";

    Team[] Teams = new Team[2];

    String Venue = "";

    String League = "";

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @SerializedName("Eps")
    String Time = "";

    public Team[] getTeams() {
        return Teams;
    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public String getLeague() {
        return League;
    }

    public void setLeague(String league) {
        League = league;
    }

    public void setTeams(Team[] teams) {
        Teams = teams;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return  Teams[0].toString() + Teams[1].toString() + "Time " + Time;
    }
}
