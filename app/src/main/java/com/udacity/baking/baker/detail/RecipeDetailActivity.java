package com.udacity.baking.baker.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.udacity.baking.baker.R;
import com.udacity.baking.baker.model.Recipe;
import com.udacity.baking.baker.model.Step;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.RecipeDetailListener {
    private static final String EXTRA_RECIPE = "EXTRA_RECIPE";

    @Nullable
    private Recipe recipe;

    @NonNull
    public static Intent newInstance(@NonNull Context context, @Nullable Recipe recipe) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(EXTRA_RECIPE, recipe);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        restoreInstanceState(savedInstanceState);
        loadArguments(getIntent());
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(RecipeDetailFragment.TAG);
        if (fragment == null) {
            fragment = RecipeDetailFragment.newInstance(recipe);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.recipe_detail_fragment_container, fragment, RecipeDetailFragment.TAG)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_RECIPE, recipe);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onStepClick(@NonNull Step step) {
        Fragment fragment = StepDetailFragment.newInstance(step);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.recipe_detail_fragment_container, fragment, StepDetailFragment.TAG)
                .addToBackStack(StepDetailFragment.TAG)
                .commit();
    }

    private void restoreInstanceState(@Nullable Bundle bundle) {
        if (bundle != null) {
            recipe = bundle.getParcelable(EXTRA_RECIPE);
        }
    }

    private void loadArguments(@NonNull Intent intent) {
        recipe = intent.getParcelableExtra(EXTRA_RECIPE);
    }

}