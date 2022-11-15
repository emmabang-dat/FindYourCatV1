package com.example.findyourcatv1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.findyourcatv1.databinding.FragmentAddCatBinding
import com.example.findyourcatv1.models.Cat
import com.example.findyourcatv1.models.CatsViewModel
import com.example.findyourcatv1.models.UsersViewModel

class AddCatFragment : Fragment() {
    private var _binding: FragmentAddCatBinding? = null
    private val binding get() = _binding!!

    private val catsViewModel: CatsViewModel by activityViewModels()
    private val usersViewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddCatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)

        binding.buttonAddCat.setOnClickListener {
            val name = binding.edittextName.text.toString().trim()
            val description = binding.edittextDescription.text.toString().trim()
            val place = binding.edittextPlace.text.toString().trim()
            val reward = binding.edittextReward.text
            val userId: String = usersViewModel.userLiveData.value?.email!!

            if (name.isEmpty()) {
                binding.edittextName.error = "Is Empty"
                return@setOnClickListener
            }
            if (description.isEmpty()) {
                binding.edittextDescription.error = "Is Empty"
                return@setOnClickListener
            }
            if (place.isEmpty()) {
                binding.edittextPlace.error = "Is Empty"
                return@setOnClickListener
            }
            if (reward.isNullOrEmpty()) {
                binding.edittextReward.error = "Is Empty"
                return@setOnClickListener
            }

        val newCat = Cat (name, description, place, reward.toString().toInt(), userId)
        catsViewModel.add(newCat)
        catsViewModel.reload()
        findNavController().popBackStack()
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}