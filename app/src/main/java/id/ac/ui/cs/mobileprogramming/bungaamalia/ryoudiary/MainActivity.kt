package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.adapter.RecipeListAdapter
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.application.RecipesApplication
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Recipe
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.databinding.ActivityMainBinding
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel.RecipeViewModel
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel.RecipeViewModelFactory

class MainActivity : AppCompatActivity() {
    private val delayExit = 2000
    private var backPressedTimer: Long = 0
    private lateinit var binding: ActivityMainBinding
    private val recipeActivityRequestCode = 1
    private val recipeViewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory((application as RecipesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        setSupportActionBar(binding.toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = RecipeListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        recipeViewModel.allRecipes.observe(owner = this) { recipes ->
            recipes.let { adapter.submitList(it) }
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, RecipeActivity::class.java)
            startActivityForResult(intent, recipeActivityRequestCode)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (backPressedTimer + delayExit > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Press once again to go back!",
                Toast.LENGTH_SHORT).show()
        }
        backPressedTimer = System.currentTimeMillis()
    }
}