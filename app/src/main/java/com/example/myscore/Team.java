package com.example.myscore;

public class Team {
    String Name = "";
    int Score = 0;
    String IconURL = "";
    int Shotsontarget = 0;
    int Shotsofftarget = 0;
    int Possesion = 0;
    int Fouls = 0;
    int Yellowcards = 0;
    int Redcards = 0;
    int Offsides = 0;
    int Corners = 0;

    public int getShotsontarget() {
        return Shotsontarget;
    }

    public void setShotsontarget(int shotsontarget) {
        Shotsontarget = shotsontarget;
    }

    public int getShotsofftarget() {
        return Shotsofftarget;
    }

    public void setShotsofftarget(int shotsofftarget) {
        Shotsofftarget = shotsofftarget;
    }

    public int getPossesion() {
        return Possesion;
    }

    public void setPossesion(int possesion) {
        Possesion = possesion;
    }

    public int getFouls() {
        return Fouls;
    }

    public void setFouls(int fouls) {
        Fouls = fouls;
    }

    public int getYellowcards() {
        return Yellowcards;
    }

    public void setYellowcards(int yellowcards) {
        Yellowcards = yellowcards;
    }

    public int getRedcards() {
        return Redcards;
    }

    public void setRedcards(int redcards) {
        Redcards = redcards;
    }

    public int getOffsides() {
        return Offsides;
    }

    public void setOffsides(int offsides) {
        Offsides = offsides;
    }

    public int getCorners() {
        return Corners;
    }

    public void setCorners(int corners) {
        Corners = corners;
    }

    @Override
    public String toString() {
        return "Team: " + Name + " - " + Score + "\n";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getIconURL() {
        return IconURL;
    }

    public void setIconURL(String iconURL) {
        IconURL = iconURL;
    }
}
