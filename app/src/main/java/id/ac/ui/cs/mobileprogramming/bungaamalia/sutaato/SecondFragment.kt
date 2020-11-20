package id.ac.ui.cs.mobileprogramming.bungaamalia.sutaato

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var viewModel: CountdownViewModel
    var countDownOnGoing = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(CountdownViewModel::class.java)
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_previous).setOnClickListener {
            if (countDownOnGoing) {
                Toast.makeText(activity?.applicationContext, "Countdown is still on going!", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }

        var value = viewModel.currentValue.toLong()
        val titleSecondText = getString(R.string.textview_title_second_text, value)
        view.findViewById<TextView>(R.id.textView_title_second).text = titleSecondText

        value *= 1000
        startCountdown(value)
    }

    private fun startCountdown(seconds: Long) {
        object : CountDownTimer(seconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countDownOnGoing = true
                view!!.findViewById<TextView>(R.id.textView_seconds).text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(activity?.applicationContext, "Countdown finished!", Toast.LENGTH_SHORT).show()
                countDownOnGoing = false
            }
        }.start()
    }
}