package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.R
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.databinding.FragmentSecondBinding
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel.CountdownViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var viewModel: CountdownViewModel
    private lateinit var binding: FragmentSecondBinding
    var countDownOnGoing = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_second, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(CountdownViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonPrevious.setOnClickListener {
            if (countDownOnGoing) {
                Toast.makeText(activity?.applicationContext, "Countdown is still on going!", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }

        var value = viewModel.currentValue.toLong()

        val titleSecondText = getString(R.string.textview_title_second_text, value)
        binding.textViewTitleSecond.text = titleSecondText

        value *= 1000
        startCountdown(value)
    }

    private fun startCountdown(seconds: Long) {
        object : CountDownTimer(seconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countDownOnGoing = true
                binding.textViewSeconds.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(activity?.applicationContext, "Countdown finished!", Toast.LENGTH_SHORT).show()
                countDownOnGoing = false
            }
        }.start()
    }
}