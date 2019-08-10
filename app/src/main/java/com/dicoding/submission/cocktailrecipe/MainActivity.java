package com.dicoding.submission.cocktailrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTitle(R.string.cocktail_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);
        coctailAdapter = new CoctailAdapter(this::onEventClick);
        recyclerView.setAdapter(coctailAdapter);

        cocktailViewModel = ViewModelProviders.of(this).get(CocktailViewModel.class);
        cocktailViewModel.getDataCoctail().observe(this, new Observer<List<CocktailModel>>() {
            @Override
            public void onChanged(List<CocktailModel> cocktailModels) {
                coctailAdapter.setCocktailModels(cocktailModels);
            }
        });
    }

    @Override
    public void onEventClick(int position) {
        Intent intent = new Intent(MainActivity.this, DetailItemActivity.class);
        CocktailModel cocktailModel = cocktailViewModel.getDataCoctail().getValue().get(position);
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
        switch (item.getItemId()) {
            case R.id.about_me:
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
