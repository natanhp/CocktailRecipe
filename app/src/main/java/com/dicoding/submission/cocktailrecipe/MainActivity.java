package com.dicoding.submission.cocktailrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.submission.cocktailrecipe.Adapters.CoctailAdapter;
import com.dicoding.submission.cocktailrecipe.Models.CocktailModel;
import com.dicoding.submission.cocktailrecipe.ViewModels.CocktailViewModel;
import com.dicoding.submission.cocktailrecipe.Views.AboutMeActivity;
import com.dicoding.submission.cocktailrecipe.Views.DetailItemActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CoctailAdapter.OnEventClickListener {
    private CocktailViewModel cocktailViewModel;
    private CoctailAdapter coctailAdapter;

    public static final String EXTRA_COCTAIL = "com.dicoding.submission.cocktailrecipe.EXTRA_COCTAIL";

    @BindView(R.id.rv_coctail)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.progress_bar_coctail)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTitle(R.string.cocktail_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        Objects.requireNonNull(recyclerView.getLayoutManager()).setMeasurementCacheEnabled(false);
        coctailAdapter = new CoctailAdapter(this);
        recyclerView.setAdapter(coctailAdapter);

        cocktailViewModel = ViewModelProviders.of(this).get(CocktailViewModel.class);
        cocktailViewModel.runThread();
        cocktailViewModel.getDataCoctail().observe(this, cocktailModels -> {
            if (getState()){
                progressBar.setVisibility(View.VISIBLE);
            }

            coctailAdapter.setCocktailModels(cocktailModels);

            if (!getState()){
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onEventClick(int position) {
        Intent intent = new Intent(MainActivity.this, DetailItemActivity.class);
        CocktailModel cocktailModel = Objects.requireNonNull(cocktailViewModel.getDataCoctail().getValue()).
                get(position);
        intent.putExtra(EXTRA_COCTAIL, cocktailModel);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about_me) {
            Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private Boolean getState(){
        return cocktailViewModel.getState();
    }
}
