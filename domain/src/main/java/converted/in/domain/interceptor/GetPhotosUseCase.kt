package converted.`in`.domain.interceptor

import converted.`in`.domain.repo.Repository
import converted.`in`.domain.response.AlbumsApiResponse
import converted.`in`.domain.response.PhotosApiResponse
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repo: Repository) {
    suspend operator fun invoke(albumId: Int): PhotosApiResponse {
        return repo.getPhotos(albumId)
    }
}