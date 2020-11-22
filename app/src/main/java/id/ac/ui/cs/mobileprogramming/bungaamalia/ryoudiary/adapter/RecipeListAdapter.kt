package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.R
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Recipe

class RecipeListAdapter : ListAdapter<Recipe, RecipeListAdapter.RecipeViewHolder>(RecipesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            recipeItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): RecipeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return RecipeViewHolder(view)
            }
        }
    }

    class RecipesComparator : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.name == newItem.name
        }
    }
}