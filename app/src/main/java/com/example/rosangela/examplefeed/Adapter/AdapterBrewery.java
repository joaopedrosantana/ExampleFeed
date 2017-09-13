package com.example.rosangela.examplefeed.Adapter;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rosangela.examplefeed.Activity.DetailsBreweryActivity;
import com.example.rosangela.examplefeed.Activity.MainActivity;
import com.example.rosangela.examplefeed.R;
import com.example.rosangela.examplefeed.models.Brewery;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Rosangela on 04/09/2017.
 */

public class AdapterBrewery extends RecyclerView.Adapter<AdapterBrewery.MyViewHolder> {
    private List<Brewery> dataSet;
    public Context context;
    private String distance;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textAdress, textDistance;
        ImageView imageBrewery;
        CircleImageView imageLogo;

        public MyViewHolder(final View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textAdress = (TextView) itemView.findViewById(R.id.text_adress);
            this.imageBrewery = (ImageView) itemView.findViewById(R.id.img_brewery);
            this.imageLogo = (CircleImageView) itemView.findViewById(R.id.image_logo);
            this.textDistance = (TextView) itemView.findViewById(R.id.text_distance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Verificação", Toast.LENGTH_LONG).show();

                }


            });
        }
    }

    public AdapterBrewery(List<Brewery> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }


    @Override
    public AdapterBrewery.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
                .load(dataSet.get(position).getPhoto())
                .resize(500, 300)
                .centerCrop()
                .into(holder.imageBrewery);
        Picasso.with(context)
                .load(dataSet.get(position).getLogo())
                .resize(100, 100)
                .centerCrop()
                .into(holder.imageLogo);
        DecimalFormat dFormat = new DecimalFormat("0.0");
        distance = dFormat.format(dataSet.get(position).getDistance());
        holder.textDistance.setText(distance + " KM");

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
