package converted.`in`.convertedin.ui.photos.presentation.view

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import converted.`in`.convertedin.databinding.FragmentPhotosBinding
import converted.`in`.convertedin.ui.photos.presentation.view.adapter.PhotosListAdapter
import converted.`in`.convertedin.ui.photos.presentation.view.dialog.ImageViewerDialogFragment
import converted.`in`.convertedin.ui.photos.presentation.viewmodel.PhotosViewModel
import converted.`in`.convertedin.utils.GeneralStates
import converted.`in`.convertedin.utils.Utils
import converted.`in`.domain.response.PhotosApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosFragment : Fragment() {


    private lateinit var binding: FragmentPhotosBinding
    private lateinit var response: PhotosApiResponse
    private lateinit var adapter: PhotosListAdapter

    private val viewModel: PhotosViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)
        initViews()
        initObservation()
        getPhotos(arguments?.get(albumKey) as Int)
        return binding.root
    }

    private fun getPhotos(albumId: Int) {
        viewModel.getPhotos(albumId)
    }

    private fun viewImage(url: String) {
        val fileViewerDialogFragment =
            ImageViewerDialogFragment(url)
        fileViewerDialogFragment.show(
            childFragmentManager,
            ImageViewerDialogFragment::class.java.simpleName
        )
    }

    private fun initObservation() {
        lifecycleScope.launch {
            viewModel.photosViewState.collect {
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
                        initPhotosView(it.data as PhotosApiResponse)
                    }
                }
            }
        }
    }

    private fun initPhotosView(photosApiResponse: PhotosApiResponse) {
        response = photosApiResponse
        binding.apply {
            val metrics = DisplayMetrics()
            activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
            adapter =
                PhotosListAdapter(photosApiResponse, metrics.widthPixels - 60) {
                    viewImage(it)
                }
            rvPhotos.layoutManager = GridLayoutManager(activity, 3)
            rvPhotos.adapter = adapter
        }
    }

    private fun initViews() {

        binding.apply {
            etSearch.doOnTextChanged { text, start, before, count ->
                adapter.updateItems(response.filter {
                    it.title.contains(text.toString())
                })
            }
            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    companion object {
        const val albumKey = "albumId"

    }

}