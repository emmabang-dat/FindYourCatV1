package com.example.findyourcatv1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.findyourcatv1.databinding.FragmentDetailCatBinding
import com.example.findyourcatv1.models.Cat
import com.example.findyourcatv1.models.CatsViewModel
import com.example.findyourcatv1.models.UsersViewModel
import com.google.android.material.snackbar.Snackbar

class DetailCatFragment : Fragment() {
    private var _binding: FragmentDetailCatBinding? = null
    private val args: DetailCatFragmentArgs by navArgs()
    private val binding get() = _binding!!

    private val catsViewModel: CatsViewModel by activityViewModels()
    private val usersViewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCatBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cat = catsViewModel[args.id]
        if (cat == null) {
            binding.errorTextview.text = "No Cat Found"
            return
        }

        binding.catId.text = cat.id.toString()
        binding.catName.text = cat.name
        binding.catDesc.text = cat.description
        binding.catPlace.text = cat.place
        binding.catReward.text = cat.reward.toString().trim()
        binding.catUserid.text = cat.userId

        binding.buttonContact.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(cat.userId))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, cat.name + " " + cat.id.toString())

            if (emailIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(emailIntent)
            } else {
                Log.d("BANANA", "Email intent unable to handle action")
            }
        }

        if (cat.userId == (usersViewModel.userLiveData.value?.email ?: String)) {
            binding.buttonDelete.visibility = View.VISIBLE

            binding.buttonDelete.setOnClickListener {
                catsViewModel.delete(cat.id)
                val snack = Snackbar.make(it, "Cat has been deleted", Snackbar.LENGTH_LONG)
                snack.show()
                findNavController().popBackStack()
                }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}