package com.example.lab6_biometric

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.example.lab6_biometric.databinding.FragmentLoginBinding
import com.example.lab6_biometric.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var username = ""

        binding.patternLockView.addPatternLockListener(object : PatternLockViewListener {
            override fun onStarted() {
            }

            override fun onProgress(progressPattern: MutableList<PatternLockView.Dot>?) {
            }

            override fun onComplete(pattern: MutableList<PatternLockView.Dot>?) {
                val key = pattern.toString()

                val sharedPreferences =
                    requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("pattern_key", key)
                editor.apply()
                binding.saveButton.visibility = View.VISIBLE
                binding.clearButton.visibility = View.VISIBLE

            }

            override fun onCleared() {
                val sharedPreferences = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.remove("pattern_key")
                editor.apply()
                binding.saveButton.visibility = View.GONE
                binding.clearButton.visibility = View.GONE
            }

        })

        binding.username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                username = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.clearButton.setOnClickListener {
            binding.patternLockView.clearPattern()
            binding.saveButton.visibility = View.GONE
            binding.clearButton.visibility = View.GONE
        }

        binding.saveButton.setOnClickListener {
            val sharedPreferences =
                requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("username", username)
            editor.apply()
            findNavController().popBackStack()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}