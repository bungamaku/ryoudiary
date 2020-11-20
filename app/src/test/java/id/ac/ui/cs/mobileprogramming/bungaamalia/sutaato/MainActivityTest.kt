package id.ac.ui.cs.mobileprogramming.bungaamalia.sutaato

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MainActivityTest {

    @Test
    fun plusValueCorrect() {
        val mainActivity = MainActivity()
        mainActivity.plusValue()
        assertEquals(1, mainActivity.value)
    }

    @Test
    fun minusValueCorrect() {
        val mainActivity = MainActivity()
        mainActivity.minusValue()
        assertEquals(-1, mainActivity.value)
    }
}