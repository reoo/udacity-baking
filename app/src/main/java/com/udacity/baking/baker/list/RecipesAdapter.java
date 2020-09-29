package com.udacity.baking.baker.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.baking.baker.databinding.ItemRecipeBinding;
import com.udacity.baking.baker.model.Recipe;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesHolder> {
    @NonNull
    private List<Recipe> recipes;
    @Nullable
    private RecipesClickListener listener;

    public interface RecipesClickListener {
        void onRecipeClick(@NonNull Recipe recipe);
    }

    public RecipesAdapter(@NonNull List<Recipe> recipes, @Nullable RecipesClickListener listener) {
        this.recipes = recipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRecipeBinding binding = ItemRecipeBinding.inflate(layoutInflater, parent, false);
        return new RecipesHolder(binding.getRoot(), binding, holderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesHolder holder, int position) {
        holder.bind(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    private RecipesHolder.RecipesHolderListener holderListener = new RecipesHolder.RecipesHolderListener() {
        @Override
        public void onRecipeClick(@NonNull Recipe recipe) {
            if(listener != null) {
                listener.onRecipeClick(recipe);
            }
        }
    };

}
