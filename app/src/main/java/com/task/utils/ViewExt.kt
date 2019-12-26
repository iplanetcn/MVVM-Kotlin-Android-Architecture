package com.task.utils

import android.app.Service
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import com.squareup.picasso.Picasso

fun View.showKeyboard() {
    (context?.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    (context?.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(@DrawableRes resId: Int) = Picasso.get().load(resId).into(this)

fun AppCompatTextView.setTextFutureExt(text: String) =
        setTextFuture(
                PrecomputedTextCompat.getTextFuture(
                        text,
                        TextViewCompat.getTextMetricsParams(this),
                        null
                )
        )

fun AppCompatEditText.setTextFutureExt(text: String) =
        setText(
                PrecomputedTextCompat.create(text, TextViewCompat.getTextMetricsParams(this))
        )
