package com.dicoding.submission.cocktailrecipe.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import lombok.Data;

@Data
public class CocktailModel implements Parcelable {
    private String name;
    private String UriImage;
    private List<String> ingredients;
    private List<String> howToMake;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.UriImage);
        dest.writeStringList(this.ingredients);
        dest.writeStringList(this.howToMake);
    }

    protected CocktailModel(Parcel in) {
        this.name = in.readString();
        this.UriImage = in.readString();
        this.ingredients = in.createStringArrayList();
        this.howToMake = in.createStringArrayList();
    }

    public static final Parcelable.Creator<CocktailModel> CREATOR = new Parcelable.Creator<CocktailModel>() {
        @Override
        public CocktailModel createFromParcel(Parcel source) {
            return new CocktailModel(source);
        }

        @Override
        public CocktailModel[] newArray(int size) {
            return new CocktailModel[size];
        }
    };
}
