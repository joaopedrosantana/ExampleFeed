package com.example.rosangela.examplefeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rosangela.examplefeed.models.Brewery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rosangela on 04/09/2017.
 */

public class AdapterState  extends RecyclerView.Adapter<AdapterState.MyViewHolder> {
    private List<Brewery> dataSet;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);



        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textViewName.setText(dataSet.get(position).getName());
        holder.textAdress.setText(dataSet.get(position).getFormattedAddress());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textAdress;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textAdress = (TextView) itemView.findViewById(R.id.text_adress);
        }
    }
    public AdapterState(List<Brewery> dataSet) {
        this.dataSet = dataSet;
    }
}
