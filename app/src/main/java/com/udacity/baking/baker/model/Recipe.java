
package com.udacity.baking.baker.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Recipe {

    @SerializedName("id")
    public int id;
    @NonNull
    @SerializedName("name")
    public String name;
    @Nullable
    @SerializedName("ingredients")
    public List<Ingredient> ingredients = null;
    @Nullable
    @SerializedName("steps")
    public List<Step> steps = null;
    @SerializedName("servings")
    public int servings;
    @Nullable
    @SerializedName("image")
    public String image;

}
