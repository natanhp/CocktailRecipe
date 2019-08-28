package com.dicoding.submission.cocktailrecipe.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.dicoding.submission.cocktailrecipe.Models.AboutMeModel;
import com.dicoding.submission.cocktailrecipe.R;
import com.dicoding.submission.cocktailrecipe.ViewModels.AboutMeViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class AboutMeActivity extends AppCompatActivity {
    private AboutMeViewModel aboutMeViewModel;

    @BindView(R.id.imageView_about_me)
    ImageView imageView;

    @BindViews({R.id.textView_about_nama, R.id.textView_about_email})
    List<TextView> textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        setTitle(R.string.about_me);
        ButterKnife.bind(this);

        aboutMeViewModel = ViewModelProviders.of(this).get(AboutMeViewModel.class);
        aboutMeViewModel.getAboutMe(getApplication()).observe(this, this::setViews);
    }

    private void setViews(AboutMeModel aboutMeModel) {
        Glide.with(this)
                .load(aboutMeModel.getUrlPhoto())
                .into(imageView);
        textViews.get(0).setText(aboutMeModel.getNama());
        textViews.get(1).setText(aboutMeModel.getEmail());
    }
}
