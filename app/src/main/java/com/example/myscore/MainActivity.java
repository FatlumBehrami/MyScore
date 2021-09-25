package com.example.myscore;

import androidx.appcompat.app.AppCompatActivity;

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
    ListView lv_scores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_football = findViewById(R.id.btn_football);
        btn_basketball = findViewById(R.id.btn_basketball);
        btn_tennis = findViewById(R.id.btn_tennis);

        final LivescoreService livescoreService = new LivescoreService(MainActivity.this);

        lv_scores.setClickable(true);
        lv_scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_football.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this,"NO",Toast.LENGTH_LONG);
        });

        btn_basketball.setOnClickListener(v -> {


            livescoreService.getFootballData(new LivescoreService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onResponse(List<League> leagues) {
                    ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, leagues);
                    lv_scores.setAdapter(adapter);
                }
            });
        });

        btn_tennis.setOnClickListener(v -> Toast.makeText(MainActivity.this,"Click 3",Toast.LENGTH_LONG).show());






    }
}