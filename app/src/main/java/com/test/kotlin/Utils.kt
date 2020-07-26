package com.test.kotlin

import android.app.ActivityManager
import android.app.ActivityManager.RecentTaskInfo
import android.app.ActivityOptions
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.projection.MediaProjection
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.Display
import android.view.Surface
import java.util.*

object Utils {

    fun log(msg: String) {
        Log.i("TestKotlin", msg)
    }

}