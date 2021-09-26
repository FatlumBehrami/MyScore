package com.example.myscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btn_football, btn_basketball, btn_tennis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_football = findViewById(R.id.btn_football);
        btn_basketball = findViewById(R.id.btn_basketball);
        btn_tennis = findViewById(R.id.btn_tennis);




        btn_football.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, SportActivity.class);
            myIntent.putExtra("key", "soccer"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        });

        btn_basketball.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, SportActivity.class);
            myIntent.putExtra("key", "basketball"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        });

        btn_tennis.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, SportActivity.class);
            myIntent.putExtra("key", "tennis"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        });


    }
}