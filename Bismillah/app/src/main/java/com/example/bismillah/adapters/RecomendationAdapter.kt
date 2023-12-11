package com.example.bismillah.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bismillah.databinding.RecomendationRowLayoutBinding
import com.example.bismillah.models.FoodRecipe
import com.example.bismillah.models.Result
import com.example.bismillah.util.RecipesDiffUtil

class RecomendationAdapter : RecyclerView.Adapter<RecomendationAdapter.MyViewHolder>(){

    private var RecomendationRecipes = emptyList<Result>()

    class MyViewHolder(private val binding: RecomendationRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result){
            binding.result = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecomendationRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return RecomendationRecipes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult = RecomendationRecipes[position]
        holder.bind(currentResult)
    }

    fun setData(newData: FoodRecipe){
        val recipesDiffUtil = RecipesDiffUtil(RecomendationRecipes, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        RecomendationRecipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

}