package converted.`in`.convertedin.ui.users.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import converted.`in`.convertedin.utils.GeneralStates
import converted.`in`.domain.interceptor.GetUsersUseCase
import converted.`in`.domain.response.UsersApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,

    ) : ViewModel() {
    var users = UsersApiResponse()
    private val _getUsersSuccess: MutableSharedFlow<GeneralStates> = MutableSharedFlow()
    val successResponse: SharedFlow<GeneralStates> = _getUsersSuccess


    fun getUsers() {
        viewModelScope.launch {
            _getUsersSuccess.emit(GeneralStates.Loading)
            try {
                val res = getUsersUseCase.invoke()

                _getUsersSuccess.emit(GeneralStates.Success(res))


            } catch (ex: Exception) {
                ex.printStackTrace()
                _getUsersSuccess.emit(GeneralStates.Failed(ex.message ?: "SomeThing went Wrong !!"))
            }
        }
    }
}

