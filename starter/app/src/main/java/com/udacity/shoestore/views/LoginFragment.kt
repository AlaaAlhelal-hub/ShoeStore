package com.udacity.shoestore.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginBtn.setOnClickListener {
            if (isValid()) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }

        binding.signUp.setOnClickListener {
            if (isValid()) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }

        return binding.root
    }

    private fun isValid(): Boolean {
       if (binding.emailInput.text.isEmpty()){
           binding.emailInput.error = resources.getText(R.string.required_field)
           return false
       }
        if (binding.passwordInput.text.isEmpty()){
            binding.passwordInput.error = resources.getText(R.string.required_field)
            return false
        }
        return true
    }


}