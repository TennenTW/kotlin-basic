package com.thoughtworks.kotlin_basic.util

import com.thoughtworks.kotlin_basic.exceptions.MaxSizeExceededException
import kotlin.math.pow

class ColumnUtil {
    private val alphabets = ('A'..'Z').toList()
    private val digits = alphabets.size
    private val maxSize = digits.toDouble().pow(3).toInt()

    fun convertToAlphabeticalLabels(start: Int, numbers: Int): Array<String> {
        val end = start + numbers - 1
        if (end > maxSize) throw MaxSizeExceededException()
        val result = mutableListOf<String>()
        for (i in start .. end) {
            val alphabetList = mutableListOf<String>()
            var number = i
            println(i)
            while (number > 0) {
                val alphabetIndex = (number - 1) % digits
                println(alphabetIndex)
                alphabetList.add(alphabets[alphabetIndex].toString())
                /* 26不进位 */
                if (number == digits) break
                number /= digits
            }
            val label: String = alphabetList.reversed().joinToString("")
            result.add(label)
        }
        return result.toTypedArray()
    }
}