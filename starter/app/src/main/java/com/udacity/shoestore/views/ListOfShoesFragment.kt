package com.udacity.shoestore.views

import android.os.Bundle
import android.view.*
import android.view.ViewGroup.MarginLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentListOfShoesBinding
import com.udacity.shoestore.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.shoe_item.view.*


class ListOfShoesFragment : Fragment() {


    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentListOfShoesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListOfShoesBinding.inflate(inflater, container, false)


        binding.lifecycleOwner = this



        viewModel.listOfShoes.observe(viewLifecycleOwner, Observer {
            newList ->
            newList.forEach { shoe ->
                val inflatedView = layoutInflater.inflate(R.layout.shoe_item, null);
                inflatedView.name_view.text = shoe.name
                inflatedView.company_view.text = shoe.company
                inflatedView.size_view.text = shoe.size.toString()
                inflatedView.description_view.text = shoe.description
                inflatedView.image.setImageResource(R.drawable.ic_baseline_image_24)
                val marginParams = MarginLayoutParams(MarginLayoutParams.MATCH_PARENT, MarginLayoutParams.WRAP_CONTENT)
                val margins = resources.getDimension(R.dimen.small_margin).toInt()
                marginParams.setMargins(margins, margins, margins, margins)
                inflatedView.layoutParams = marginParams
                binding.mainLinearLayout.addView(inflatedView)
            }
        })

        binding.addShoeBtn.setOnClickListener {
            findNavController().navigate(ListOfShoesFragmentDirections
                .actionListOfShoesFragmentToAddNewShoeFragment())
        }
        binding.shoesViewModel = viewModel


        setHasOptionsMenu(true)


        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.logout_item) {
            findNavController().navigate(ListOfShoesFragmentDirections.actionListOfShoesFragmentToLoginFragment())
            true
        } else super.onOptionsItemSelected(item)
    }


}