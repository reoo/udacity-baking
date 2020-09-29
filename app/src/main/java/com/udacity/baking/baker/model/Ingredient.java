
package com.udacity.baking.baker.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Ingredient {

    @SerializedName("quantity")
    public double quantity;
    @NonNull
    @SerializedName("measure")
    public String measure;
    @NonNull
    @SerializedName("ingredient")
    public String ingredient;

}
