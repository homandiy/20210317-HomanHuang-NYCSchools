package com.example.background.helper

import android.util.Log

// logcat shortcut helper
const val TAG = "MLOG"
fun lgd(s:String) = Log.d(TAG, s)
fun lgi(s:String) = Log.i(TAG, s)
fun lge(s:String) = Log.e(TAG, s)
fun lgv(s:String) = Log.v(TAG, s)
fun lgw(s:String) = Log.w(TAG, s)