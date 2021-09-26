package com.example.myscore;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Competition {
    @SerializedName("Cid")
    String Id = "";

    @SerializedName("Cnm")
    String Name = "";

    @Override
    public String toString() {
        return "League{" +
                "Id='" + Id + '\'' +
                ", name='" + Name + '\'' +
                ", Matches=" + Leagues +
                '}';
    }

    ArrayList<League> Leagues = new ArrayList<>();

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getName() {
        return Name;
    }

    public ArrayList<League> getLeagues() {
        return Leagues;
    }

    public void setLeagues(ArrayList<League> leagues) {
        Leagues = leagues;
    }
}
