package id.ac.ui.cs.mobileprogramming.bungaamalia.sutaato

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val delayExit = 2000
    private var backPressedTimer: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.plus_button_view).setOnClickListener {
            plusValue()
        }
        findViewById<Button>(R.id.minus_button_view).setOnClickListener {
            minusValue()
        }
        Toast.makeText(this, "STATE: ON_CREATE", Toast.LENGTH_SHORT).show()
        Log.i("ActivityLifecycle", "STATE: ON_CREATE")
    }

    fun plusValue() {
        val valueTextView = findViewById<TextView>(R.id.value_view)
        value = valueTextView.text.toString().toInt()
        value++
        valueTextView.text = value.toString()
    }

    fun minusValue() {
        val valueTextView = findViewById<TextView>(R.id.value_view)
        value = valueTextView.text.toString().toInt()
        value--
        valueTextView.text = value.toString()
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