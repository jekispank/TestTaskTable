package com.example.testtasktable

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(private val point: Int, private val index: Int) : ViewModel() {


    private var _arrayOne = MutableLiveData<List<List<Int?>>>(List(7) { List(6) { null } })
    val arrayOne: LiveData<List<List<Int?>>> = _arrayOne

    private var _sum = MutableLiveData<List<Int?>>(List(7) { null })
    val sum: LiveData<List<Int?>> = _sum

    private var _finalPlaces = MutableLiveData<List<Int?>>()
    val finalPlaces: LiveData<List<Int?>> = _finalPlaces

    /* Function to add values to array of points of participants and count the point's sums of every participant .
    Then the array added to Live Data for displaying in UI */
    fun addToArray(index: Int, point: Int, arrayIndex: Int) {
        val temporaryList = _arrayOne.value.orEmpty().toMutableList()
        val innerList = temporaryList[arrayIndex].toMutableList()
        innerList[index] = point
        temporaryList[arrayIndex] = innerList
        _arrayOne.value = temporaryList

        Log.d("ViewModel", "1 Array is $temporaryList")

        val newSumList = _sum.value.orEmpty().toMutableList()
        if (!temporaryList[arrayIndex].contains(null)) {
            val sum = temporaryList[arrayIndex].filterNotNull().sum()
            newSumList[arrayIndex] = sum
            _sum.value = newSumList
            Log.d("ViewModel", "2 Sum List is $newSumList")

        }
        else {
            newSumList[arrayIndex] = null
            _sum.value = newSumList
            Log.d("ViewModel", "2 Sum List is $newSumList")
        }
    }

    fun distributeFinalPlaces() {
        val arrayOfPoints: List<Int?>? = _sum.value
        Log.d("ViewModel", "34 New list is $arrayOfPoints")
        val newArrayOfPoints = MutableList<Int>(7) {0}
        var index = 0
        if (arrayOfPoints != null) {
            for (i in arrayOfPoints) {
                if (i != null) {
                    newArrayOfPoints[index] = i
                }
                index++
            }
        }
        Log.d("ViewModel", "33 New list is $newArrayOfPoints")


            val indexedPoints = newArrayOfPoints.mapIndexed { index, value -> Pair(index, value) }
            val sortedPoints =
                indexedPoints.sortedWith(compareByDescending<Pair<Int, Int>> { it.second }.thenBy { it.first })

            val places = MutableList(newArrayOfPoints.size) { 0 }
            var place = 1

            for (i in sortedPoints.indices) {
                val index = sortedPoints[i].first
                if (i > 0 && sortedPoints[i].second != sortedPoints[i - 1].second) {
                    place++
                }
                places[index] = place
            }
        _finalPlaces.value = places.toList()

        }


//        val sortedArray = arrayOfPoints?.filterNotNull()?.sortedDescending()
//        var arrayOfFinalPlaces = List<Int?>(7){ null }
//        val arrayToLiveData = arrayOfFinalPlaces.toTypedArray()
//        arrayOfPoints?.forEach {
//            val index = arrayOfPoints.indexOf(it)
//            val value  = sortedArray?.indexOf(it)?.plus(1)
//            val previousValue  = sortedArray?.indexOf(it)
//            arrayToLiveData[index] = value
//            Log.d("ViewModel", "Index is $index, value is $value")
//            _finalPlaces.value = arrayToLiveData.toList()
//        }
//        Log.d("ViewModel", "23 list is $arrayToLiveData")
//    }
}

class MainViewModelFactory(private val point: Int, private val index: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(point, index) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}

    


