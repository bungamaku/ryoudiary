package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.R
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.databinding.FragmentInputCountdownBinding
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel.CountdownViewModel

class InputCountdownFragment : Fragment() {
    private lateinit var viewModel: CountdownViewModel
    private lateinit var binding: FragmentInputCountdownBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_input_countdown, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(CountdownViewModel::class.java)
        viewModel.currentValue.observe(viewLifecycleOwner, Observer { newValue ->
            binding.textViewInput.text = newValue.toString() })
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {
            if (viewModel.currentValue.value?.toInt() == 0) {
                Toast.makeText(activity?.applicationContext,
                    "Countdown can't start from 0!", Toast.LENGTH_SHORT).show()
            } else {
                findNavController()
                    .navigate(R.id.action_InputCountdownFragment_to_OutputCountdownFragment)
            }
        }

        binding.plusButton.setOnClickListener {
            plusValue()
        }

        binding.minusButton.setOnClickListener {
            minusValue()
        }
    }

    private fun plusValue() {
        binding.textViewInput.text = viewModel.plusValue()
    }

    private fun minusValue() {
        if (viewModel.currentValue.value?.toInt()!! < 1) {
            Toast.makeText(activity?.applicationContext,
                "Seconds can't be negative!", Toast.LENGTH_SHORT).show()
        } else {
            binding.textViewInput.text = viewModel.minusValue()
        }
    }
}