package com.dicoding.submission.cocktailrecipe.Dao;

import android.app.Application;

import com.dicoding.submission.cocktailrecipe.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CocktailDao {
    public JSONObject creatJSONOBject(Application application) throws IOException {
        JSONObject jsonObject = null;

        InputStream inputStream = application.getApplicationContext().getResources().openRawResource(R.raw.Cocktails);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        for (String line; (line = bufferedReader.readLine()) != null; ) {
            stringBuilder.append(line).append("\n");
        }

        String resultString = stringBuilder.toString();
        JSONTokener jsonTokener = new JSONTokener(resultString);
        try {
            jsonObject = new JSONObject(jsonTokener);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
