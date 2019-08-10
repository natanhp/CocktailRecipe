package com.dicoding.submission.cocktailrecipe.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.submission.cocktailrecipe.Models.CocktailModel;
import com.dicoding.submission.cocktailrecipe.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoctailAdapter extends RecyclerView.Adapter<CoctailAdapter.CoctailViewHolder> {

    private List<CocktailModel> cocktailModels = new ArrayList<>();
    private OnEventClickListener onEventClickListener;

    public CoctailAdapter(OnEventClickListener onEventClickListener) {
        this.onEventClickListener = onEventClickListener;
    }

    @NonNull
    @Override
    public CoctailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coctail_item, parent, false);

        return new CoctailViewHolder(view, onEventClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CoctailViewHolder holder, int position) {
        CocktailModel cocktailModel = cocktailModels.get(position);

        holder.textViewCocktail.setText(cocktailModel.getName());
        Glide.with(holder.itemView.getContext())
                .load(cocktailModel.getUriImage())
                .apply(new RequestOptions().override(400, 400))
                .into(holder.imageViewCocktail);
    }

    @Override
    public int getItemCount() {
        return cocktailModels.size();
    }

    class CoctailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imageView_coctail)
        ImageView imageViewCocktail;

        @BindView(R.id.textView_coctail)
        TextView textViewCocktail;

        OnEventClickListener onEventClickListener;

        CoctailViewHolder(@NonNull View itemView, OnEventClickListener onEventClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            onEventClickListener.onEventClick(getAdapterPosition());
        }
    }

    public void setCocktailModels(List<CocktailModel> cocktailModels) {
        this.cocktailModels = cocktailModels;
        notifyDataSetChanged();
    }

    public interface OnEventClickListener{
        void onEventClick(int position);
    }
}
