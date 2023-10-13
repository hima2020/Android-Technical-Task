package converted.`in`.convertedin.di

import converted.`in`.domain.interceptor.GetAlbumsUseCase
import converted.`in`.domain.interceptor.GetPhotosUseCase
import converted.`in`.domain.interceptor.GetUsersUseCase
import converted.`in`.domain.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetUsersUseCase(repo: Repository): GetUsersUseCase {
        return GetUsersUseCase(repo)
    }

    @Provides
    fun provideGetAlbumsUseCase(repo: Repository): GetAlbumsUseCase {
        return GetAlbumsUseCase(repo)
    }

    @Provides
    fun provideGetPhotosUseCase(repo: Repository): GetPhotosUseCase {
        return GetPhotosUseCase(repo)
    }
}