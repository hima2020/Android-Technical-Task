package converted.`in`.domain.repo

import converted.`in`.domain.response.AlbumsApiResponse
import converted.`in`.domain.response.PhotosApiResponse
import converted.`in`.domain.response.UsersApiResponse


interface Repository {
    suspend fun getUsers(): UsersApiResponse
    suspend fun getAlbums(userId: Int): AlbumsApiResponse
    suspend fun getPhotos(albumId: Int): PhotosApiResponse
}