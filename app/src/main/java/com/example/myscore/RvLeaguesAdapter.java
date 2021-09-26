package com.example.myscore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RvLeaguesAdapter extends RecyclerView.Adapter<RvLeaguesAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<League> leagues;
    Context ctx;
    String sport;
    public RvLeaguesAdapter(Context _ctx, List<League> leagues, String _sport){
        this.inflater = LayoutInflater.from(_ctx);
        this.leagues = leagues;
        this.ctx = _ctx;
        this.sport = _sport;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the data
        holder.competitionName.setText(leagues.get(position).getLeague());

//        Picasso.get().load(competitions.get(position).getCoverImage()).into(holder.songCoverImage);

    }

    @Override
    public int getItemCount() {
        return leagues.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView competitionName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            competitionName = itemView.findViewById(R.id.competitionName);

            // handle onClick

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "Do Something With this Click" + ctx, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ctx,  MatchesActivity.class);
                    intent.putExtra("key", sport);
                    League l = leagues.get(getAdapterPosition());

                    intent.putExtra("leagueCode", l.getLeagueCode());
//                    Toast.makeText(v.getContext(), "Do Something With this Click " + comp.getName(), Toast.LENGTH_SHORT).show();




                    ctx.startActivity(intent);
                }
            });
        }
    }
}
