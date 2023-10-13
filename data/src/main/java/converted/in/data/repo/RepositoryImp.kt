package converted.`in`.data.repo

import converted.`in`.data.source.remote.ApiService
import converted.`in`.domain.repo.Repository
import converted.`in`.domain.response.AlbumsApiResponse
import converted.`in`.domain.response.PhotosApiResponse
import converted.`in`.domain.response.UsersApiResponse
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val apiService: ApiService) :
    Repository {
    override suspend fun getUsers(): UsersApiResponse {
        return apiService.getUser()
    }

    override suspend fun getAlbums(userId: Int): AlbumsApiResponse {
        return apiService.getAlbums(userId)
    }

    override suspend fun getPhotos(albumId: Int): PhotosApiResponse {
        return apiService.getPhotos(albumId)
    }


}