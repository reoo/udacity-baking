package com.udacity.baking.baker.api;

import com.udacity.baking.baker.model.Recipe;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Response;

public class HttpRecipesClient implements RecipesClient {
    @NonNull
    RetrofitAPIService apiService;

    public HttpRecipesClient(@NonNull RetrofitAPIService apiService) {
        this.apiService = apiService;
    }

    @Nullable
    @Override
    public List<Recipe> getRecipes() throws IOException {
        Call<List<Recipe>> call = apiService.getRecipes();
        Response<List<Recipe>> response = call.execute();
        if(response != null) {
            return response.body();
        }
        return null;
    }
}
