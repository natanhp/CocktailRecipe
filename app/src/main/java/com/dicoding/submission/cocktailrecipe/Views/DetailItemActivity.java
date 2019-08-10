package com.dicoding.submission.cocktailrecipe.Views;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.submission.cocktailrecipe.MainActivity;
import com.dicoding.submission.cocktailrecipe.Models.CocktailModel;
import com.dicoding.submission.cocktailrecipe.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class DetailItemActivity extends AppCompatActivity {
    @BindViews({R.id.textView_ingredients, R.id.textView_how_to})
    List<TextView> textViews;

    @BindView(R.id.imageView_coctail)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
        ButterKnife.bind(this);

        CocktailModel cocktailModel = getCocktail();

        setTitle(cocktailModel.getName());

        setViews(cocktailModel);
    }

    private CocktailModel getCocktail() {
        return getIntent().getParcelableExtra(MainActivity.EXTRA_COCTAIL);
    }

    private void setViews(CocktailModel cocktailModel) {
        Glide.with(this)
                .load(cocktailModel.getUriImage())
                .into(imageView);
        StringBuilder tempString = new StringBuilder();
        for (String ingredient : cocktailModel.getIngredients()) {
            tempString.append(ingredient).append("\n");
        }
        textViews.get(0).setText(tempString.toString());
        tempString = new StringBuilder();
        for (String howTo : cocktailModel.getHowToMake()) {
            tempString.append(howTo).append("\n");
        }
        textViews.get(1).setText(tempString.toString());
    }
}
