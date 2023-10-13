package converted.`in`.data.source.remote

import converted.`in`.domain.response.AlbumsApiResponse
import converted.`in`.domain.response.PhotosApiResponse
import converted.`in`.domain.response.UsersApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("users")
    suspend fun getUser(): UsersApiResponse

    @GET("albums")
    suspend fun getAlbums(@Query("userId") userId: Int): AlbumsApiResponse

    @GET("photos")
    suspend fun getPhotos(@Query("albumId") albumId: Int): PhotosApiResponse
}