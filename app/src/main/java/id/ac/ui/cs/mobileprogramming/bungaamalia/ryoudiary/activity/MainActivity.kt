package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.activity

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.R
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

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        Toast.makeText(this, "STATE: ON_CREATE", Toast.LENGTH_SHORT).show()
        Log.i("ActivityLifecycle", "STATE: ON_CREATE")
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