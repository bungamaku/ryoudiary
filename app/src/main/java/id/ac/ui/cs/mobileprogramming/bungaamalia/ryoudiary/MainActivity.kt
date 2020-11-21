package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
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

        binding.fab.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, RecipeActivity::class.java)
            startActivityForResult(intent, recipeActivityRequestCode)
        }

        Toast.makeText(this, "STATE: ON_CREATE", Toast.LENGTH_SHORT).show()
        Log.i("ActivityLifecycle", "STATE: ON_CREATE")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == recipeActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(RecipeActivity.EXTRA_REPLY)?.let { reply ->
                val recipe = Recipe(0, reply, "test desc",
                    "text image", "test date")
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
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
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

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "STATE: ON_START", Toast.LENGTH_SHORT).show()
        Log.i("ActivityLifecycle", "STATE: ON_START")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "STATE: ON_RESUME", Toast.LENGTH_SHORT).show()
        Log.i("ActivityLifecycle", "STATE: ON_RESUME")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "STATE: ON_PAUSE", Toast.LENGTH_SHORT).show()
        Log.i("ActivityLifecycle", "STATE: ON_PAUSE")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "STATE: ON_STOP", Toast.LENGTH_SHORT).show()
        Log.i("ActivityLifecycle", "STATE: ON_STOP")
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "STATE: ON_RESTART", Toast.LENGTH_SHORT).show()
        Log.i("ActivityLifecycle", "STATE: ON_RESTART")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "STATE: ON_DESTROY", Toast.LENGTH_SHORT).show()
        Log.i("ActivityLifecycle", "STATE: ON_DESTROY")
    }
}