package com.example.lab6_biometric

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab6_biometric.databinding.FragmentLoginBinding
import com.example.lab6_biometric.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_registerFragment)
        }


    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}