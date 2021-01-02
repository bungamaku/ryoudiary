package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.util

import android.Manifest
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.R

fun Fragment.isGranted(permission: AppPermission) = run {
    context?.let {
        (PermissionChecker.checkSelfPermission(it, permission.permissionName
        ) == PermissionChecker.PERMISSION_GRANTED)
    } ?: false
}

fun Fragment.shouldShowRationale(permission: AppPermission) = run {
    shouldShowRequestPermissionRationale(permission.permissionName)
}

fun Fragment.requestPermission(permission: AppPermission) {
    requestPermissions(arrayOf(permission.permissionName), permission.requestCode
    )
}

fun Fragment.handlePermission(
    permission: AppPermission,
    onGranted: (AppPermission) -> Unit,
    onDenied: (AppPermission) -> Unit,
    onRationaleNeeded: ((AppPermission) -> Unit)? = null
) {
    when {
        isGranted(permission) ->onGranted.invoke(permission)
        shouldShowRationale(permission) ->  onRationaleNeeded?.invoke(permission)
        else ->  onDenied.invoke(permission)
    }
}

fun Fragment.handlePermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray,
    onPermissionGranted: (AppPermission) -> Unit,
    onPermissionDenied: ((AppPermission) -> Unit)? = null,
    onPermissionDeniedPermanently: ((AppPermission) -> Unit)? = null
) {

    AppPermission.permissions.find {
        it.requestCode == requestCode
    }?.let { appPermission ->
        val permissionGrantResult = mapPermissionsAndResults(permissions, grantResults
        )[appPermission.permissionName]
        when {
            PermissionChecker.PERMISSION_GRANTED == permissionGrantResult -> {
                onPermissionGranted(appPermission)
            }
            shouldShowRationale(appPermission) -> onPermissionDenied?.invoke(appPermission)
            else -> {
                onPermissionDeniedPermanently?.invoke(appPermission)
            }
        }
    }
}

private fun mapPermissionsAndResults(
    permissions: Array<out String>, grantResults: IntArray
): Map<String, Int> = permissions.mapIndexedTo(mutableListOf<Pair<String, Int>>()
) { index, permission -> permission to grantResults[index] }.toMap()

sealed class AppPermission(
    val permissionName: String, val requestCode: Int,
    val deniedMessageId: Int, val explanationMessageId: Int
) {
    companion object {
        val permissions: List<AppPermission> by lazy {
            listOf(
                ACCESS_COARSE_LOCATION,
                ACCESS_FINE_LOCATION
            )
        }
    }

    object ACCESS_FINE_LOCATION : AppPermission(
        Manifest.permission.ACCESS_FINE_LOCATION, 42,
        R.string.permission_required_text, R.string.permission_required_text
    )

    object ACCESS_COARSE_LOCATION : AppPermission(
        Manifest.permission.ACCESS_COARSE_LOCATION, 43,
        R.string.permission_required_text, R.string.permission_required_text
    )
}