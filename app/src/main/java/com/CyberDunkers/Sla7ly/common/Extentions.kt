package com.CyberDunkers.Sla7ly.common

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.CyberDunkers.Sla7ly.common.util.MultiPartUtil
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun Context.drawableResToUri(drawableResId: Int): Uri {
    val res = resources
    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(res.getResourcePackageName(drawableResId))
        .appendPath(res.getResourceTypeName(drawableResId))
        .appendPath(res.getResourceEntryName(drawableResId))
        .build()
}
@ExperimentalPermissionsApi
fun PermissionState.isPermanentlyDenied(): Boolean {
    return !shouldShowRationale && !hasPermission
}


//convert String to part
fun String.toRequestBody() : RequestBody {
    val mediaType = "multipart/form-data".toMediaType()
    return this.toRequestBody(mediaType)
}

fun Uri.toMultiPart(context: Context) : MultipartBody.Part{
    return MultiPartUtil.fileToMultiPart(
            context.applicationContext, this,
            "uploaded-file"
        )
}

fun Context.openSettingForEnable(){
    Intent(
        Settings.ACTION_LOCATION_SOURCE_SETTINGS,
        Uri.fromParts("package", packageName, null)

    ).also(::startActivity)
}
