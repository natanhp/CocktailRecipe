package com.dicoding.submission.cocktailrecipe.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dicoding.submission.cocktailrecipe.Repositories.CoctailRepository;

import org.json.JSONObject;

public class CocktailViewModel extends AndroidViewModel {
    private CoctailRepository coctailRepository;

    public CocktailViewModel(@NonNull Application application) {
        super(application);
        coctailRepository = new CoctailRepository(application);
    }

    public LiveData<JSONObject> getJSONObject(){
        return coctailRepository.creatJSONOBject();
    }
}
