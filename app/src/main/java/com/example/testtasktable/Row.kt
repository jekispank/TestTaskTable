package com.example.testtasktable

import android.widget.TextView
import com.example.testtasktable.databinding.FragmentMainBinding

class Row {

    fun getArrayOfPoints(binding: FragmentMainBinding) = arrayOf(

        arrayOf(
            binding.partOnePointTwo,
            binding.partOnePointThree,
            binding.partOnePointFour,
            binding.partOnePointFive,
            binding.partOnePointSix,
            binding.partOnePointSeven,
            binding.partOneSum
        ),

        arrayOf(
            binding.partTwoPointOne,
            binding.partTwoPointThree,
            binding.partTwoPointFour,
            binding.partTwoPointFive,
            binding.partTwoPointSix,
            binding.partTwoPointSeven,
            binding.partTwoSum
        ),

        arrayOf(
            binding.partThreePointOne,
            binding.partThreePointTwo,
            binding.partThreePointFour,
            binding.partThreePointFive,
            binding.partThreePointSix,
            binding.partThreePointSeven,
            binding.partThreeSum
        ),

        arrayOf(
            binding.partFourPointOne,
            binding.partFourPointTwo,
            binding.partFourPointThree,
            binding.partFourPointFive,
            binding.partFourPointSix,
            binding.partFourPointSeven,
            binding.partFourSum
        ),

        arrayOf(
            binding.partFivePointOne,
            binding.partFivePointTwo,
            binding.partFivePointThree,
            binding.partFivePointFour,
            binding.partFivePointSix,
            binding.partFivePointSeven,
            binding.partFiveSum
        ),

        arrayOf(
            binding.partSixPointOne,
            binding.partSixPointTwo,
            binding.partSixPointThree,
            binding.partSixPointFour,
            binding.partSixPointFive,
            binding.partSixPointSeven,
            binding.partSixSum
        ),

        arrayOf(
            binding.partSevenPointOne,
            binding.partSevenPointTwo,
            binding.partSevenPointThree,
            binding.partSevenPointFour,
            binding.partSevenPointFive,
            binding.partSevenPointSeven,
            binding.partSevenSum
        ),
    )
}