package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val delayExit = 2000
    private var backPressedTimer: Long = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        setSupportActionBar(binding.toolbar)
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