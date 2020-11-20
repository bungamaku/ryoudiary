package id.ac.ui.cs.mobileprogramming.bungaamalia.sutaato

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.start_button).setOnClickListener {
            val showValueTextView = view.findViewById<TextView>(R.id.textView_input)
            val currentValue = showValueTextView.text.toString().toInt()
            if (currentValue == 0) {
                Toast.makeText(activity?.applicationContext, "Countdown can't start from 0!", Toast.LENGTH_SHORT).show()
            } else {
                val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentValue)
                findNavController().navigate(action)
            }
        }

        view.findViewById<Button>(R.id.plus_button).setOnClickListener {
            plusValue(view)
        }

        view.findViewById<Button>(R.id.minus_button).setOnClickListener {
            minusValue(view)
        }
    }

    private fun plusValue(view: View) {
        val showValueTextView = view.findViewById<TextView>(R.id.textView_input)
        val valueString = showValueTextView.text.toString()
        var value = valueString.toInt()
        value++
        showValueTextView.text = value.toString()
    }

    private fun minusValue(view: View) {
        val showValueTextView = view.findViewById<TextView>(R.id.textView_input)
        val valueString = showValueTextView.text.toString()
        var value = valueString.toInt()
        if (value < 1) {
            Toast.makeText(activity?.applicationContext, "Seconds can't be negative!", Toast.LENGTH_SHORT).show()
        } else {
            value--
        }
        showValueTextView.text = value.toString()
    }
}