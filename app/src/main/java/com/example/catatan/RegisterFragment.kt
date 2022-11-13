package com.example.catatan

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.catatan.R
import com.example.catatan.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().applicationContext.getSharedPreferences("datauser",
            Context.MODE_PRIVATE)
        binding.btnRegister.setOnClickListener {
            val username = binding.username.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val repeatpassword = binding.confirmPassword.text.toString()
            var addData = sharedPreferences.edit()

            if (password == repeatpassword) {
                addData.putString("username",username)
                addData.putString("email",email)
                addData.putString("password",password)
                addData.apply()
                it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                Toast.makeText(activity, "Register Succesed", Toast.LENGTH_SHORT).show()
            } else if (password != repeatpassword) {
                Toast.makeText(activity, "Password Wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}