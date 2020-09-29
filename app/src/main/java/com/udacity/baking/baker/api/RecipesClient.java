package com.udacity.baking.baker.api;

import com.udacity.baking.baker.model.Recipe;

import java.io.IOException;
import java.util.List;

import androidx.annotation.Nullable;

public interface RecipesClient {

    @Nullable
    List<Recipe> getRecipes() throws IOException;

}
