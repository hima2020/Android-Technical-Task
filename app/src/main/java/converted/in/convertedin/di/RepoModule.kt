package converted.`in`.convertedin.di

import converted.`in`.data.repo.RepositoryImp
import converted.`in`.data.source.remote.ApiService
import converted.`in`.domain.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {


    @Provides
    fun provideRepoModule(apiService: ApiService): Repository {
        return RepositoryImp(apiService)

    }

}