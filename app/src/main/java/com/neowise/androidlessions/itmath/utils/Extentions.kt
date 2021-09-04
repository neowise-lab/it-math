package com.neowise.androidlessions.itmath.utils

import android.widget.EditText

val EditText.string: String
    get() = this.text.toString().trim()