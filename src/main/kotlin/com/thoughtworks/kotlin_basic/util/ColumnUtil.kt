package com.thoughtworks.kotlin_basic.util

import com.thoughtworks.kotlin_basic.exceptions.MaxSizeExceededException
import kotlin.math.pow

class ColumnUtil(private val alphabets: List<Char> = ('A'..'Z').toList(), private val times: Int = 3) {
    private val digits = alphabets.size
    private val maxSize = getMaxSize()

    private fun getMaxSize(): Int {
        var res = 0
        for (i in 1 .. times) {
            res += digits.toDouble().pow(i).toInt()
        }
        return res
    }

    private fun getAlphabeticalLabelByNumber(number: Int): String {
        val alphabetList = mutableListOf<String>()
        var tempNumber = number
        while (tempNumber > 0) {
            val alphabetIndex = (tempNumber - 1) % digits
            alphabetList.add(alphabets[alphabetIndex].toString())
            tempNumber = (tempNumber - 1) / digits
        }
        return alphabetList.reversed().joinToString("")
    }

    fun convertToAlphabeticalLabels(start: Int, numbers: Int): Array<String> {
        val end = start + numbers - 1
        if (end > maxSize) throw MaxSizeExceededException()
        val result = mutableListOf<String>()
        for (i in start .. end) {
            result.add(getAlphabeticalLabelByNumber(i))
        }
        return result.toTypedArray()
    }
}