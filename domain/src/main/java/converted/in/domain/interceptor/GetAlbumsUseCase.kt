package converted.`in`.domain.interceptor

import converted.`in`.domain.repo.Repository
import converted.`in`.domain.response.AlbumsApiResponse
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(private val repo: Repository) {
    suspend operator fun invoke(userId: Int): AlbumsApiResponse {
        return repo.getAlbums(userId)
    }
}