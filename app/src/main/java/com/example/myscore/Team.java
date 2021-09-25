package com.example.myscore;

public class Team {
    String Name = "";
    int Score = 0;
    String IconURL = "";

    @Override
    public String toString() {
        return "Team{" +
                "Name='" + Name + '\'' +
                ", Score=" + Score +
                ", IconURL='" + IconURL + '\'' +
                '}';
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
