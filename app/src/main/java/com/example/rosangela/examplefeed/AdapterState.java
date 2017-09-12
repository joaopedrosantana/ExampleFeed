package com.example.rosangela.examplefeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rosangela.examplefeed.models.Brewery;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Rosangela on 04/09/2017.
 */

public class AdapterState extends RecyclerView.Adapter<AdapterState.MyViewHolder> {
    private List<Brewery> dataSet;
    private Context context;
    private String distance;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textAdress, textDistance;
        ImageView imageBrewery;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textAdress = (TextView) itemView.findViewById(R.id.text_adress);
            this.imageBrewery = (ImageView) itemView.findViewById(R.id.img_brewery);
            this.textDistance = (TextView) itemView.findViewById(R.id.text_distance);
        }
    }

    public AdapterState(List<Brewery> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;

    }

    @Override
    public AdapterState.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textViewName.setText(dataSet.get(position).getName());
        holder.textAdress.setText(dataSet.get(position).getFormattedAddress());
        Picasso.with(context)
                .load(dataSet.get(position).getLogo())
                .resize(500, 250)
                .centerCrop()
                .into(holder.imageBrewery);
        DecimalFormat dFormat = new DecimalFormat("0.0");
        distance = dFormat.format(dataSet.get(position).getDistance());
        holder.textDistance.setText(distance + " KM");
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
