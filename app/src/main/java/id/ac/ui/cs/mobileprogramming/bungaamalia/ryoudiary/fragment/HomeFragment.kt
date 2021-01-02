package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.R
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeListButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeListFragment)
        }
        binding.homeCountdownButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_InputCountdownFragment)
        }
    }

}