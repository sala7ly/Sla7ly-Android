package com.CyberDunkers.Sla7ly.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.CyberDunkers.Sla7ly.data.models.applocal.AppLocal
import com.CyberDunkers.Sla7ly.presentation.authentication.authoptions.AuthOptionsViewModels
import java.util.Locale

object LanguageHelper {
    fun changeLang(
        newLang: AppLocal,
        configuration: Configuration,
        resources: Resources,
        context: Context,
    ) {
        // to change the language
        val locale = Locale(newLang.name)
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        context.findActivity()?.recreate()
    }

    fun changeLanguage(
        viewModels: AuthOptionsViewModels, configuration: Configuration,
        resources: Resources,
        context: Context,
    ) {
        val currentLanguage = Locale.getDefault().language
        Log.d("test" , currentLanguage)
        if (currentLanguage == "en") {
            changeLang(AppLocal.AR, configuration, resources, context)
            viewModels.saveCurrentLang(AppLocal.AR)
            val config = Configuration(context.resources.configuration)
            config.setLocale(Locale("ar"))

            context.createConfigurationContext(config)
        } else {
            changeLang(AppLocal.EN, configuration, resources, context)
            viewModels.saveCurrentLang(AppLocal.AR)
            val config = Configuration(context.resources.configuration)
            config.setLocale(Locale("en"))
        }
    }
}



fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}