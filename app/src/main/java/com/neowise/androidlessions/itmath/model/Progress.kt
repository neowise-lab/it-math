package com.neowise.androidlessions.itmath.model

data class Progress(
    val current: Int,
    val max: Int
) {
    val isReached: Boolean
        get() = (current == max)

    operator fun inc() : Progress {
        return Progress(current + 1, max)
    }
}
