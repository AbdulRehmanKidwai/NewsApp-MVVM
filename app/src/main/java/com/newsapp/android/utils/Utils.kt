package com.newsapp.android.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import android.widget.SearchView
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Utils {

    companion object{
        fun openCustomTabUrl(context: Context, url:String){
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(url))
        }
    }


}