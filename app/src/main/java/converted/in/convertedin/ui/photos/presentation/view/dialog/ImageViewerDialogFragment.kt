package converted.`in`.convertedin.ui.photos.presentation.view.dialog

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import converted.`in`.convertedin.databinding.DialogImageViewerBinding


class ImageViewerDialogFragment(
    private val imageLink: String,
) : DialogFragment() {
    private var _binding: DialogImageViewerBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogImageViewerBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val TAG = "ImageViewerDialogFragment"

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            ivClose.setOnClickListener {
                dialog?.dismiss()
            }
            Glide.with(root.context).load(imageLink).into(ivDocImg)
            binding.btnShare.setOnClickListener {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type="text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, imageLink);
                startActivity(Intent.createChooser(shareIntent,"Share My Image"))
            }

        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width, height)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.window?.setGravity(Gravity.CENTER)
            dialog.setCancelable(false)
        }
    }
}
