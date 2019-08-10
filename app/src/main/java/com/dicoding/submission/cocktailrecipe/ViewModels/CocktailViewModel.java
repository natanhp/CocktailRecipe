package com.dicoding.submission.cocktailrecipe.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dicoding.submission.cocktailrecipe.Models.CocktailModel;
import com.dicoding.submission.cocktailrecipe.Repositories.CoctailRepository;

import org.json.JSONObject;

import java.util.List;

public class CocktailViewModel extends AndroidViewModel {
    private CoctailRepository coctailRepository;

    public CocktailViewModel(@NonNull Application application) {
        super(application);
        coctailRepository = new CoctailRepository(application);
    }

    private LiveData<List<CocktailModel>> getDataCoctail(){
        return coctailRepository.getDataCoctail();
    }
}
