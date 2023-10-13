package converted.`in`.convertedin.ui.users.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import converted.`in`.convertedin.databinding.ItemUserBinding
import converted.`in`.domain.response.UsersApiResponseItem

class UserListAdapter(
    private val items: List<UsersApiResponseItem?>,
    private val onClick: (UsersApiResponseItem) -> Unit
) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UsersApiResponseItem?) {
            binding.apply {

                tvUserNameShort.text = user?.name?.subSequence(0, 2).toString().capitalize()
                tvUserName.text = user?.name
            }
            itemView.rootView.setOnClickListener {
                onClick.invoke(user!!)
            }

        }
    }
}
