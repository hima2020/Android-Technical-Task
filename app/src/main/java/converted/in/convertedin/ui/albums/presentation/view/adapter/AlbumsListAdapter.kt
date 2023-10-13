package converted.`in`.convertedin.ui.albums.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import converted.`in`.convertedin.databinding.ItemAlbumBinding
import converted.`in`.domain.response.AlbumsApiResponseItem

class AlbumsListAdapter(
    private val items: List<AlbumsApiResponseItem?>,
    private val onClick: (Int) -> Unit
) :
    RecyclerView.Adapter<AlbumsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: AlbumsApiResponseItem?) {
            binding.apply {
                tvAlbumTitle.text = album?.title
            }
            itemView.rootView.setOnClickListener {
                album?.let { it1 -> onClick.invoke(it1.id) }
            }

        }
    }
}
