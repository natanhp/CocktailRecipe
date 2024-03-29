package com.dicoding.submission.cocktailrecipe.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dicoding.submission.cocktailrecipe.Models.CocktailModel;
import com.dicoding.submission.cocktailrecipe.Repositories.CoctailRepository;

import java.util.List;

import lombok.Getter;

public class CocktailViewModel extends AndroidViewModel {
    private CoctailRepository coctailRepository;

    @Getter
    private Boolean state;

    public CocktailViewModel(@NonNull Application application) {
        super(application);
        coctailRepository = new CoctailRepository(application);
        state = coctailRepository.getState();
    }

    public LiveData<List<CocktailModel>> getDataCoctail() {
        return coctailRepository.getDataCoctail();
    }

    public void runThread() {
        coctailRepository.runThread();
    }
}
