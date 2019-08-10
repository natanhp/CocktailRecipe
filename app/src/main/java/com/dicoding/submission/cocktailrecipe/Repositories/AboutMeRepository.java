package com.dicoding.submission.cocktailrecipe.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dicoding.submission.cocktailrecipe.Models.AboutMeModel;
import com.dicoding.submission.cocktailrecipe.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AboutMeRepository {
    private JSONObject createJSONObject(Application application) {
        JSONObject jsonObject = new JSONObject();

        InputStream inputStream = application.getApplicationContext().getResources().openRawResource(R.raw.data_diri);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                stringBuilder.append(line).append("\n");
            }

            String resultString = stringBuilder.toString();
            JSONTokener jsonTokener = new JSONTokener(resultString);

            jsonObject = new JSONObject(jsonTokener);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public LiveData<AboutMeModel> parseJSONObject(Application application) {
        AboutMeModel aboutMeModel = new AboutMeModel();
        JSONObject jsonObject = createJSONObject(application);
        MutableLiveData<AboutMeModel> aboutMeModelMutableLiveData = new MutableLiveData<>();
        try {
            aboutMeModel.setNama(jsonObject.getString("nama"));
            aboutMeModel.setEmail(jsonObject.getString("email"));
            aboutMeModel.setUrlPhoto(jsonObject.getString("urlPhoto"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        aboutMeModelMutableLiveData.setValue(aboutMeModel);

        return aboutMeModelMutableLiveData;
    }
}
