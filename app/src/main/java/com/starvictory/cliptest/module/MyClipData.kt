package com.starvictory.cliptest.module

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Created by zhb5145 on 2020/12/24
 */

const val MINE_TYPE_TEXT_PLAIN = "text"
const val MINE_TYPE_URI_PLAIN = "URI"
const val MINE_TYPE_INTENT_PLAIN = "Intent"

class MyClipData {


    var mClipData: ClipData? = null

    companion object {
        private var instance: MyClipData? = null

        @Synchronized
        fun get(): MyClipData {
            if (null == instance) instance = MyClipData()
            return instance as MyClipData
        }
    }

    fun writeClipText(
        label: String = MINE_TYPE_TEXT_PLAIN,
        text: String = "",
        context: Context
    ): Unit {
        mClipData = ClipData.newPlainText(label, text)
        getClipboardManager(context).setPrimaryClip(mClipData!!)
    }

    fun writeClipURI(label: String = "URI", uri: String = "", context: Context) {
        mClipData = ClipData.newUri(context.contentResolver, label, Uri.parse(uri))
        getClipboardManager(context).setPrimaryClip(mClipData!!)
    }

    fun writeClipIntent(intent: Intent, label: String = "Intent", context: Context) {
        mClipData = ClipData.newIntent(label, intent)
        getClipboardManager(context).setPrimaryClip(mClipData!!)
    }

    fun getClipString(context: Context): String? {
        when {
            !getClipboardManager(context).hasPrimaryClip() -> {
                return null
            }
            !(getClipboardManager(context).primaryClipDescription?.hasMimeType(MINE_TYPE_TEXT_PLAIN)
                ?: true) -> {
                return null
            }

        }
        val item = getClipboardManager(context).primaryClip?.getItemAt(0)
        val pasteData = item?.text?:return null
        return pasteData.toString()
    }


    private fun getClipboardManager(context: Context): ClipboardManager {
        return context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }


}