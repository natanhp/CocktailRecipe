package com.dicoding.submission.cocktailrecipe.Models;

import java.util.List;

import lombok.Data;

@Data
public class CocktailModel {
    private String name;
    private String UriImage;
    private List<String> ingridients;
    private List<String> howToMake;
}
