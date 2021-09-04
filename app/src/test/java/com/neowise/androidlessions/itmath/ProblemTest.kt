package com.neowise.androidlessions.itmath

import org.junit.Test
import com.neowise.androidlessions.itmath.model.*

class ProblemTest {

    @Test
    fun testGenerateProblems() {
        repeat(10) {
            val problem = generateProblem(10, 100)
            println(problem)
            println(problem.variants)
        }
    }
}