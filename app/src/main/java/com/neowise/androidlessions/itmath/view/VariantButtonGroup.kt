package com.neowise.androidlessions.itmath.view

import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.neowise.androidlessions.itmath.R

class VariantButtonGroup(private val variantSelectListener: VariantSelectListener) {

    private val buttons = mutableListOf<VariantButtonWrapper>()
    private var lastSelectedButton: VariantButtonWrapper? = null

    operator fun plusAssign(button: Button) {
        val buttonWrapper = VariantButtonWrapper(button)
        buttons += buttonWrapper

        button.setOnClickListener {
            lastSelectedButton?.select(false)
            select(buttonWrapper)
            variantSelectListener.variantSelected(button, buttonWrapper.variant)
        }
    }

    fun clear() {
        lastSelectedButton?.select(false)
    }

    private fun select(buttonWrapper: VariantButtonWrapper) {
        buttonWrapper.select(true)
        lastSelectedButton = buttonWrapper
    }

    fun initVariants(variants: List<Int>) {
        for ((index, wrapper) in buttons.withIndex()) {
            wrapper.variant = variants[index]
        }
    }

    private class VariantButtonWrapper(private val button: Button) {

        var variant: Int = -1
            set(value) {
                field = value
                button.text = value.toString()
            }

        fun select(selected: Boolean) {
            val color = if (selected) R.color.accent_red else R.color.blue_button
            ViewCompat.setBackgroundTintList(
                button,
                ContextCompat.getColorStateList(button.context, color)
            )
        }
    }

    fun interface VariantSelectListener {
        fun variantSelected(button: Button, variant: Int)
    }
}
