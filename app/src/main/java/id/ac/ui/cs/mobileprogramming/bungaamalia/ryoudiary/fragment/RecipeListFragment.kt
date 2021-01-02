package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.R
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.adapter.RecipeListAdapter

class RecipeListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false)
        val recyclerView = rootView?.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView?.layoutManager = LinearLayoutManager(activity)

        val adapter = RecipeListAdapter()
        recyclerView?.adapter = adapter
        (recyclerView?.adapter as RecipeListAdapter).notifyDataSetChanged()

        return rootView
    }
}