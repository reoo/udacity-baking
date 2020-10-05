package com.udacity.baking.baker.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udacity.baking.baker.databinding.RecipeDetailFragmentBinding;
import com.udacity.baking.baker.model.Recipe;
import com.udacity.baking.baker.model.Step;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class RecipeDetailFragment extends Fragment implements RecipeDetailAdapter.RecipeClickListener {
    public static final String TAG = RecipeDetailFragment.class.getSimpleName();

    private static final String EXTRA_RECIPE = "EXTRA_RECIPE";

    @NonNull
    private RecipeDetailFragmentBinding binding;
    @Nullable
    private Recipe recipe;

    public static RecipeDetailFragment newInstance(@Nullable Recipe recipe) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_RECIPE, recipe);
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoreInstanceState(savedInstanceState);
        loadArguments(getArguments());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = RecipeDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (recipe != null) {
            getActivity().setTitle(recipe.name);
            RecipeDetailAdapter adapter = new RecipeDetailAdapter(recipe, this);
            binding.recipeDetailListItems.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recipeDetailListItems.setAdapter(adapter);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_RECIPE, recipe);
    }

    @Override
    public void onStepClick(@NonNull Step step) {
        Toast.makeText(getActivity(), step.shortDescription, Toast.LENGTH_SHORT).show();
    }

    private void restoreInstanceState(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            recipe = savedInstanceState.getParcelable(EXTRA_RECIPE);
        }
    }

    private void loadArguments(@Nullable Bundle arguments) {
        if (arguments != null) {
            recipe = arguments.getParcelable(EXTRA_RECIPE);
        }
    }


}