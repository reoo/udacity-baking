package com.udacity.baking.baker.detail;

import android.view.View;

import com.udacity.baking.baker.R;
import com.udacity.baking.baker.databinding.ItemRecipeIngredientsBinding;
import com.udacity.baking.baker.model.Ingredient;
import com.udacity.baking.baker.model.Recipe;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailHolder extends RecyclerView.ViewHolder {
    @Nullable
    private ItemRecipeIngredientsBinding binding;
    @Nullable
    private RecipeDetailHolderListener holderListener;

    public interface RecipeDetailHolderListener {
        void onRecipeClick(@NonNull Recipe recipe);
    }

    public RecipeDetailHolder(@NonNull View itemView, @Nullable ItemRecipeIngredientsBinding binding, @Nullable RecipeDetailHolderListener holderListener) {
        super(itemView);
        this.binding = binding;
        this.holderListener = holderListener;
    }

    public void bind(@NonNull final List<Ingredient> ingredients) {
        if (binding != null) {
            binding.itemRecipeIngredientsTitle.setText(binding.itemRecipeIngredientsTitle.getResources().getString(R.string.item_recipe_ingredients_title, ingredients.size()));
            binding.itemRecipeIngredientsList.setText(null);
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
            binding.itemRecipeIngredientsList.append(stringBuilder.toString());
        }
    }

}
