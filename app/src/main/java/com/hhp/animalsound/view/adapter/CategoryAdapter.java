package com.hhp.animalsound.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hhp.animalsound.R;
import com.hhp.animalsound.model.Animal;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.DetailHolder>{
    private final Context context;
    private final List<Animal> listAnimal;
    private View.OnClickListener event;

    public CategoryAdapter(Context context, List<Animal> listAnimal, View.OnClickListener event) {
        this.context = context;
        this.listAnimal = listAnimal;
        this.event = event;
    }

    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_9_animal, parent, false);
        return new DetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        int fromIndex = 9 * position;
        int toIndex = Math.min(9 * (position+1), listAnimal.size());
        List<Animal> subList = listAnimal.subList(fromIndex, toIndex);
        NineAnimalsAdapter adapter = new NineAnimalsAdapter(context, subList, event);
        holder.rvAnimal.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return (int) Math.ceil(listAnimal.size() / 9.0);
    }

    public class DetailHolder extends RecyclerView.ViewHolder{
        private RecyclerView rvAnimal;
        public DetailHolder(@NonNull View itemView) {
            super(itemView);
            rvAnimal = itemView.findViewById(R.id.rvAnimal);
        }
    }
}
