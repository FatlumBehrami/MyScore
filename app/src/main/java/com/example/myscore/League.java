package com.example.myscore;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class League {

    @SerializedName("Sid")
    String Id = "";

    @SerializedName("Snm")
    String League = "";

    @SerializedName("Cnm")
    String Country = "";

    @Override
    public String toString() {
        return "League{" +
                "Id='" + Id + '\'' +
                ", League='" + League + '\'' +
                ", Country='" + Country + '\'' +
                ", Matches=" + Matches +
                '}';
    }

    List<Match> Matches = new ArrayList<>();

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLeague() {
        return League;
    }

    public void setLeague(String league) {
        League = league;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public List<?> getMatches() {
        return Matches;
    }

    public void setMatches(List<Match> matches) {
        Matches = matches;
    }
}
