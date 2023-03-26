package com.example.testtasktable

import android.widget.TextView
import com.example.testtasktable.databinding.FragmentMainBinding

class Place {
    fun getListOfPlaces(binding: FragmentMainBinding) = arrayOf(
        binding.partOnePlace,
        binding.partTwoPlace,
        binding.partThreePlace,
        binding.partFourPlace,
        binding.partFivePlace,
        binding.partSixPlace,
        binding.partSevenPlace,
    )
}