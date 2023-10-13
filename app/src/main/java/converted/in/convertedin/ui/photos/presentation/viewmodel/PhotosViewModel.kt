package converted.`in`.convertedin.ui.photos.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import converted.`in`.convertedin.utils.GeneralStates
import converted.`in`.domain.interceptor.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,

    ) : ViewModel() {

    private val _getPhotosSuccess: MutableSharedFlow<GeneralStates> = MutableSharedFlow()
    val photosViewState: SharedFlow<GeneralStates> = _getPhotosSuccess


    fun getPhotos(albumId: Int) {
        viewModelScope.launch {
            _getPhotosSuccess.emit(GeneralStates.Loading)
            try {
                val res = getPhotosUseCase.invoke(albumId)

                _getPhotosSuccess.emit(GeneralStates.Success(res))


            } catch (ex: Exception) {
                ex.printStackTrace()
                _getPhotosSuccess.emit(
                    GeneralStates.Failed(
                        ex.message ?: "SomeThing went Wrong !!"
                    )
                )
            }
        }
    }
}

