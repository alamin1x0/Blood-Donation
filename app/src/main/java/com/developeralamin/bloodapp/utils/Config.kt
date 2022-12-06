package com.developeralamin.bloodapp.utils

import androidx.appcompat.app.AlertDialog
import android.content.Context
import com.developeralamin.bloodapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object Config {

    private var dialog: AlertDialog? = null

    fun showDialog(context: Context){
        dialog = MaterialAlertDialogBuilder(context)
            .setView(R.layout.loading_layout)
            .setCancelable(false)
            .create()

        dialog!!.show()
    }
    fun hideDialog(){
        dialog!!.dismiss()
    }

}