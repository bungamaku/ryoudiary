package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.FirstFragmentDirections
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.R
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.databinding.FragmentFirstBinding
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel.CountdownViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var viewModel: CountdownViewModel
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_first, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(CountdownViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {
            val showValueTextView = binding.textViewInput
            viewModel.currentValue = showValueTextView.text.toString().toInt()
            if (viewModel.currentValue == 0) {
                Toast.makeText(activity?.applicationContext, "Countdown can't start from 0!", Toast.LENGTH_SHORT).show()
            } else {
                val action =
                    FirstFragmentDirections.actionFirstFragmentToSecondFragment()
                findNavController().navigate(action)
            }
        }

        binding.plusButton.setOnClickListener {
            plusValue(view)
        }

        binding.minusButton.setOnClickListener {
            minusValue(view)
        }
    }

    private fun plusValue(view: View) {
        val showValueTextView = binding.textViewInput
        showValueTextView.text = viewModel.plusValue()
    }

    private fun minusValue(view: View) {
        val showValueTextView = binding.textViewInput
        var value = viewModel.currentValue
        if (value < 1) {
            Toast.makeText(activity?.applicationContext, "Seconds can't be negative!", Toast.LENGTH_SHORT).show()
        } else {
            showValueTextView.text = viewModel.minusValue()
        }
    }
}