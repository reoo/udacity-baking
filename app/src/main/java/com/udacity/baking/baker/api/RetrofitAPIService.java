package com.udacity.baking.baker.api;

import com.udacity.baking.baker.model.Recipe;

import java.util.List;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface RetrofitAPIService {
    String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";

    class Factory {
        @NonNull
        private String baseUrl;
        private boolean debugMode;

        public Factory(@NonNull String baseUrl, boolean debugMode) {
            this.baseUrl = baseUrl;
            this.debugMode = debugMode;
        }

        @NonNull
        public RetrofitAPIService create() {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.baseUrl(baseUrl);
            OkHttpClient okHttpClient = createHttpClient();
            builder.client(okHttpClient);

            Retrofit retrofit = builder.build();

            return retrofit.create(RetrofitAPIService.class);
        }

        @NonNull
        private OkHttpClient createHttpClient() {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            if(debugMode) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                clientBuilder.addInterceptor(interceptor);
            }

            return clientBuilder.build();
        }

    }

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();

}
