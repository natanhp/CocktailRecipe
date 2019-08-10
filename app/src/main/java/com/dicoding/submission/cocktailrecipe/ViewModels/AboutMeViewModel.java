package com.dicoding.submission.cocktailrecipe.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dicoding.submission.cocktailrecipe.Models.AboutMeModel;
import com.dicoding.submission.cocktailrecipe.Repositories.AboutMeRepository;

public class AboutMeViewModel extends AndroidViewModel {
    private AboutMeRepository aboutMeRepository;

    public AboutMeViewModel(@NonNull Application application) {
        super(application);
        aboutMeRepository = new AboutMeRepository();
    }

    public LiveData<AboutMeModel> getAboutMe(Application application){
        return aboutMeRepository.parseJSONObject(application);
    }
}
