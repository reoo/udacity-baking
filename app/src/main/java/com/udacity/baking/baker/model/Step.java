
package com.udacity.baking.baker.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Step {

    @SerializedName("id")
    public int id;
    @NonNull
    @SerializedName("shortDescription")
    public String shortDescription;
    @Nullable
    @SerializedName("description")
    public String description;
    @Nullable
    @SerializedName("videoURL")
    public String videoURL;
    @Nullable
    @SerializedName("thumbnailURL")
    public String thumbnailURL;

}
