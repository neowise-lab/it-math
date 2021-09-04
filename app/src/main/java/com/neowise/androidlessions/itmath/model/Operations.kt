package com.neowise.androidlessions.itmath.model

private val operations = arrayOf(
    PlusOperation(),
    MinusOperation(),
    MultipleOperation(),
    DivideOperation()
)

fun randomOperation() = operations.random()

abstract class Operation(private val text: String) {
    abstract fun eval(a: Int, b: Int): Int
    override fun toString() = text
}

class PlusOperation : Operation("+") {
    override fun eval(a: Int, b: Int) = a + b
}

class MinusOperation : Operation("-") {
    override fun eval(a: Int, b: Int) = a - b
}

class DivideOperation : Operation("/") {
    override fun eval(a: Int, b: Int) = a / b
}

class MultipleOperation : Operation("*") {
    override fun eval(a: Int, b: Int)  = a * b
}