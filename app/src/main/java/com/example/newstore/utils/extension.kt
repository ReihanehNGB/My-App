package com.example.newstore.utils

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.snackbar.Snackbar

//
//fun Toast.failure(
//    message: String,
//    gravity: Int = Gravity.TOP,
//    durationTime: Int = Toast.LENGTH_LONG
//) {
//    val binding = DataBindingUtil.inflate<CustomToastBinding>(LayoutInflater.from(APP.applicationContext()), R.layout.custom_toast, null, false)
//
//    binding.let {
//        it.type = ToastType.FAILURE
//        it.text.text = message
//        it.width = DeviceUtils.width
//        it.icon.setAnimation(R.raw.toast_warning_anim)
//        it.icon.playAnimation()
//        it.root.setOnClickListener { this.cancel() }
//    }
//
//    this.apply {
//        setGravity(gravity, 0, 40)
//        duration = durationTime
//        view = binding.root
//        show()
//    }
//}
//
//
//fun Toast.success(
//    message: String,
//    gravity: Int = Gravity.TOP,
//    durationTime: Int = Toast.LENGTH_LONG
//) {
//    val binding = DataBindingUtil.inflate<CustomToastBinding>(LayoutInflater.from(APP.applicationContext()), R.layout.custom_toast, null, false)
//
//    binding.let {
//        it.width = DeviceUtils.width
//        it.type = ToastType.SUCCESS
//        it.text.text = message
//        it.icon.setAnimation(R.raw.toast_done_anim)
//        it.icon.playAnimation()
//        it.root.setOnClickListener { this.cancel() }
//    }
//
//
//    this.apply {
//        setGravity(gravity, 0, 40)
//        duration = durationTime
//        view = binding.root
//        show()
//    }
//
//
//}


fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun RecyclerView.disableItemAnimator() {
    //disable blink when notifyDataSetChanged
    (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
}

fun EditText.showKeyboard() {
    this.requestFocus()
    (this.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
        .showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun EditText.hideKeyboard() {
    (this.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(this.windowToken, 0)
}

fun EditText.clear() {
    this.setText("", TextView.BufferType.EDITABLE)
}

fun EditText.setText(text: String) {
    this.setText(text, TextView.BufferType.EDITABLE)
}

//fun RecyclerView.paginateListener(listener: () -> Unit) {
//    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            super.onScrolled(recyclerView, dx, dy)
//            if (dy > 0 && !recyclerView.canScrollVertically(1)) {
//                listener.invoke()
//            }
//        }
//    })
//}

//fun Activity.showSnackBar(
//    text: String,
//    action1: String? = null,
//    action2: String? = null,
//    action3: String? = null,
//    durationTime: Int = Snackbar.LENGTH_INDEFINITE,
//    action1Listener: ((snackBar: Snackbar) -> Unit)? = null,
//    action2Listener: ((snackBar: Snackbar) -> Unit)? = null,
//    action3Listener: ((snackBar: Snackbar) -> Unit)? = null,
//): Snackbar {
//    val snackbar = Snackbar.make(findViewById(android.R.id.content), "", durationTime)
//
//    DataBindingUtil.inflate<ir.tanrah.tanrahapp.databinding.CustomSnackBarBinding>(
//        LayoutInflater.from(this), R.layout.custom_snack_bar, null, false
//    )?.also {
//        it.text.text = text
//        it.action1.text = action1
//        it.action2.text = action2
//        it.action3.text = action3
//
//        it.action1.setOnClickListener { action1Listener?.invoke(snackbar) }
//        it.action2.setOnClickListener { action2Listener?.invoke(snackbar) }
//        it.action3.setOnClickListener { action3Listener?.invoke(snackbar) }
//
//        snackbar.view.setBackgroundColor(Color.TRANSPARENT)
//        (snackbar.view as Snackbar.SnackbarLayout).setPadding(0, 0, 0, 0)
//        val params = snackbar.view.layoutParams as FrameLayout.LayoutParams
//        params.gravity = Gravity.TOP
//        snackbar.view.layoutParams = params
//        MUtils.addView(snackbar.view as Snackbar.SnackbarLayout, it.constraint)
//        snackbar.show()
//    }
//
//    return snackbar
//}


fun EditText.onTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged.invoke(s.toString())
        }

        override fun afterTextChanged(editable: Editable?) {
        }
    })
}

fun TextView.setText(value: String?) {
    if (value.isNullOrEmpty()) this.text = ""
    else this.text = value
}

//fun Context.showMessageDialog(
//    title: String,
//    description: String,
//    iconDrawable: Int = R.drawable.ic_error_dialog,
//    positiveText: String? = "",
//    negativeText: String? = "",
//    neutralText: String? = "",
//    cancelable: Boolean = false,
//    positiveListener: ((dialog: MaterialDialog) -> Unit)? = null,
//    negativeListener: ((dialog: MaterialDialog) -> Unit)? = null,
//    neutralListener: ((dialog: MaterialDialog) -> Unit)? = null
//) {
//    DataBindingUtil.inflate<DialogAlertBinding?>(
//        LayoutInflater.from(this), R.layout.dialog_alert, null, false
//    )?.let {
//        it.title.text = title
//        it.description.text = description
//        it.negative.text = negativeText
//        it.positive.text = positiveText
//        it.neutral.text = neutralText
//
//        it.image.setImageDrawable(ContextCompat.getDrawable(this, iconDrawable))
//
//        val dialog = MaterialDialog(this).show {
//            customView(view = it.root)
//            cornerRadius(15f)
//            cancelable(cancelable)
//        }
//
//        it.negative.setOnClickListener { negativeListener?.invoke(dialog) }
//        it.positive.setOnClickListener { positiveListener?.invoke(dialog) }
//        it.neutral.setOnClickListener { neutralListener?.invoke(dialog) }
//    }
//}
