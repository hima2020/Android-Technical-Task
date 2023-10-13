package converted.`in`.convertedin.ui.albums.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import converted.`in`.convertedin.utils.GeneralStates
import converted.`in`.domain.interceptor.GetAlbumsUseCase
import converted.`in`.domain.response.AlbumsApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,

    ) : ViewModel() {

    var albums = AlbumsApiResponse()
    private val _getAlbumsSuccess: MutableSharedFlow<GeneralStates> = MutableSharedFlow()
    val albumsViewState: SharedFlow<GeneralStates> = _getAlbumsSuccess


    fun getAlbums(albumId: Int) {
        viewModelScope.launch {
            _getAlbumsSuccess.emit(GeneralStates.Loading)
            try {
                val res = getAlbumsUseCase.invoke(albumId)

                _getAlbumsSuccess.emit(GeneralStates.Success(res))


            } catch (ex: Exception) {
                ex.printStackTrace()
                _getAlbumsSuccess.emit(
                    GeneralStates.Failed(
                        ex.message ?: "SomeThing went Wrong !!"
                    )
                )
            }
        }
    }
}

