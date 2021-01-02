package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.adapter.RecipeListAdapter
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.application.RecipesApplication
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Recipe
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.databinding.FragmentRecipeListBinding
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel.RecipeViewModel
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel.RecipeViewModelFactory

class RecipeActivity : AppCompatActivity() {
    private lateinit var editRecipeName: EditText
    private lateinit var binding: FragmentRecipeListBinding
    private val recipeActivityRequestCode = 1
    private val recipeViewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory((application as RecipesApplication).repository)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.fragment_recipe_list
        )
        setContentView(R.layout.activity_recipe)
        editRecipeName = findViewById(R.id.edit_recipe_name)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RecipeListAdapter()
        recyclerView.adapter = adapter

        recipeViewModel.allRecipes.observe(owner = this) { recipes ->
            recipes.let { adapter.submitList(it) }
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this@RecipeActivity, RecipeActivity::class.java)
            startActivityForResult(intent, recipeActivityRequestCode)
        }

        val saveButton = findViewById<Button>(R.id.recipe_button_save)
        saveButton.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editRecipeName.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val recipeName = editRecipeName.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, recipeName)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            (recyclerView.adapter as RecipeListAdapter).notifyDataSetChanged()
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == recipeActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(RecipeActivity.EXTRA_REPLY)?.let { reply ->
                val recipe = Recipe(0, reply, "test desc",
                    "test image uri string", "test date")
                recipeViewModel.insertRecipe(recipe)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.recipe_empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.ryoudiary.android.recipelistsql.REPLY"
    }
}