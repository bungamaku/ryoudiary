package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary

import android.content.Context
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.util.GLRenderer

class GLActivity : AppCompatActivity() {
    private lateinit var gLView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gLView = MyGLSurfaceView(this)
        setContentView(gLView)
    }

    override fun onResume() {
        super.onResume()
        gLView.onResume()
    }

    override fun onPause() {
        super.onPause()
        gLView.onPause()
    }
}

class MyGLSurfaceView(context: Context) : GLSurfaceView(context) {
    private val renderer: GLRenderer = GLRenderer()

    init {
        setRenderer(renderer)
    }
}