
package com.udacity.baking.baker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Recipe implements Parcelable {

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

    public Recipe(int id, @NonNull String name, @Nullable List<Ingredient> ingredients, @Nullable List<Step> steps, int servings, @Nullable String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
        steps = in.createTypedArrayList(Step.CREATOR);
        servings = in.readInt();
        image = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeTypedList(ingredients);
        parcel.writeTypedList(steps);
        parcel.writeInt(servings);
        parcel.writeString(image);
    }
}
