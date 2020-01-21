package com.amrudesh.appupdater

import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.amrudesh.appupdater.databinding.UpdateAlertDialogBinding
import kotlinx.android.synthetic.main.dialog_button_layout.view.*


/**
 * Created by Amrudesh Balakrishnan.
 */
class Display {
    fun showUpdateAvailableDialog(
        context: Context,
        title: String,
        content: String,
        positiveButtonText: String,
        negativeButtonText: String,
        resId: Int,
        updateNow: Boolean
    ): AlertDialog.Builder {

        val updateAlertDialogBinding: UpdateAlertDialogBinding
        val layoutInflater = LayoutInflater.from(context)
        updateAlertDialogBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.update_alert_dialog,
            null, false
        )


        val alertDialog = AlertDialog.Builder(context)
        val customLayout = updateAlertDialogBinding.dialogButton.rootView



        updateAlertDialogBinding.dialogButton.apply {
            updateAlertDialogBinding.dialogButton.invalidate()
            updateAlertDialogBinding.dialogButton.main_heading.text = title
            updateAlertDialogBinding.dialogButton.layout_description.text = content
            updateAlertDialogBinding.dialogButton.positive_button.text = positiveButtonText
            updateAlertDialogBinding.dialogButton.positive_button.background =
                ContextCompat.getDrawable(context, R.drawable.positive_button_back)
            updateAlertDialogBinding.dialogButton.negative_button.text = negativeButtonText
            updateAlertDialogBinding.dialogButton.negative_button.background =
                ContextCompat.getDrawable(context, R.drawable.negative_button_back)
            updateAlertDialogBinding.dialogButton.updateImage.setImageResource(resId)

        }
        updateAlertDialogBinding.dialogButton.positive_button.setOnClickListener {
            if (updateNow) {
                updateAlertDialogBinding.dialogButton.animate().setDuration(1000).alpha(0f).start()
                updateAlertDialogBinding.progressDialog.animate().setDuration(1000).alpha(1f).start()
            }
        }

        updateAlertDialogBinding.dialogButton.negative_button.setOnClickListener {
            Toast.makeText(context, "You have Pressed Negative Button", Toast.LENGTH_SHORT).show()
        }


        if (updateNow) {
            alertDialog.setCancelable(false)
        }
        alertDialog.setView(customLayout)


        return alertDialog
    }
}