package com.neowise.androidlessions.itmath.model

fun generateProblem(from: Int, to: Int): Problem {
    val a = (from..to).random()
    val b = (from..to).random()
    val operation = randomOperation()

    val answer = operation.eval(a, b)
    val variants =  generateVariants(answer)
    return Problem(a, b, operation, answer, variants)
}

private fun generateVariants(answer: Int): List<Int> {
    val variants = mutableListOf(answer)
    // after add generated wrong answers
    repeat(8) {
        variants += (1..(answer + 100)).random()
    }
    return variants.shuffled()
}

class Problem internal constructor(
    private val a: Int,
    private val b: Int,
    private val operation: Operation,
    private val answer: Int,
    val variants: List<Int>
) {
    fun isCorrect(answer: Int): Boolean = this.answer == answer
    override fun toString(): String = "$a $operation $b"
}