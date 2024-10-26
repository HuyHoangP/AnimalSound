package com.hhp.animalsound.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hhp.animalsound.R;
import com.hhp.animalsound.model.Animal;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailHolder>{
    private final Context context;
    private final List<Animal> listAnimal;
    private View.OnClickListener event;

    public DetailAdapter(Context context, List<Animal> listAnimal, View.OnClickListener event) {
        this.context = context;
        this.listAnimal = listAnimal;
        this.event = event;
    }

    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail, parent, false);
        return new DetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        Animal animal = listAnimal.get(position);
        holder.ivAnimal.setImageBitmap(animal.getImage());
        View.OnClickListener clickDetail = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                view.setTag(animal);
                event.onClick(view);
            }
        };

        holder.ivDownload.setOnClickListener(clickDetail);
        holder.ivPlay.setOnClickListener(clickDetail);
        holder.ivSearch.setOnClickListener(clickDetail);
    }

    @Override
    public int getItemCount() {
        return listAnimal.size();
    }

    public class DetailHolder extends RecyclerView.ViewHolder{
        ImageView ivAnimal;
        ImageView ivPlay;
        ImageView ivSearch;
        ImageView ivDownload;
        public DetailHolder(@NonNull View itemView) {
            super(itemView);
            ivAnimal = itemView.findViewById(R.id.ivAnimal);
            ivPlay = itemView.findViewById(R.id.ivPlay);
            ivSearch = itemView.findViewById(R.id.ivSearch);
            ivDownload = itemView.findViewById(R.id.ivDownload);

        }
    }
}
