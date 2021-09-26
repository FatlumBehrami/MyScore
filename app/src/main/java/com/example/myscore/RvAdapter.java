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

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Competition> competitions;
    Context ctx;
    String sport;
    public RvAdapter(Context _ctx, List<Competition> competitions, String _sport){
        this.inflater = LayoutInflater.from(_ctx);
        this.competitions = competitions;
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
        holder.competitionName.setText(competitions.get(position).getName());

//        Picasso.get().load(competitions.get(position).getCoverImage()).into(holder.songCoverImage);

    }

    @Override
    public int getItemCount() {
        return competitions.size();
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
                    Intent intent = new Intent(ctx,  LeaguesActivity.class);
                    intent.putExtra("sport", sport);
                    Competition comp = competitions.get(getAdapterPosition());

                    intent.putExtra("competition", comp.getName());
//                    Toast.makeText(v.getContext(), "Do Something With this Click " + comp.getName(), Toast.LENGTH_SHORT).show();




                    ctx.startActivity(intent);
                }
            });
        }
    }
}
