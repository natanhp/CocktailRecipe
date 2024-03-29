package com.dicoding.submission.cocktailrecipe.Repositories;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.dicoding.submission.cocktailrecipe.Dao.CocktailDao;
import com.dicoding.submission.cocktailrecipe.Models.CocktailModel;
import com.dicoding.submission.cocktailrecipe.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class CoctailRepository {
    private JSONObject jsonObject = new JSONObject();

    @Getter
    private MutableLiveData<List<CocktailModel>> dataCoctail = new MutableLiveData<>();

    @Getter
    private Boolean state = false;

    private Application application;

    public CoctailRepository(Application application) {
        this.application = application;
    }

    @SuppressLint("StaticFieldLeak")
    private class JSONAksesAsynTask extends AsyncTask<Application, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            state = true;
        }

        @Override
        protected JSONObject doInBackground(Application... applications) {
            System.out.println("TEST LEK");
            JSONObject jsonObject = new JSONObject();

            InputStream inputStream = applications[0].getApplicationContext().getResources().openRawResource(R.raw.cocktails);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            try {
                for (String line; (line = bufferedReader.readLine()) != null; ) {
                    stringBuilder.append(line).append("\n");
                }

                String resultString = stringBuilder.toString();
                JSONTokener jsonTokener = new JSONTokener(resultString);

                jsonObject = new JSONObject(jsonTokener);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject object) {
            super.onPostExecute(object);
            System.out.println("TEST LEK2");
            jsonObject = object;
            System.out.println(jsonObject);
            new parseJSONObjectAsyncTask().execute();
        }

    }

    @SuppressLint("StaticFieldLeak")
    private class parseJSONObjectAsyncTask extends AsyncTask<Void, Void, MutableLiveData<List<CocktailModel>>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            state = true;
        }

        @Override
        protected MutableLiveData<List<CocktailModel>> doInBackground(Void... voids) {
            CocktailDao cocktailDao = () -> jsonObject;
            System.out.println("TEST LEK3");

            MutableLiveData<List<CocktailModel>> cocktailData = new MutableLiveData<>();
            List<CocktailModel> cocktailModels = new ArrayList<>();
            try {
                JSONArray jsonArray = cocktailDao.creatJSONOBject().getJSONArray("drinks");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject;
                    jsonObject = jsonArray.getJSONObject(i);

                    CocktailModel cocktailModel = new CocktailModel();
                    cocktailModel.setName(jsonObject.getString("name"));
                    cocktailModel.setUriImage(jsonObject.getString("UriImage"));

                    JSONArray ingedients;
                    ingedients = jsonObject.getJSONArray("ingredient");

                    List<String> stringList = new ArrayList<>();
                    for (int j = 0; j < ingedients.length(); j++) {
                        stringList.add(ingedients.getString(j));
                    }
                    cocktailModel.setIngredients(stringList);

                    JSONArray howToMake;
                    howToMake = jsonObject.getJSONArray("howToMake");
                    stringList = new ArrayList<>();
                    for (int j = 0; j < howToMake.length(); j++) {
                        stringList.add(howToMake.getString(j));
                    }
                    cocktailModel.setHowToMake(stringList);

                    cocktailModels.add(cocktailModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            cocktailData.postValue(cocktailModels);

            return cocktailData;
        }

        @Override
        protected void onPostExecute(MutableLiveData<List<CocktailModel>> listMutableLiveData) {
            super.onPostExecute(listMutableLiveData);
            dataCoctail.postValue(listMutableLiveData.getValue());
            state = false;
            System.out.println("TEST LEK" + dataCoctail);
        }
    }

    public void runThread() {
        new JSONAksesAsynTask().execute(application);
    }
}
