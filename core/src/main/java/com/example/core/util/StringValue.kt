package com.example.core.util

import android.content.Context
import androidx.annotation.StringRes

/*
    this was created because string manipulation shouldn't really be done in viewModels,
    also getting resources from strings.xml requires context to be passed in the viewModel
*/
sealed class StringValue {

    data class DynamicString(val value: String) : StringValue()

    object Empty : StringValue()

    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : StringValue()

    fun asString(context: Context?): String {
        return when (this) {
            is Empty -> ""
            is DynamicString -> value
            is StringResource -> context?.getString(resId, *args).orEmpty()
        }
    }
}
