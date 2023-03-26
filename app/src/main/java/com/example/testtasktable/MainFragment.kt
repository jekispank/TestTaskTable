package com.example.testtasktable

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testtasktable.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var row: Row
    private lateinit var place: Place
    private var point = 0
    private var index = 0

    private val viewModelFactory: MainViewModelFactory by lazy {
        MainViewModelFactory(point, index)
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val arrayList = initializeListOfParticipants()
        for (array in arrayList) {
            for (item in array) {
                setOnAction(item, array, arrayList)
                Log.d("MainFragment", "ARRAYLIST is $arrayList.")
            }

        }
    }

    private fun setOnAction(
        element: TextView,
        arrayOfElements: Array<TextView>,
        arrayListOfParticipants: Array<Array<TextView>>
    ) {

        element.setOnEditorActionListener { textView, _, _ ->
            val currentValue = textView.text.toString()
            if (checkCurrentValue(currentValue)) {
                textView.setBackgroundResource(R.drawable.background_outlined_red)
                Toast.makeText(
                    requireContext(),
                    "Enter valid value from 0 to 5!",
                    Toast.LENGTH_SHORT
                )
                    .show()
                true
            } else {
                if (arrayOfElements.indexOf(element) != 5) {
                    addValueSetSum(textView, element, arrayOfElements, arrayListOfParticipants)
                    textView.setBackgroundResource(R.drawable.background_outlined)
                } else {
                    addValueSetSum(textView, element, arrayOfElements, arrayListOfParticipants)
                    textView.setBackgroundResource(R.drawable.background_outlined)
                    viewModel.sum.observe(viewLifecycleOwner, Observer {
                        Log.d("MainFragment", "2 List of sum from LiveData is $it")
                        arrayOfElements[6].text =
                            it[arrayListOfParticipants.indexOf(arrayOfElements)]?.toString()
                        if (!it.contains(null)) setFinalPlaces()
                    })

                }
                false
            }
        }
    }

    /* Return true if entered value is  empty, more than 5 or less than 0 */
    private fun checkCurrentValue(value: String): Boolean {
        return value.isEmpty() || value.toInt() < 0 || value.toInt() > 5
    }

    /* Set participant's point to UI*/
    private fun setPoints(ints: List<List<Int?>>, currentIndex: Int, arrayIndex: Int) {
        initializeListOfParticipants()[arrayIndex][currentIndex].text =
            ints[arrayIndex][currentIndex].toString()
    }

    /* Return array of view for editing score for first participant */
    private fun initializeListOfParticipants(): Array<Array<TextView>> {
        row = Row()
        return row.getArrayOfPoints(binding)
    }

    /* Add entered value to List for setting to UI and update sum */
    fun addValueSetSum(
        textView: TextView,
        item: TextView,
        array: Array<TextView>,
        arrayList: Array<Array<TextView>>
    ) {
        val point = Integer.parseInt(textView.text.toString())
        val arrayIndex = arrayList.indexOf(array)
        val internalIndex = array.indexOf(item)
        viewModel.addToArray(internalIndex, point, arrayIndex)
        viewModel.arrayOne.observe(viewLifecycleOwner, Observer {
            setPoints(it, internalIndex, arrayIndex)
        })
    }

    /* Fun to set final places of participants according to scored points */
    private fun setFinalPlaces() {
        place = Place()
        val listOfCells = place.getListOfPlaces(binding)
        viewModel.distributeFinalPlaces()
        viewModel.finalPlaces.observe(viewLifecycleOwner, Observer { list ->
            Log.d("MainFragment", "List of places is $list")

            listOfCells.forEach {
                val index = listOfCells.indexOf(it)
                val value = list[index]

                it.text = value.toString()

            }
        })
    }
}
