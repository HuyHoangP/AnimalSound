package com.hhp.animalsound.view.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hhp.animalsound.R;
import com.hhp.animalsound.model.Animal;

import java.util.List;

public class NineAnimalsAdapter extends RecyclerView.Adapter<NineAnimalsAdapter.DetailHolder>{
    private final Context context;
    private final List<Animal> listAnimal;
    private View.OnClickListener event;

    public NineAnimalsAdapter(Context context, List<Animal> listAnimal, View.OnClickListener event) {
        this.context = context;
        this.listAnimal = listAnimal;
        this.event = event;
    }

    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_animal, parent, false);
        return new DetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        Animal animal = listAnimal.get(position);
            View.OnClickListener clickDetail = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                    event.onClick(view);
                }
            };
            holder.ivAnimal.setOnClickListener(clickDetail);
            holder.ivAnimal.setImageBitmap(animal.getImage());
            holder.ivAnimal.setTag(animal);
        }

    @Override
    public int getItemCount() {
        return Math.min(listAnimal.size(), 9);
    }

    public class DetailHolder extends RecyclerView.ViewHolder{

        private ImageView ivAnimal;
        public DetailHolder(@NonNull View itemView) {
            super(itemView);
           ivAnimal = itemView.findViewById(R.id.ivAnimal);
        }
    }
}
