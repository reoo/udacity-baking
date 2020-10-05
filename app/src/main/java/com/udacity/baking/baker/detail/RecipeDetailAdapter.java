package com.udacity.baking.baker.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.baking.baker.databinding.ItemRecipeIngredientsBinding;
import com.udacity.baking.baker.model.Recipe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailHolder> {
    @NonNull
    private Recipe recipes;
    @Nullable
    private RecipeClickListener listener;

    public interface RecipeClickListener {
        void onRecipeClick(@NonNull Recipe recipe);
    }

    public RecipeDetailAdapter(@NonNull Recipe recipes, @Nullable RecipeClickListener listener) {
        this.recipes = recipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRecipeIngredientsBinding binding = ItemRecipeIngredientsBinding.inflate(layoutInflater, parent, false);
        return new RecipeDetailHolder(binding.getRoot(), binding, holderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailHolder holder, int position) {
        if (recipes.ingredients != null) {
            holder.bind(recipes.ingredients);
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private final RecipeDetailHolder.RecipeDetailHolderListener holderListener = new RecipeDetailHolder.RecipeDetailHolderListener() {
        @Override
        public void onRecipeClick(@NonNull Recipe recipe) {
            if(listener != null) {
                listener.onRecipeClick(recipe);
            }
        }
    };

}
