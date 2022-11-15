package com.example.findyourcatv1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.findyourcatv1.databinding.FragmentRegisterBinding
import com.example.findyourcatv1.models.UsersViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val usersViewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegister.setOnClickListener {
            val email = binding.edittextEmail.text.toString().trim()
            val password = binding.edittextPassword.text.toString().trim()
            if (email.isEmpty()) {
                binding.edittextEmail.error = "No Email"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.edittextPassword.error = "No Password"
                return@setOnClickListener
            }
            usersViewModel.createUserWithEmailAndPassword(email, password)
            findNavController().popBackStack()
        }

        usersViewModel.userLiveData.observe(viewLifecycleOwner) {
            if (usersViewModel.userLiveData.value != null) {
                val action = RegisterFragmentDirections.actionRegisterFragmentToListFragment()
                findNavController().navigate(action)
            }
        }

        usersViewModel.errorLiveData.observe(viewLifecycleOwner) {
            if (usersViewModel.errorLiveData.value != null) {
                Snackbar.make(
                    binding.root,
                    usersViewModel.errorLiveData.value.toString(),
                    Snackbar.LENGTH_LONG
                ).show()
                Log.d("BANANA", usersViewModel.errorLiveData.value.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}