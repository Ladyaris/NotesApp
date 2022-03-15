package com.chores.notesapp.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun hideKeyboard(activity: Activity){
    val inputMethodManage = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    val currentFocusedView = activity.currentFocus
    currentFocusedView.let {
        inputMethodManage.hideSoftInputFromWindow(
            currentFocusedView?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}