package com.udacity.baking.baker.list;

import android.view.View;

import com.bumptech.glide.Glide;
import com.udacity.baking.baker.R;
import com.udacity.baking.baker.databinding.ItemRecipeBinding;
import com.udacity.baking.baker.model.Recipe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecipesHolder extends RecyclerView.ViewHolder {
    @NonNull
    private ItemRecipeBinding binding;
    @Nullable
    private RecipesHolderListener holderListener;

    public interface RecipesHolderListener {
        void onRecipeClick(@NonNull Recipe recipe);
    }

    public RecipesHolder(@NonNull View itemView, @NonNull ItemRecipeBinding binding, @Nullable RecipesHolderListener holderListener) {
        super(itemView);
        this.binding = binding;
        this.holderListener = holderListener;
    }

    public void bind(@NonNull final Recipe recipe) {
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holderListener != null) {
                    holderListener.onRecipeClick(recipe);
                }
            }
        });
        binding.itemRecipeTitle.setText(recipe.name);
        binding.itemRecipeServings.setText(binding.itemRecipeServings.getContext().getString(R.string.item_recipe_servings, recipe.servings));
        Glide.with(binding.itemRecipeImage)
                .load(recipe.image)
                .placeholder(R.color.colorAccent)
                .into(binding.itemRecipeImage);
    }

}
