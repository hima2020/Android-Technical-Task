package converted.`in`.domain.interceptor

import converted.`in`.domain.repo.Repository
import converted.`in`.domain.response.UsersApiResponse
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repo: Repository) {
    suspend operator fun invoke(): UsersApiResponse {
        return repo.getUsers()
    }
}