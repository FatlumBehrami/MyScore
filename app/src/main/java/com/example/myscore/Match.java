package com.example.myscore;

import com.example.myscore.Team;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Calendar;

public class Match {
    Team[] Teams = new Team[2];

    @SerializedName("Eps")
    String Time = "";

    public Team[] getTeams() {
        return Teams;
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
        return "Match{" +
                "Teams=" + Arrays.toString(Teams) +
                ", Time='" + Time + '\'' +
                '}';
    }
}
