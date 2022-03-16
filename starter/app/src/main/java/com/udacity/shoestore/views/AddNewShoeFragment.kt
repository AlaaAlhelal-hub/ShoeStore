package com.udacity.shoestore.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentAddNewShoeBinding
import com.udacity.shoestore.databinding.FragmentListOfShoesBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewModel.SharedViewModel


class AddNewShoeFragment : Fragment() {


    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentAddNewShoeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewShoeBinding.inflate(inflater, container, false)



        binding.saveButton.setOnClickListener {
            if (isValid()) {
                val name = binding.nameEditText.text.toString()
                val size = binding.sizeEditText.text.toString().toDouble()
                val company = binding.companyEditText.text.toString()
                val des = binding.descriptionEditText.text.toString()
                val shoe = Shoe(name, size, company, des)
                viewModel.addNewShoe(shoe)
                Snackbar.make(binding.root, "added successfully",
                    Snackbar.LENGTH_LONG)
                findNavController().navigate(AddNewShoeFragmentDirections
                    .actionAddNewShoeFragmentToListOfShoesFragment())
            }
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(AddNewShoeFragmentDirections
                    .actionAddNewShoeFragmentToListOfShoesFragment())
        }

        return binding.root
    }


    private fun isValid(): Boolean {
        if (binding.nameEditText.text.isEmpty()){
            binding.nameEditText.error = resources.getText(R.string.required_field)
            return false
        }
        if (binding.descriptionEditText.text.isEmpty()){
            binding.descriptionEditText.error = resources.getText(R.string.required_field)
            return false
        }
        if (binding.companyEditText.text.isEmpty()){
            binding.companyEditText.error = resources.getText(R.string.required_field)
            return false
        }
        if (binding.sizeEditText.text.isEmpty()){
            binding.sizeEditText.error = resources.getText(R.string.required_field)
            return false
        }

        return true
    }

}