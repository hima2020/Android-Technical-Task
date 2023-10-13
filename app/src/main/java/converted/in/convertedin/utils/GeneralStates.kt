package converted.`in`.convertedin.utils

sealed class GeneralStates {
    object Loading : GeneralStates()
//    object NoConnection : GeneralStates()

    //TODO we have to simplify check type
    data class Success<T>(val data: T) : GeneralStates()
    data class Failed(val error: String = "") : GeneralStates()
}
