package com.udacity.baking.baker.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udacity.baking.baker.BuildConfig;
import com.udacity.baking.baker.R;
import com.udacity.baking.baker.api.HttpRecipesClient;
import com.udacity.baking.baker.api.RecipesClient;
import com.udacity.baking.baker.api.RetrofitAPIService;
import com.udacity.baking.baker.databinding.FragmentRecipesBinding;
import com.udacity.baking.baker.model.Recipe;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

public class RecipesFragment extends Fragment implements RecipesAdapter.RecipesClickListener {
    private static final int VA_INDEX_CONTENT_STATE = 0;
    private static final int VA_INDEX_EMPTY_STATE = 1;
    private static final int VA_INDEX_LOADING_STATE = 2;

    @NonNull
    private FragmentRecipesBinding binding;
    @NonNull
    private RecipesClient recipesClient;
    @Nullable
    private List<Recipe> recipes;

    public RecipesFragment() {
        // Required empty public constructor
    }

    public static RecipesFragment newInstance() {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            // get arguments
        }

        RetrofitAPIService service = new RetrofitAPIService.Factory(RetrofitAPIService.BASE_URL, BuildConfig.DEBUG).create();
        recipesClient = new HttpRecipesClient(service);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecipesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(recipes == null) {
            binding.recipesViewAnimator.setDisplayedChild(VA_INDEX_LOADING_STATE);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        recipes = recipesClient.getRecipes();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bindRecipes();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            bindRecipes();
        }
    }

    @Override
    public void onRecipeClick(@NonNull Recipe recipe) {
        Toast.makeText(getActivity(), recipe.name, Toast.LENGTH_SHORT).show();
    }

    private void bindRecipes() {
        int columns = getResources().getInteger(R.integer.recipes_columns);
        binding.recipesViewAnimator.setDisplayedChild(VA_INDEX_CONTENT_STATE);
        RecipesAdapter adapter = new RecipesAdapter(this.recipes, this);
        binding.recipesListItems.setAdapter(adapter);
        binding.recipesListItems.setLayoutManager(new GridLayoutManager(getActivity(), columns));
    }

}