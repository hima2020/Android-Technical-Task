package converted.`in`.domain.response

class AlbumsApiResponse : ArrayList<AlbumsApiResponseItem>()

data class AlbumsApiResponseItem(
    val id: Int,
    val title: String,
    val userId: Int
)