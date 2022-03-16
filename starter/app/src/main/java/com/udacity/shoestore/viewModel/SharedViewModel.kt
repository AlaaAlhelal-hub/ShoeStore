package com.udacity.shoestore.viewModel


import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.models.Shoe

class SharedViewModel: ViewModel() {

    private var _listOfShoes = MutableLiveData<MutableList<Shoe>>()
    val listOfShoes: LiveData<MutableList<Shoe>>
        get() = _listOfShoes


    init {
        setListOfShoes()
    }


    private fun setListOfShoes() {
        val shoe1 = Shoe( "Air Huarache", 8.5, "Nike", "Running shoes")
        val shoe2 = Shoe( "Adidas Originals", 8.0, "Adidas", "Running shoes")
        val shoe3 = Shoe( "Sketchers Slippers", 7.5, "Sketchers", "Perfect wear for a beach picnic")
        val shoe4 = Shoe( "Sketchers Sneaker", 9.0, "Sketchers", "Running shoes")
        val shoe5 = Shoe( "Sandals", 7.0, "Nike", "Perfect wear for a beach picnic")
        _listOfShoes.value = mutableListOf(shoe1, shoe2, shoe3, shoe4, shoe5)

    }

    fun addNewShoe(shoes: Shoe){
        shoes.let {
            _listOfShoes.value?.add(0, it)

        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}