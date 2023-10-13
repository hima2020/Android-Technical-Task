package converted.`in`.domain.response

class PhotosApiResponse : ArrayList<PhotosApiResponseItem>()

data class PhotosApiResponseItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)