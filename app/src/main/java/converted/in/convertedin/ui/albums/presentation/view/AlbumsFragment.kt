package converted.`in`.convertedin.ui.albums.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import converted.`in`.convertedin.databinding.FragmentAlbumsBinding
import converted.`in`.convertedin.ui.albums.presentation.view.adapter.AlbumsListAdapter
import converted.`in`.convertedin.ui.albums.presentation.viewmodel.AlbumsViewModel
import converted.`in`.convertedin.utils.GeneralStates
import converted.`in`.convertedin.utils.Utils
import converted.`in`.domain.response.AlbumsApiResponse
import converted.`in`.domain.response.UsersApiResponseItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AlbumsFragment : Fragment() {


    private lateinit var binding: FragmentAlbumsBinding
    private lateinit var adapter: AlbumsListAdapter

    private val viewModel: AlbumsViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        initViews()

        initObservation()
        getAlbums(
            (Gson().fromJson(
                arguments?.get(UserKey).toString(),
                UsersApiResponseItem::class.java
            )).id
        )
        return binding.root
    }

    private fun initViews() {

        binding.apply {
            val user = Gson().fromJson(
                arguments?.get(UserKey).toString(),
                UsersApiResponseItem::class.java
            )
            tvUserName.text = user.name
            tvAddress.text = "${user.address.city} ,${user.address.street} ,${user.address.suite}"
            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun getAlbums(id: Int) {
        if (viewModel.albums.isEmpty()) {
            viewModel.getAlbums(id)
            initObservation()
        } else {
            initAlbumsViews(viewModel.albums)
        }

    }


    private fun initObservation() {
        lifecycleScope.launch {
            viewModel.albumsViewState.collect {
                when (it) {
                    is GeneralStates.Failed -> {
                        Utils.hideProgressBar()
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                    }

                    is GeneralStates.Loading -> {
                        Utils.showProgressBar(requireContext(), false)
                    }

                    is GeneralStates.Success<*> -> {
                        Utils.hideProgressBar()
                        initAlbumsViews(it.data as AlbumsApiResponse)
                    }
                }
            }
        }
    }

    private fun initAlbumsViews(albumsApiResponse: AlbumsApiResponse) {
        viewModel.albums = albumsApiResponse
        binding.apply {

            adapter = AlbumsListAdapter(albumsApiResponse) {

                findNavController().navigate(
                    AlbumsFragmentDirections.actionAlbumsFragmentToPhotosFragment(it)
                )
            }
            rvAlbums.adapter = adapter

        }
    }

    companion object {

        const val UserKey = "userData"
    }
}
