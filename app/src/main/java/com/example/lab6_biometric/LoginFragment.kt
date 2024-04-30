package com.example.lab6_biometric

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.example.lab6_biometric.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var username = ""
        var key = ""
        var oldKey = ""
        var oldUsername = ""
        var cntOfLogin = 3

        binding.patternLockView.addPatternLockListener(object : PatternLockViewListener {
            override fun onStarted() {
            }

            override fun onProgress(progressPattern: MutableList<PatternLockView.Dot>?) {
            }

            override fun onComplete(pattern: MutableList<PatternLockView.Dot>?) {
                key = pattern.toString()
                val sharedPreferences =
                    requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
                oldKey = sharedPreferences.getString("pattern_key", "").toString()
                oldUsername = sharedPreferences.getString("username", "").toString()

            }

            override fun onCleared() {

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



        binding.buttonCheck.setOnClickListener {

            if (cntOfLogin == 0) {
                Toast.makeText(context, "Ошибка! Закончились попытки!", Toast.LENGTH_LONG).show()
            } else {
                if (key == oldKey && username == oldUsername) {
                    Toast.makeText(context, "Вход успешно выполнен!", Toast.LENGTH_LONG).show()
                } else {
                    cntOfLogin--
                    Toast.makeText(
                        context,
                        "Ошибка! Ключи не совпадают! Оставшиеся попытки: $cntOfLogin",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}