package com.thoughtworks.kotlin_basic.util

import com.thoughtworks.kotlin_basic.exceptions.MaxSizeExceededException
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith

class ColumnUtilTest {
    private val columnUtil = ColumnUtil()
    @Test
    fun `Parameters (1, 1) should return (A)`() {
        assertContentEquals(arrayOf("A"), columnUtil.convertToAlphabeticalLabels(1, 1))
    }

    @Test
    fun `Parameters (1, 2) should return (A, B)`() {
        assertContentEquals(arrayOf("A", "B"), columnUtil.convertToAlphabeticalLabels(1, 2))
    }

    @Test
    fun `Parameters (26, 3) should return (Z, AA, AB)`() {
        assertContentEquals(arrayOf("Z", "AA", "AB"), columnUtil.convertToAlphabeticalLabels(26, 3))
    }

    @Test
    fun `Parameters (702, 2) should return (ZZ, AAA)`() {
        assertContentEquals(arrayOf("ZZ", "AAA"), columnUtil.convertToAlphabeticalLabels(702, 2))
    }

    @Test
    fun `Parameters (18274, 5) should return (ZZV, ZZW, ZZX, ZZY, ZZZ)`() {
        assertContentEquals(arrayOf("ZZV", "ZZW", "ZZX", "ZZY", "ZZZ"), columnUtil.convertToAlphabeticalLabels(18274, 5))
    }

    @Test
    fun `Up to ZZZ, beyond which, an error should be thrown`() {
        assertFailsWith(MaxSizeExceededException::class) {
            columnUtil.convertToAlphabeticalLabels(18278, 2)
        }
    }
}