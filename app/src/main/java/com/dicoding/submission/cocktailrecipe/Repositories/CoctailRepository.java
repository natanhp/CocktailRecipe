package com.dicoding.submission.cocktailrecipe.Repositories;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dicoding.submission.cocktailrecipe.Dao.CocktailDao;
import com.dicoding.submission.cocktailrecipe.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CoctailRepository implements CocktailDao {
    private MutableLiveData<JSONObject> jsonObjectLiveData = new MutableLiveData<>();

    public CoctailRepository(Application application) {
        AsyncTask<Application, Void, JSONObject> jsonAksesAsynTask = new JSONAksesAsynTask();
        jsonAksesAsynTask.execute(application);
    }

    @SuppressLint("StaticFieldLeak")
    private class JSONAksesAsynTask extends AsyncTask<Application, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Application... applications) {
            JSONObject jsonObject = new JSONObject();

            InputStream inputStream = applications[0].getApplicationContext().getResources().openRawResource(R.raw.Cocktails);
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

        @Override
        protected void onPostExecute(JSONObject object) {
            super.onPostExecute(object);
            jsonObjectLiveData.setValue(object);
        }

    }

    @Override
    public LiveData<JSONObject> creatJSONOBject() {
        return jsonObjectLiveData;
    }
}
