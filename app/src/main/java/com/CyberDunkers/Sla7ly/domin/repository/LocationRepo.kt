package com.CyberDunkers.Sla7ly.domin.repository

import android.content.Context

interface LocationRepo {

    fun hasLocationPermission(context: Context ): Boolean
    fun getCurrentLocation(context: Context ,callback: (Double, Double) -> Unit )
}