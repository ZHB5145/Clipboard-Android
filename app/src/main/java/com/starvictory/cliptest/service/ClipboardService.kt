package com.starvictory.cliptest.service

import android.app.Service
import android.content.ClipboardManager
import android.content.ClipboardManager.OnPrimaryClipChangedListener
import android.content.Intent
import android.os.IBinder
import android.util.Log
/**
 * Created by zhb5145 on 2020/12/24
 */
class ClipboardService : Service() {

    private var mClipboardManager: ClipboardManager? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        mClipboardManager?.removePrimaryClipChangedListener(changedListener)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        mClipboardManager?.addPrimaryClipChangedListener(changedListener)
        return super.onStartCommand(intent, flags, startId)
    }

    private val changedListener = OnPrimaryClipChangedListener {
        Log.d("zhb5145", "[SYSTEM] clipboard changing detected")

    }
}