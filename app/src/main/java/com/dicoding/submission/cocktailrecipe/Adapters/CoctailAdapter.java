package com.dicoding.submission.cocktailrecipe.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicoding.submission.cocktailrecipe.Models.CocktailModel;
import com.dicoding.submission.cocktailrecipe.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoctailAdapter extends RecyclerView.Adapter<CoctailAdapter.CoctailViewHolder> {

    private List<CocktailModel> cocktailModels = new ArrayList<>();
    @NonNull
    @Override
    public CoctailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coctail_item, parent, false);

        return new CoctailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoctailViewHolder holder, int position) {
        CocktailModel cocktailModel = cocktailModels.get(position);

        holder.textViewCocktail.setText(cocktailModel.getName());
        Glide.with(holder.itemView.getContext())
                .load(cocktailModel.getUriImage())
                .into(holder.imageViewCocktail);
    }

    @Override
    public int getItemCount() {
        return cocktailModels.size();
    }

    class CoctailViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView_coctail)
        ImageView imageViewCocktail;

        @BindView(R.id.textView_coctail)
        TextView textViewCocktail;

        CoctailViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
