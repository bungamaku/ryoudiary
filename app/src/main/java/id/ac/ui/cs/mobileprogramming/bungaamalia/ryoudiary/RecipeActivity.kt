package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class RecipeActivity : AppCompatActivity() {
    private lateinit var editRecipeName: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        editRecipeName = findViewById(R.id.edit_recipe_name)

        val button = findViewById<Button>(R.id.recipe_button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editRecipeName.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val recipeName = editRecipeName.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, recipeName)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.ryoudiary.android.recipelistsql.REPLY"
    }
}