package converted.`in`.convertedin.utils

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import converted.`in`.convertedin.databinding.LayoutProgressDialogBinding


object Utils {
    var isFirstTime = true
    var isFromOrders = true
    private var dialog: Dialog? = null
    private const val TAG = "Utils"
    fun showProgressBar(
        mContext: Context,
        cancelable: Boolean = false,
    ) {
        Log.d(TAG, "showProgressBar: Called")
        dialog = Dialog(mContext)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(cancelable)
        val binding = LayoutProgressDialogBinding.inflate(LayoutInflater.from(mContext))
        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog!!.setContentView(binding.root)
        dialog!!.setCancelable(true)

        try {
            dialog?.show()
        } catch (e: Exception) {
            Log.e(TAG, "showProgressBar: CATCH ${e.message}")
        }
    }

    fun hideProgressBar() {
        Log.d(TAG, "hideProgressBar: Called")
        try {
            if (dialog != null && dialog!!.isShowing) {
                dialog?.dismiss()
            }
        } catch (e: Exception) {
            Log.e(TAG, "hideProgressBar: CATCH ${e.message}")
        }
    }


}