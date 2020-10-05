package com.udacity.baking.baker.detail;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.baking.baker.R;
import com.udacity.baking.baker.databinding.ItemRecipeDetailStepBinding;
import com.udacity.baking.baker.databinding.ItemRecipeDetailStepsHeaderBinding;
import com.udacity.baking.baker.databinding.ItemRecipeIngredientsBinding;
import com.udacity.baking.baker.model.Recipe;
import com.udacity.baking.baker.model.Step;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailHolder> {
    @NonNull
    private Recipe recipes;
    @Nullable
    private RecipeClickListener listener;
    @NonNull
    private SparseIntArray positionToLayout;
    @NonNull
    private SparseArray<Object> positionToModel;

    public interface RecipeClickListener {
        void onStepClick(@NonNull Step step);
    }

    public RecipeDetailAdapter(@NonNull Recipe recipes, @Nullable RecipeClickListener listener) {
        this.recipes = recipes;
        this.listener = listener;
        positionToLayout = new SparseIntArray();
        positionToModel = new SparseArray<>();
        mapPositions();
    }

    @Override
    public int getItemViewType(int position) {
        return positionToLayout.get(position);
    }

    @NonNull
    @Override
    public RecipeDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if(R.layout.item_recipe_ingredients == viewType) {
            ItemRecipeIngredientsBinding binding = ItemRecipeIngredientsBinding.inflate(layoutInflater, parent, false);
            return new RecipeDetailHolder(binding.getRoot(), binding, holderListener);
        } else if(R.layout.item_recipe_detail_step == viewType) {
            ItemRecipeDetailStepBinding binding = ItemRecipeDetailStepBinding.inflate(layoutInflater, parent, false);
            return new RecipeDetailHolder(binding.getRoot(), binding, holderListener);
        } else {
            // R.layout.item_recipe_detail_steps_header
            ItemRecipeDetailStepsHeaderBinding binding = ItemRecipeDetailStepsHeaderBinding.inflate(layoutInflater, parent, false);
            return new RecipeDetailHolder(binding.getRoot(), binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailHolder holder, int position) {
        int viewType = positionToLayout.get(position);
        if(R.layout.item_recipe_ingredients == viewType) {
            if (recipes.ingredients != null) {
                holder.bindIngredients(recipes.ingredients);
            }
        } else if(R.layout.item_recipe_detail_step == viewType) {
            holder.bindStep((Step) positionToModel.get(position));
        } else {
            // R.layout.item_recipe_detail_steps_header
            if (recipes.steps != null) {
                holder.bindStepsHeader(recipes.steps.size());
            }
        }
    }

    @Override
    public int getItemCount() {
        return positionToLayout.size();
    }

    private final RecipeDetailHolder.RecipeDetailHolderListener holderListener = new RecipeDetailHolder.RecipeDetailHolderListener() {
        @Override
        public void onRecipeClick(@NonNull Step step) {
            if(listener != null) {
                listener.onStepClick(step);
            }
        }
    };

    private void mapPositions() {
        int position = 0;
        positionToLayout.put(position++, R.layout.item_recipe_ingredients);
        if (recipes.steps != null && recipes.steps.size() > 0) {
            positionToLayout.put(position++, R.layout.item_recipe_detail_steps_header);
            for(Step item: recipes.steps) {
                positionToModel.put(position, item);
                positionToLayout.put(position++, R.layout.item_recipe_detail_step);
            }
        }
    }

}
