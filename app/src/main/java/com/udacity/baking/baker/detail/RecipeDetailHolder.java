package com.udacity.baking.baker.detail;

import android.view.View;

import com.udacity.baking.baker.R;
import com.udacity.baking.baker.databinding.ItemRecipeDetailStepBinding;
import com.udacity.baking.baker.databinding.ItemRecipeDetailStepsHeaderBinding;
import com.udacity.baking.baker.databinding.ItemRecipeIngredientsBinding;
import com.udacity.baking.baker.model.Ingredient;
import com.udacity.baking.baker.model.Step;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailHolder extends RecyclerView.ViewHolder {
    @Nullable
    private ItemRecipeIngredientsBinding ingredientsBinding;
    @Nullable
    private ItemRecipeDetailStepBinding stepBinding;
    @Nullable
    private ItemRecipeDetailStepsHeaderBinding stepsHeaderBinding;
    @Nullable
    private RecipeDetailHolderListener holderListener;

    public interface RecipeDetailHolderListener {
        void onRecipeClick(@NonNull Step step);
    }

    public RecipeDetailHolder(@NonNull View itemView, @Nullable ItemRecipeIngredientsBinding ingredientsBinding, @Nullable RecipeDetailHolderListener holderListener) {
        super(itemView);
        this.ingredientsBinding = ingredientsBinding;
        this.holderListener = holderListener;
    }

    public RecipeDetailHolder(@NonNull View itemView, @Nullable ItemRecipeDetailStepsHeaderBinding stepsHeaderBinding) {
        super(itemView);
        this.stepsHeaderBinding = stepsHeaderBinding;
    }

    public RecipeDetailHolder(@NonNull View itemView, @Nullable ItemRecipeDetailStepBinding stepBinding, @Nullable RecipeDetailHolderListener holderListener) {
        super(itemView);
        this.stepBinding = stepBinding;
        this.holderListener = holderListener;
    }

    public void bindIngredients(@NonNull final List<Ingredient> ingredients) {
        if (ingredientsBinding != null) {
            ingredientsBinding.itemRecipeIngredientsTitle.setText(ingredientsBinding.itemRecipeIngredientsTitle.getResources().getString(R.string.item_recipe_ingredients_title, ingredients.size()));
            ingredientsBinding.itemRecipeIngredientsList.setText(null);
            StringBuilder stringBuilder = new StringBuilder();
            for(Ingredient item: ingredients) {
                stringBuilder
                        .append("- ")
                        .append(item.quantity)
                        .append(' ')
                        .append(item.measure)
                        .append(' ')
                        .append(item.ingredient)
                        .append('\n');
            }
            ingredientsBinding.itemRecipeIngredientsList.append(stringBuilder.toString());
        }
    }

    public void bindStepsHeader(int steps) {
        if (stepsHeaderBinding != null) {
            stepsHeaderBinding.itemRecipeDetailStepsHeader.setText(
                    stepsHeaderBinding.itemRecipeDetailStepsHeader.getResources().getString(
                            R.string.item_recipe_detail_steps_header, steps)
            );
        }
    }

    public void bindStep(@NonNull final Step step) {
        if (stepBinding != null) {
            stepBinding.itemRecipeDetailStepDescription.setText(step.shortDescription);
            stepBinding.itemRecipeDetailStepRootContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holderListener != null) {
                        holderListener.onRecipeClick(step);
                    }
                }
            });
        }
    }

}
