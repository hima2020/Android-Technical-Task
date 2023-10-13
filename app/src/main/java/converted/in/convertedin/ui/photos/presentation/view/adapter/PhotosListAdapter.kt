package converted.`in`.convertedin.ui.photos.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import converted.`in`.convertedin.databinding.ItemPhotoBinding
import converted.`in`.domain.response.PhotosApiResponseItem


class PhotosListAdapter(
    private var items: List<PhotosApiResponseItem?>,
    private val width: Int,
    private val onClick: (String) -> Unit



) : RecyclerView.Adapter<PhotosListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: PhotosApiResponseItem?) {
            binding.apply {

                val lp = binding.root.layoutParams
                lp.height = width / 3
                lp.width = lp.height
                binding.root.layoutParams = lp
                Glide.with(itemView.rootView.context).load(photo?.thumbnailUrl).into(ivPhoto)

                root.setOnClickListener{
                    onClick.invoke(photo?.thumbnailUrl!!)
                }
            }

        }
    }

    fun updateItems(updatedItems: List<PhotosApiResponseItem?>) {
        items = updatedItems
        notifyDataSetChanged()
    }
}
